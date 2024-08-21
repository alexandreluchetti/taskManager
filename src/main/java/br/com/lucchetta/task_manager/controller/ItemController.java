package br.com.lucchetta.task_manager.controller;

import br.com.lucchetta.task_manager.model.Item;
import br.com.lucchetta.task_manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista/{listaId}/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems(@PathVariable Long listaId) {
        return itemService.findAllByListaId(listaId);
    }

    @PostMapping
    public Item createItem(@PathVariable Long listaId, @RequestBody Item item) {
        //TODO Validar listaId e associar item com a lista correta
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long listaId, @PathVariable Long id, @RequestBody Item item) {
        //TODO Validar listaId e ID do item
        item.setId(id);
        return ResponseEntity.ok(itemService.save(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long listaId, @PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
