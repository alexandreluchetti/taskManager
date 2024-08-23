package br.com.lucchetta.task_manager.entrypoint.item.controller;

import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.core.entity.Item;
import br.com.lucchetta.task_manager.core.entity.Lista;
import br.com.lucchetta.task_manager.core.useCase.item.ItemUseCase;
import br.com.lucchetta.task_manager.core.useCase.lista.ListaUseCase;
import br.com.lucchetta.task_manager.entrypoint.item.dto.ItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static br.com.lucchetta.task_manager.entrypoint.item.ItemRequestPath.*;

@RestController
@RequestMapping(ITEM_PATH)
@Tag(name = ITEM_TAG, description = ITEM_DESCRIPTION)
public class CreateItemRestController {

    private final ItemUseCase itemUseCase;
    private final ListaUseCase listaUseCase;

    @Autowired
    public CreateItemRestController(ItemUseCase itemUseCase, ListaUseCase listaUseCase) {
        this.itemUseCase = itemUseCase;
        this.listaUseCase = listaUseCase;
    }

    @PostMapping
    @Operation(summary = "Criar um novo item", description = "Retornao item criado")
    public Item createItem(@PathVariable Long listaId, @RequestBody ItemDto dto) {
        Lista lista = listaUseCase.findById(listaId);
        if (lista == null) throw new NoneResultException("Nenhuma lista encontrada com o id informado");

        Item item = dto.toObject(lista);
        return itemUseCase.save(item);
    }
}
