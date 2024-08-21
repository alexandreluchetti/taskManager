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
        try {
            return listaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Nenhuma lista no banco de dados.");
        }
    }

    public Lista findById(Long id) {
        try {
            return listaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Nenhuma lista encontrada com o id informado.");
        }
    }

    public Lista save(Lista lista) {
        try {
            return listaRepository.save(lista);
        } catch (Exception e){
            throw new RuntimeException("Impossivel salvar lista.");
        }
    }

    public void deleteById(Long id) {
        try {
            listaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nenhuma lista deletada.");
        }
    }
}
