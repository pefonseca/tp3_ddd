package infnet.ddd.testeperformance3.domain.service.impl;

import infnet.ddd.testeperformance3.api.rest.dto.request.PedidoRequestDTO;
import infnet.ddd.testeperformance3.api.rest.dto.response.PedidoResponseDTO;
import infnet.ddd.testeperformance3.domain.mapper.PedidoMapper;
import infnet.ddd.testeperformance3.domain.mapper.ProdutoMapper;
import infnet.ddd.testeperformance3.domain.model.Pedido;
import infnet.ddd.testeperformance3.domain.model.Produto;
import infnet.ddd.testeperformance3.domain.repository.PedidoRepository;
import infnet.ddd.testeperformance3.domain.service.PedidoService;
import infnet.ddd.testeperformance3.domain.service.ProdutoService;
import infnet.ddd.testeperformance3.infra.feign.CepClient;
import infnet.ddd.testeperformance3.infra.response.CepResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final CepClient cepClient;
    private final PedidoMapper pedidoMapper;

    @Override
    public PedidoResponseDTO createPedido(PedidoRequestDTO pedidoRequestDTO) throws Exception {
        Produto produto = produtoService.findById(pedidoRequestDTO.getProdutoId());
        verificarDisponibilidadeProduto(produto, pedidoRequestDTO.getQuantidade());
        Pedido pedido = pedidoMapper.toPedido(pedidoRequestDTO, produto);
        CepResponse cepResponse = verificarCidadeEBairro(pedidoRequestDTO.getCep());
        pedido.setCidade(cepResponse.getLocalidade());
        pedido.setBairro(cepResponse.getBairro());
        pedidoRepository.save(pedido);
        return pedidoMapper.toPedidoResponseDTO(pedido);
    }

    @Override
    public PedidoResponseDTO getPedido(Long id) {
        return pedidoMapper.toPedidoResponseDTO(pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));
    }

    @Override
    public PedidoResponseDTO updatePedido(Long id, PedidoRequestDTO pedidoRequestDTO) throws Exception {
        Pedido pedidoDB = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        Produto produto = produtoService.findById(pedidoRequestDTO.getProdutoId());
        verificarDisponibilidadeProduto(produto, pedidoRequestDTO.getQuantidade());
        BeanUtils.copyProperties(pedidoRequestDTO, pedidoDB, "id", "logradouro", "bairro");
        pedidoRepository.save(pedidoDB);
        return pedidoMapper.toPedidoResponseDTO(pedidoDB);
    }

    @Override
    public void deletePedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        List<PedidoResponseDTO> pedidos = pedidoRepository.findAll().stream().map(prod ->
                PedidoResponseDTO.builder()
                                 .id(prod.getId())
                                 .quantidade(prod.getQuantidade())
                                 .bairro(prod.getBairro())
                                 .produto(ProdutoMapper.toProdutoResponseDTO(prod.getProduto()))
                                 .cep(prod.getCep())
                                 .logradouro(prod.getCidade())
                                 .build())
                                 .toList();
        return pedidos;
    }

    private boolean verificarDisponibilidadeProduto(Produto produto, int quantidade) throws Exception {
        return produtoService.verificarDisponibilidade(produto, quantidade);
    }

    private CepResponse verificarCidadeEBairro(String cep) {
        try {
            CepResponse cepResponse = new CepResponse();
            if(Strings.isNotBlank(cep)) {
                cepResponse = cepClient.consultarCep(cep);
            }
            return cepResponse;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao consultar cep: " + e.getMessage());
        }
    }
}
