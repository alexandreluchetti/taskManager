package br.com.lucchetta.task_manager.controller.lista.controller;

import br.com.lucchetta.task_manager.controller.lista.dto.ListaDto;
import br.com.lucchetta.task_manager.controller.lista.dto.ListaSemItensDto;
import br.com.lucchetta.task_manager.model.Lista;
import br.com.lucchetta.task_manager.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista")
@Tag(name = "Listas", description = "Operações relacionadas às listas")
public class ListaController {

    private final ListaService listaService;

    @Autowired
    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @GetMapping
    @Operation(summary = "Obter todas as listas", description = "Retorna todas as listas disponíveis")
    public List<Lista> getAllListas() {
        return listaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter lista pelo id", description = "Retorna uma lista de acordo com o id informado")
    public ResponseEntity<Lista> getListaById(@PathVariable Long id) {
        Lista lista = listaService.findById(id);
        if (lista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Cria uma lista", description = "Retorna a lista criada")
    public Lista createLista(@RequestBody ListaDto dto) {
        Lista lista = dto.toObject();
        return listaService.save(lista);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza uma lista",
            description = """
                    Retorna a lista, de acordo com o id informado, com os dados atualizados.
                    Obs.: Para alterar os itens de uma lista, utiliza a operacao PUT: /api/lista/{listaId}/item/{id}
            """
    )
    public ResponseEntity<Lista> updateLista(@PathVariable Long id, @RequestBody ListaSemItensDto dto) {
        Lista lista = listaService.findById(id);
        if (lista == null) {
            return ResponseEntity.notFound().build();
        }

        lista.setNome(dto.nome());
        return ResponseEntity.ok(listaService.save(lista));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma lista", description = "Deleta a lista de acordo com o id informado")
    public ResponseEntity<Void> deleteLista(@PathVariable Long id) {
        listaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
