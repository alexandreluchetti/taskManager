package br.com.lucchetta.task_manager.entrypoint.lista.dto;

import br.com.lucchetta.task_manager.core.entity.Lista;

public record ListaSemItensDto(

        String nome

) {

    public Lista toObject() {
        return new Lista(this.nome);
    }

}
