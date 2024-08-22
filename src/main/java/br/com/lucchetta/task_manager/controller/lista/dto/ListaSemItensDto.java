package br.com.lucchetta.task_manager.controller.lista.dto;

import br.com.lucchetta.task_manager.controller.item.dto.ItemDto;
import br.com.lucchetta.task_manager.model.Item;
import br.com.lucchetta.task_manager.model.Lista;

import java.util.List;

public record ListaSemItensDto(

        String nome

) {

    public Lista toObject() {
        return new Lista(this.nome);
    }

}
