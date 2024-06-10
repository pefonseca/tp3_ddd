package infnet.ddd.testeperformance3.domain.mapper;

import infnet.ddd.testeperformance3.api.rest.dto.request.PedidoRequestDTO;
import infnet.ddd.testeperformance3.api.rest.dto.request.ProdutoRequestDTO;
import infnet.ddd.testeperformance3.api.rest.dto.response.PedidoResponseDTO;
import infnet.ddd.testeperformance3.api.rest.dto.response.ProdutoResponseDTO;
import infnet.ddd.testeperformance3.domain.model.Pedido;
import infnet.ddd.testeperformance3.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        return PedidoResponseDTO.builder()
                                .id(pedido.getId())
                                .quantidade(pedido.getQuantidade())
                                .produto(ProdutoMapper.toProdutoResponseDTO(pedido.getProduto()))
                                .cep(pedido.getCidade())
                                .logradouro(pedido.getCidade())
                                .bairro(pedido.getBairro())
                                .build();
    }

    public Pedido toPedido(PedidoRequestDTO pedidoRequestDTO, Produto produto) {
        return Pedido.builder()
                     .id(pedidoRequestDTO.getId())
                     .quantidade(pedidoRequestDTO.getQuantidade())
                     .produto(produto)
                     .cep(pedidoRequestDTO.getCep())
                     .build();
    }
}
