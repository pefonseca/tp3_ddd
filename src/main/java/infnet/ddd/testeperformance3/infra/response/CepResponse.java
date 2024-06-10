package infnet.ddd.testeperformance3.infra.response;

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
public class CepResponse {

    private String localidade; // Cidade
    private String bairro;

}
