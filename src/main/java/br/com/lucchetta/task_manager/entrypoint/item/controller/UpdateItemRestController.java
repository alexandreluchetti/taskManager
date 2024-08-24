package br.com.lucchetta.task_manager.entrypoint.item.controller;

import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.core.item.entity.Item;
import br.com.lucchetta.task_manager.core.lista.entity.Lista;
import br.com.lucchetta.task_manager.core.item.useCase.ItemUseCase;
import br.com.lucchetta.task_manager.core.lista.useCase.ListaUseCase;
import br.com.lucchetta.task_manager.entrypoint.item.dto.ItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static br.com.lucchetta.task_manager.entrypoint.item.ItemRequestPath.*;

@RestController
@RequestMapping(ITEM_PATH)
@Tag(name = ITEM_TAG, description = ITEM_DESCRIPTION)
public class UpdateItemRestController {

    private final ItemUseCase itemUseCase;
    private final ListaUseCase listaUseCase;

    @Autowired
    public UpdateItemRestController(ItemUseCase itemUseCase, ListaUseCase listaUseCase) {
        this.itemUseCase = itemUseCase;
        this.listaUseCase = listaUseCase;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um item", description = "Retorna o item, de acordo com o id informado, com os dados atualizados")
    public ResponseEntity<Item> updateItem(@PathVariable Long listaId, @PathVariable Long id, @RequestBody ItemDto dto) {
        Item item = getItemFromLista(listaId, id);
        if (item == null) throw new NoneResultException("Nenhum item encontrado com o id informado");

        Lista lista = listaUseCase.findById(listaId);
        item.update(dto, lista);
        return ResponseEntity.ok(itemUseCase.save(item));
    }

    private Item getItemFromLista(Long listaId, Long id) {
        List<Item> itens = itemUseCase.findAllByListaId(listaId);
        for (Item item : itens) {
            if (Objects.equals(item.getId(), id)) return item;
        }
        return null;
    }
}
