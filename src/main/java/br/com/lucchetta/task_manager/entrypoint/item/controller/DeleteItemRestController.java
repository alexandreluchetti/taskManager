package br.com.lucchetta.task_manager.entrypoint.item.controller;

import br.com.lucchetta.task_manager.core.item.useCase.ItemUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.lucchetta.task_manager.entrypoint.item.ItemRequestPath.*;

@RestController
@RequestMapping(ITEM_PATH)
@Tag(name = ITEM_TAG, description = ITEM_DESCRIPTION)
public class DeleteItemRestController {

    private final ItemUseCase itemUseCase;

    @Autowired
    public DeleteItemRestController(ItemUseCase itemUseCase) {
        this.itemUseCase = itemUseCase;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um item", description = "Deleta o item de acordo com o id informado")
    public ResponseEntity<Void> deleteItem(@PathVariable Long listaId, @PathVariable Long id) {
        itemUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

