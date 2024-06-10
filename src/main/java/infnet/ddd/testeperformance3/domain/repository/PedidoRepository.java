package infnet.ddd.testeperformance3.domain.repository;

import infnet.ddd.testeperformance3.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
