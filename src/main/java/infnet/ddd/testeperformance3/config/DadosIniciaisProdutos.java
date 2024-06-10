package infnet.ddd.testeperformance3.config;

import infnet.ddd.testeperformance3.domain.model.Produto;
import infnet.ddd.testeperformance3.domain.repository.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DadosIniciaisProdutos {

    private final ProdutoRepository produtoRepository;

    @PostConstruct
    public void popularBaseDeDados() {
        if (produtoRepository.count() == 0) {
            Produto produto1 = Produto.builder().nome("Produto 1").preco(20).quantidade(10).build();
            Produto produto2 = Produto.builder().nome("Produto 2").preco(10).quantidade(2).build();
            Produto produto3 = Produto.builder().nome("Produto 3").preco(30).quantidade(5).build();

            produtoRepository.saveAll(List.of(produto1, produto2, produto3));

            System.out.println("Base de dados de produtos populada com sucesso.");
        } else {
            System.out.println("Base de dados de produtos já populada.");
        }
    }

}
