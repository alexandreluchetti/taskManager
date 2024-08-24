package br.com.lucchetta.task_manager.entrypoint.lista.dto;

import br.com.lucchetta.task_manager.core.lista.entity.Lista;

public record ListaSemItensDto(

        String nome

) {

    public Lista toObject() {
        return new Lista(this.nome);
    }

}
