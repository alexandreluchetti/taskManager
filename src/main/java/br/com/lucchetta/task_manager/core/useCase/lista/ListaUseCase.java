package br.com.lucchetta.task_manager.core.useCase.lista;

import br.com.lucchetta.task_manager.core.entity.Lista;

import java.util.List;

public interface ListaUseCase {

    List<Lista> findAll();

    Lista findById(Long id);

    Lista save(Lista lista);

    void deleteById(Long id);
}
