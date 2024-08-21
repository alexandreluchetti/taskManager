package br.com.lucchetta.task_manager.controller;

import br.com.lucchetta.task_manager.model.Item;
import br.com.lucchetta.task_manager.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista/{listaId}/item")
@Tag(name = "Itens", description = "Operações relacionadas aos itens")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @Operation(summary = "Obter todos os itens", description = "Retorna todos os itens disponíveis da lista com o id informado")
    public List<Item> getAllItems(@PathVariable Long listaId) {
        return itemService.findAllByListaId(listaId);
    }

    @PostMapping
    @Operation(summary = "Criar um novo item", description = "Retornao item criado")
    public Item createItem(@PathVariable Long listaId, @RequestBody Item item) {
        //TODO Validar listaId e associar item com a lista correta
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um item", description = "Retorna o item, de acordo com o id informado, com os dados atualizados")
    public ResponseEntity<Item> updateItem(@PathVariable Long listaId, @PathVariable Long id, @RequestBody Item item) {
        //TODO Validar listaId e ID do item
        item.setId(id);
        return ResponseEntity.ok(itemService.save(item));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um item", description = "Deleta o item de acordo com o id informado")
    public ResponseEntity<Void> deleteItem(@PathVariable Long listaId, @PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
