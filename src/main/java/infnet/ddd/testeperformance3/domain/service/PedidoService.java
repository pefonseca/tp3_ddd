package infnet.ddd.testeperformance3.domain.service;

import infnet.ddd.testeperformance3.api.rest.dto.request.PedidoRequestDTO;
import infnet.ddd.testeperformance3.api.rest.dto.response.PedidoResponseDTO;
import infnet.ddd.testeperformance3.domain.model.Pedido;

import java.util.List;

public interface PedidoService {

    PedidoResponseDTO createPedido(PedidoRequestDTO pedido) throws Exception;
    PedidoResponseDTO getPedido(Long id);
    PedidoResponseDTO updatePedido(Long id, PedidoRequestDTO pedido) throws Exception;
    void deletePedido(Long id);
    List<PedidoResponseDTO> findAll();

}
