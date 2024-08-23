package br.com.lucchetta.task_manager.core.useCase.item;

import br.com.lucchetta.task_manager.core.entity.Item;

import java.util.List;

public interface ItemUseCase {

    List<Item> findAllByListaId(Long listaId);

    Item save(Item item);

    void deleteById(Long id);
}
