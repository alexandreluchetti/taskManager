package br.com.lucchetta.task_manager.entrypoint.item.controller;

import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
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
import java.util.Objects;

import static br.com.lucchetta.task_manager.entrypoint.item.ItemRequestPath.*;

@RestController
@RequestMapping(ITEM_PATH)
@Tag(name = ITEM_TAG, description = ITEM_DESCRIPTION)
public class ConsultOneItemRestController {

    private final ItemUseCase itemUseCase;

    @Autowired
    public ConsultOneItemRestController(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um item", description = "Retorna o item de acordo com o id informado")
    public Item getItem(@PathVariable Long listaId, @PathVariable Long id) {
        Item item = getItemFromLista(listaId, id);
        if (item == null) throw new NoneResultException("Nenhum item encontrado");

        return item;
    }

    private Item getItemFromLista(Long listaId, Long id) {
        List<Item> itens = itemUseCase.findAllByListaId(listaId);
        for (Item item : itens) {
            if (Objects.equals(item.getId(), id)) return item;
        }
        return null;
    }
}
