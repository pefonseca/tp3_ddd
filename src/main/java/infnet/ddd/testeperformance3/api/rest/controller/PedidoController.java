package infnet.ddd.testeperformance3.api.rest.controller;

import infnet.ddd.testeperformance3.api.rest.dto.request.PedidoRequestDTO;
import infnet.ddd.testeperformance3.api.rest.dto.response.PedidoResponseDTO;
import infnet.ddd.testeperformance3.domain.model.Pedido;
import infnet.ddd.testeperformance3.domain.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> getAllPedidos() {
        List<PedidoResponseDTO> pedidos = pedidoService.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO createPedido(@Valid @RequestBody PedidoRequestDTO pedidoResponseDTO) throws Exception {
        return pedidoService.createPedido(pedidoResponseDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponseDTO getPedido(@PathVariable Long id) {
        return pedidoService.getPedido(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponseDTO updatePedido(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedidoResponseDTO) throws Exception {
        return pedidoService.updatePedido(id, pedidoResponseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
    }
}
