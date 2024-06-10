package infnet.ddd.testeperformance3.api.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private int quantidade;
    private ProdutoResponseDTO produto;
    private String cep;
    private String logradouro;
    private String bairro;

}
