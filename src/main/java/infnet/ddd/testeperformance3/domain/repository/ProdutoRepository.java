package infnet.ddd.testeperformance3.domain.repository;

import infnet.ddd.testeperformance3.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
