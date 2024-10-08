package br.com.lucchetta.task_manager.core.lista.entity;

import br.com.lucchetta.task_manager.core.item.entity.Item;
import br.com.lucchetta.task_manager.entrypoint.lista.dto.ListaDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> itens;

    public Lista(String nome, List<Item> itens) {
        this.nome = nome;
        this.itens = itens;
    }

    public Lista(String nome) {
        this.nome = nome;
    }

    public Lista() {
    }

    public void update(ListaDto dto) {
        this.nome = dto.nome();
        this.itens = dto.toObject().getItens();
    }
}

