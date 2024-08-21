package br.com.lucchetta.task_manager.controller;

import br.com.lucchetta.task_manager.model.Lista;
import br.com.lucchetta.task_manager.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista")
public class ListaController {

    private final ListaService listaService;

    @Autowired
    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @GetMapping
    public List<Lista> getAllListas() {
        return listaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista> getListaById(@PathVariable Long id) {
        Lista lista = listaService.findById(id);
        if (lista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public Lista createLista(@RequestBody Lista lista) {
        return listaService.save(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lista> updateLista(@PathVariable Long id, @RequestBody Lista lista) {
        if (listaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        lista.setId(id);
        return ResponseEntity.ok(listaService.save(lista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLista(@PathVariable Long id) {
        listaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
