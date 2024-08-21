package br.com.lucchetta.task_manager.model;

import br.com.lucchetta.task_manager.controller.item.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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
    private Lista lista;

    public Item(String titulo, boolean estado, boolean prioridade, Lista lista) {
        this.titulo = titulo;
        this.estado = estado;
        this.prioridade = prioridade;
        this.lista = lista;
    }

    public void update(ItemDto dto, Lista lista) {
        this.titulo = dto.titulo();
        this.estado = dto.estado();
        this.prioridade = dto.prioridade();
        this.lista = lista;
    }
}
