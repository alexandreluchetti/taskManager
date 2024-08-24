package br.com.lucchetta.task_manager.entrypoint.item.dto;

import br.com.lucchetta.task_manager.core.item.entity.Item;
import br.com.lucchetta.task_manager.core.lista.entity.Lista;

public record ItemDto(

        String titulo,
        boolean estado,
        boolean prioridade

) {

    public Item toObject(Lista lista) {
        return new Item(
                this.titulo,
                this.estado,
                this.prioridade,
                lista
        );
    }

    public Item toObject() {
        return new Item(
                this.titulo,
                this.estado,
                this.prioridade,
                null
        );
    }
}
