package br.com.lucchetta.task_manager.model;

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
}
