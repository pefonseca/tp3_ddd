package infnet.ddd.testeperformance3.domain.service.impl;

import infnet.ddd.testeperformance3.api.rest.dto.request.ProdutoRequestDTO;
import infnet.ddd.testeperformance3.api.rest.dto.response.ProdutoResponseDTO;
import infnet.ddd.testeperformance3.infra.exception.PedidoException;
import infnet.ddd.testeperformance3.domain.model.Produto;
import infnet.ddd.testeperformance3.domain.repository.ProdutoRepository;
import infnet.ddd.testeperformance3.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll().stream().map(prod ->
                ProdutoResponseDTO.builder()
                        .id(prod.getId())
                        .nome(prod.getNome())
                        .preco(prod.getPreco())
                        .quantidade(prod.getQuantidade())
                        .pedidos(prod.getPedidos())
                        .build())
                .toList();
    }

    @Override
    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    @Override
    public Produto create(ProdutoRequestDTO produto) {
        Produto newProduto = new Produto();
        BeanUtils.copyProperties(produto, newProduto);
        return produtoRepository.save(newProduto);
    }

    @Override
    public Produto update(Long id, ProdutoRequestDTO produto) {
        Produto produtoDB = findById(id);
        BeanUtils.copyProperties(produto, produtoDB, "id");
        return produtoDB;
    }

    @Override
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        produtoRepository.delete(produto);
    }

    @Override
    public boolean verificarDisponibilidade(Produto produto, int quantidade) throws Exception {
        boolean disponibilidade = true;
        if(produto.getQuantidade() > 0) {
            System.out.println("Temos estoque");
            if(quantidade > produto.getQuantidade()) {
                throw new PedidoException("Não temos está quantidade do produto no estoque.");
            }
        }
        return disponibilidade;
    }
}
