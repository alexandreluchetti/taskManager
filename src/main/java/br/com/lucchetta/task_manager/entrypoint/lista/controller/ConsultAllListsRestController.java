package br.com.lucchetta.task_manager.entrypoint.lista.controller;

import br.com.lucchetta.task_manager.core.lista.entity.Lista;
import br.com.lucchetta.task_manager.core.lista.useCase.ListaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.lucchetta.task_manager.entrypoint.lista.ListaRequestPath.*;

@RestController
@RequestMapping(LISTA_PATH)
@Tag(name = LISTA_TAG, description = LISTA_DESCRIPTION)
public class ConsultAllListsRestController {

    private final ListaUseCase listaUseCase;

    @Autowired
    public ConsultAllListsRestController(ListaUseCase listaUseCase) {
        this.listaUseCase = listaUseCase;
    }

    @GetMapping
    @Operation(summary = "Obter todas as listas", description = "Retorna todas as listas disponíveis")
    public List<Lista> getAllListas() {
        return listaUseCase.findAll();
    }
}
