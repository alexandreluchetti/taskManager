package br.com.lucchetta.task_manager.core.useCase.item.impl;

import br.com.lucchetta.task_manager.core.useCase.item.ItemUseCase;
import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.configuration.exception.OperationException;
import br.com.lucchetta.task_manager.core.entity.Item;
import br.com.lucchetta.task_manager.dataprovider.item.ItemRepository;

import java.util.List;

public class ItemUseCaseImpl implements ItemUseCase {

    private final ItemRepository itemRepository;

    public ItemUseCaseImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAllByListaId(Long listaId) {
        try {
            return itemRepository.findAll().stream()
                    .filter(item -> item.getLista().getId().equals(listaId))
                    .toList();
        } catch (Exception e) {
            throw new NoneResultException("Nenhum item encontrado.");
        }
    }

    @Override
    public Item save(Item item) {
        try {
            return itemRepository.save(item);
        } catch (Exception e) {
            throw new OperationException("Impossivel salvar item");
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            throw new OperationException("Nenhum item deletado.");
        }
    }
}
