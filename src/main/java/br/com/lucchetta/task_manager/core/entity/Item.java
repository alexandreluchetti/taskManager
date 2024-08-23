package br.com.lucchetta.task_manager.core.entity;

import br.com.lucchetta.task_manager.entrypoint.item.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private boolean estado;

    @Column(nullable = false)
    private boolean prioridade;

    @ManyToOne
    @JoinColumn(name = "lista_id")
    @JsonBackReference
    private Lista lista;

    public Item(String titulo, boolean estado, boolean prioridade, Lista lista) {
        this.titulo = titulo;
        this.estado = estado;
        this.prioridade = prioridade;
        this.lista = lista;
    }

    public Item() {
    }

    public void update(ItemDto dto, Lista lista) {
        this.titulo = dto.titulo();
        this.estado = dto.estado();
        this.prioridade = dto.prioridade();
        this.lista = lista;
    }
}
