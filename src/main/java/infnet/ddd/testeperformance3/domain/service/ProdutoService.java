package infnet.ddd.testeperformance3.domain.service;

import infnet.ddd.testeperformance3.api.rest.dto.request.ProdutoRequestDTO;
import infnet.ddd.testeperformance3.api.rest.dto.response.ProdutoResponseDTO;
import infnet.ddd.testeperformance3.domain.model.Produto;

import java.util.List;

public interface ProdutoService {

    List<ProdutoResponseDTO> findAll();
    Produto findById(Long id);
    Produto create(ProdutoRequestDTO produto);
    Produto update(Long id, ProdutoRequestDTO produto);
    void delete(Long id);
    boolean verificarDisponibilidade(Produto produto, int quantidade) throws Exception;

}
