package br.com.lucchetta.task_manager.core.useCase.lista.impl;

import br.com.lucchetta.task_manager.core.useCase.lista.ListaUseCase;
import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.configuration.exception.OperationException;
import br.com.lucchetta.task_manager.core.entity.Lista;
import br.com.lucchetta.task_manager.dataprovider.lista.ListaRepository;

import java.util.List;

public class ListaUseCaseImpl implements ListaUseCase {

    private final ListaRepository listaRepository;

    public ListaUseCaseImpl(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    @Override
    public List<Lista> findAll() {
        try {
            return listaRepository.findAll();
        } catch (Exception e) {
            throw new NoneResultException("Nenhuma lista no banco de dados.");
        }
    }

    @Override
    public Lista findById(Long id) {
        try {
            return listaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new NoneResultException("Nenhuma lista encontrada com o id informado.");
        }
    }

    @Override
    public Lista save(Lista lista) {
        try {
            return listaRepository.save(lista);
        } catch (Exception e){
            throw new OperationException("Impossivel salvar lista.");
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            listaRepository.deleteById(id);
        } catch (Exception e) {
            throw new OperationException("Nenhuma lista deletada.");
        }
    }
}
