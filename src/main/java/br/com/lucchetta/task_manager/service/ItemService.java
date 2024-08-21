package br.com.lucchetta.task_manager.service;

import br.com.lucchetta.task_manager.model.Item;
import br.com.lucchetta.task_manager.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAllByListaId(Long listaId) {
        return itemRepository.findAll().stream()
                .filter(item -> item.getLista().getId().equals(listaId))
                .toList();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
