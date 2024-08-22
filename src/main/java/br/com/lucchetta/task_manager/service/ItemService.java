package br.com.lucchetta.task_manager.service;

import br.com.lucchetta.task_manager.exception.NoneResultException;
import br.com.lucchetta.task_manager.exception.OperationException;
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
        try {
            return itemRepository.findAll().stream()
                    .filter(item -> item.getLista().getId().equals(listaId))
                    .toList();
        } catch (Exception e) {
            throw new NoneResultException("Nenhum item encontrado.");
        }
    }

    public Item save(Item item) {
        try {
            return itemRepository.save(item);
        } catch (Exception e) {
            throw new OperationException("Impossivel salvar item");
        }
    }

    public void deleteById(Long id) {
        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            throw new OperationException("Nenhum item deletado.");
        }
    }
}
