package br.com.lucchetta.task_manager.controller.item.controller;

import br.com.lucchetta.task_manager.controller.item.dto.ItemDto;
import br.com.lucchetta.task_manager.model.Item;
import br.com.lucchetta.task_manager.model.Lista;
import br.com.lucchetta.task_manager.service.ItemService;
import br.com.lucchetta.task_manager.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/lista/{listaId}/item")
@Tag(name = "Itens", description = "Operações relacionadas aos itens")
public class ItemController {

    private final ItemService itemService;
    private final ListaService listaService;

    @Autowired
    public ItemController(ItemService itemService, ListaService listaService) {
        this.itemService = itemService;
        this.listaService = listaService;
    }

    @GetMapping
    @Operation(summary = "Obter todos os itens", description = "Retorna todos os itens disponíveis da lista com o id informado")
    public List<Item> getAllItems(@PathVariable Long listaId) {
        return itemService.findAllByListaId(listaId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um item", description = "Retorna o item de acordo com o id informado")
    public Item getItem(@PathVariable Long listaId, @PathVariable Long id) {
        Item item = getItemFromLista(listaId, id);
        if (item == null) throw new RuntimeException("Nenhum item encontrado");
        return item;
    }

    @PostMapping
    @Operation(summary = "Criar um novo item", description = "Retornao item criado")
    public Item createItem(@PathVariable Long listaId, @RequestBody ItemDto dto) {
        Lista lista = listaService.findById(listaId);
        Item item = dto.toObject(lista);
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um item", description = "Retorna o item, de acordo com o id informado, com os dados atualizados")
    public ResponseEntity<Item> updateItem(@PathVariable Long listaId, @PathVariable Long id, @RequestBody ItemDto dto) {
        Item item = getItemFromLista(listaId, id);
        if (item == null) throw new RuntimeException("Nenhum item encontrado");

        Lista lista = listaService.findById(listaId);
        item.update(dto, lista);
        return ResponseEntity.ok(itemService.save(item));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um item", description = "Deleta o item de acordo com o id informado")
    public ResponseEntity<Void> deleteItem(@PathVariable Long listaId, @PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Item getItemFromLista(Long listaId, Long id) {
        List<Item> itens = itemService.findAllByListaId(listaId);
        for (Item item : itens) {
            if (Objects.equals(item.getId(), id)) return item;
        }
        return null;
    }
}
