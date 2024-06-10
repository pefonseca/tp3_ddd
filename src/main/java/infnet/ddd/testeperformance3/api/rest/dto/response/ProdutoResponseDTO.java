package infnet.ddd.testeperformance3.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import infnet.ddd.testeperformance3.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private double preco;
    private int quantidade;
    @JsonIgnore
    private List<Pedido> pedidos;

}
