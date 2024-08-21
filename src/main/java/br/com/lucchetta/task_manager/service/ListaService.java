package br.com.lucchetta.task_manager.service;

import br.com.lucchetta.task_manager.model.Lista;
import br.com.lucchetta.task_manager.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    private final ListaRepository listaRepository;

    @Autowired
    public ListaService(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    public List<Lista> findAll() {
        return listaRepository.findAll();
    }

    public Lista findById(Long id) {
        return listaRepository.findById(id).orElse(null);
    }

    public Lista save(Lista lista) {
        return listaRepository.save(lista);
    }

    public void deleteById(Long id) {
        listaRepository.deleteById(id);
    }
}
