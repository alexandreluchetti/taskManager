package br.com.lucchetta.task_manager.core.lista.useCase;

import br.com.lucchetta.task_manager.core.lista.entity.Lista;

import java.util.List;

public interface ListaUseCase {

    List<Lista> findAll();

    Lista findById(Long id);

    Lista save(Lista lista);

    void deleteById(Long id);
}
