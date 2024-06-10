package infnet.ddd.testeperformance3.domain.mapper;

import infnet.ddd.testeperformance3.api.rest.dto.request.ProdutoRequestDTO;
import infnet.ddd.testeperformance3.domain.model.Produto;
import infnet.ddd.testeperformance3.api.rest.dto.response.ProdutoResponseDTO;

public class ProdutoMapper {

    public static ProdutoResponseDTO toProdutoResponseDTO(Produto produto) {
        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .preco(produto.getPreco())
                .quantidade(produto.getQuantidade())
                .build();
    }

    public static Produto toProduto(ProdutoRequestDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());
        return produto;
    }
}
