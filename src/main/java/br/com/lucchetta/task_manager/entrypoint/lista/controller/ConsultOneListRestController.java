package br.com.lucchetta.task_manager.entrypoint.lista.controller;

import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.core.entity.Lista;
import br.com.lucchetta.task_manager.core.useCase.lista.ListaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.lucchetta.task_manager.entrypoint.lista.ListaRequestPath.*;

@RestController
@RequestMapping(LISTA_PATH)
@Tag(name = LISTA_TAG, description = LISTA_DESCRIPTION)
public class ConsultOneListRestController {

    private final ListaUseCase listaUseCase;

    @Autowired
    public ConsultOneListRestController(ListaUseCase listaUseCase) {
        this.listaUseCase = listaUseCase;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter lista pelo id", description = "Retorna uma lista de acordo com o id informado")
    public ResponseEntity<Lista> getListaById(@PathVariable Long id) {
        Lista lista = listaUseCase.findById(id);
        if (lista == null) {
            throw new NoneResultException("Nenhuma lista encontrada com o id informado.");
        }
        return ResponseEntity.ok(lista);
    }
}
