package br.com.lucchetta.task_manager.entrypoint.item.controller;

import br.com.lucchetta.task_manager.core.entity.Item;
import br.com.lucchetta.task_manager.core.useCase.item.ItemUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.lucchetta.task_manager.entrypoint.item.ItemRequestPath.*;

@RestController
@RequestMapping(ITEM_PATH)
@Tag(name = ITEM_TAG, description = ITEM_DESCRIPTION)
public class ConsultAllItemsRestController {

    private final ItemUseCase itemUseCase;

    @Autowired
    public ConsultAllItemsRestController(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    @GetMapping
    @Operation(summary = "Obter todos os itens", description = "Retorna todos os itens disponíveis da lista com o id informado")
    public List<Item> getAllItems(@PathVariable Long listaId) {
        return itemUseCase.findAllByListaId(listaId);
    }
}
