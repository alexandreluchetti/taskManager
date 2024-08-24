package br.com.lucchetta.task_manager.entrypoint.lista.controller;

import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.core.lista.entity.Lista;
import br.com.lucchetta.task_manager.core.lista.useCase.ListaUseCase;
import br.com.lucchetta.task_manager.entrypoint.lista.dto.ListaSemItensDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.lucchetta.task_manager.entrypoint.lista.ListaRequestPath.*;

@RestController
@RequestMapping(LISTA_PATH)
@Tag(name = LISTA_TAG, description = LISTA_DESCRIPTION)
public class UpdateListRestController {

    private final ListaUseCase listaUseCase;

    @Autowired
    public UpdateListRestController(ListaUseCase listaUseCase) {
        this.listaUseCase = listaUseCase;
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza uma lista",
            description = """
                    Retorna a lista, de acordo com o id informado, com os dados atualizados.
                    Obs.: Para alterar os itens de uma lista, utiliza a operacao PUT: /api/lista/{listaId}/item/{id}
            """
    )
    public ResponseEntity<Lista> updateLista(@PathVariable Long id, @RequestBody ListaSemItensDto dto) {
        Lista lista = listaUseCase.findById(id);
        if (lista == null) {
            throw new NoneResultException("Nenhuma lista encontrada com o id informado.");
        }

        lista.setNome(dto.nome());
        return ResponseEntity.ok(listaUseCase.save(lista));
    }
}
