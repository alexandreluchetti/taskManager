package br.com.lucchetta.task_manager.entrypoint.lista.controller;

import br.com.lucchetta.task_manager.core.useCase.lista.ListaUseCase;
import br.com.lucchetta.task_manager.entrypoint.lista.dto.ListaDto;
import br.com.lucchetta.task_manager.entrypoint.lista.dto.ListaSemItensDto;
import br.com.lucchetta.task_manager.configuration.exception.NoneResultException;
import br.com.lucchetta.task_manager.core.entity.Lista;
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

    private final ListaUseCase listaUseCase;

    @Autowired
    public ListaController(ListaUseCase listaUseCase) {
        this.listaUseCase = listaUseCase;
    }

    @GetMapping
    @Operation(summary = "Obter todas as listas", description = "Retorna todas as listas disponíveis")
    public List<Lista> getAllListas() {
        return listaUseCase.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter lista pelo id", description = "Retorna uma lista de acordo com o id informado")
    public ResponseEntity<Lista> getListaById(@PathVariable Long id) {
        Lista lista = listaUseCase.findById(id);
        if (lista == null) {
            throw new NoneResultException("Nenhuma lista encontrada com o id informado.");
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Cria uma lista", description = "Retorna a lista criada")
    public Lista createLista(@RequestBody ListaDto dto) {
        Lista lista = dto.toObject();
        return listaUseCase.save(lista);
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
        Lista lista = listaUseCase.findById(id);
        if (lista == null) {
            throw new NoneResultException("Nenhuma lista encontrada com o id informado.");
        }

        lista.setNome(dto.nome());
        return ResponseEntity.ok(listaUseCase.save(lista));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma lista", description = "Deleta a lista de acordo com o id informado")
    public ResponseEntity<Void> deleteLista(@PathVariable Long id) {
        listaUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
