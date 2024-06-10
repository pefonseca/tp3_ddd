package infnet.ddd.testeperformance3.api.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PedidoRequestDTO {

    @JsonIgnore
    private Long id;
    private Long produtoId;
    private int quantidade;
    private String cep;

}
