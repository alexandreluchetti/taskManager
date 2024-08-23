package br.com.lucchetta.task_manager.entrypoint.item.controller;

import br.com.lucchetta.task_manager.core.useCase.item.ItemUseCase;
import br.com.lucchetta.task_manager.core.useCase.lista.ListaUseCase;
import br.com.lucchetta.task_manager.entrypoint.item.dto.ItemDto;
import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.core.entity.Item;
import br.com.lucchetta.task_manager.core.entity.Lista;
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

    private final ItemUseCase itemUseCase;
    private final ListaUseCase listaUseCase;

    @Autowired
    public ItemController(ItemUseCase itemUseCase, ListaUseCase listaUseCase) {
        this.itemUseCase = itemUseCase;
        this.listaUseCase = listaUseCase;
    }

    @GetMapping
    @Operation(summary = "Obter todos os itens", description = "Retorna todos os itens disponíveis da lista com o id informado")
    public List<Item> getAllItems(@PathVariable Long listaId) {
        return itemUseCase.findAllByListaId(listaId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um item", description = "Retorna o item de acordo com o id informado")
    public Item getItem(@PathVariable Long listaId, @PathVariable Long id) {
        Item item = getItemFromLista(listaId, id);
        if (item == null) throw new NoneResultException("Nenhum item encontrado");

        return item;
    }

    @PostMapping
    @Operation(summary = "Criar um novo item", description = "Retornao item criado")
    public Item createItem(@PathVariable Long listaId, @RequestBody ItemDto dto) {
        Lista lista = listaUseCase.findById(listaId);
        if (lista == null) throw new NoneResultException("Nenhuma lista encontrada com o id informado");

        Item item = dto.toObject(lista);
        return itemUseCase.save(item);
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um item", description = "Deleta o item de acordo com o id informado")
    public ResponseEntity<Void> deleteItem(@PathVariable Long listaId, @PathVariable Long id) {
        itemUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Item getItemFromLista(Long listaId, Long id) {
        List<Item> itens = itemUseCase.findAllByListaId(listaId);
        for (Item item : itens) {
            if (Objects.equals(item.getId(), id)) return item;
        }
        return null;
    }
}
