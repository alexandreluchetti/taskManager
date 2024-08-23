package br.com.lucchetta.task_manager.entrypoint.lista.controller;

import br.com.lucchetta.task_manager.core.useCase.lista.ListaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.lucchetta.task_manager.entrypoint.lista.ListaRequestPath.*;

@RestController
@RequestMapping(LISTA_PATH)
@Tag(name = LISTA_TAG, description = LISTA_DESCRIPTION)
public class DeleteListRestController {

    private final ListaUseCase listaUseCase;

    @Autowired
    public DeleteListRestController(ListaUseCase listaUseCase) {
        this.listaUseCase = listaUseCase;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma lista", description = "Deleta a lista de acordo com o id informado")
    public ResponseEntity<Void> deleteLista(@PathVariable Long id) {
        listaUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
