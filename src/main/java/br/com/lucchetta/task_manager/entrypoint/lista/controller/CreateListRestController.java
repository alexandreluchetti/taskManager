package br.com.lucchetta.task_manager.entrypoint.lista.controller;

import br.com.lucchetta.task_manager.core.lista.entity.Lista;
import br.com.lucchetta.task_manager.core.lista.useCase.ListaUseCase;
import br.com.lucchetta.task_manager.entrypoint.lista.dto.ListaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.lucchetta.task_manager.entrypoint.lista.ListaRequestPath.*;

@RestController
@RequestMapping(LISTA_PATH)
@Tag(name = LISTA_TAG, description = LISTA_DESCRIPTION)
public class CreateListRestController {

    private final ListaUseCase listaUseCase;

    @Autowired
    public CreateListRestController(ListaUseCase listaUseCase) {
        this.listaUseCase = listaUseCase;
    }

    @PostMapping
    @Operation(summary = "Cria uma lista", description = "Retorna a lista criada")
    public Lista createLista(@RequestBody ListaDto dto) {
        Lista lista = dto.toObject();
        return listaUseCase.save(lista);
    }
}
