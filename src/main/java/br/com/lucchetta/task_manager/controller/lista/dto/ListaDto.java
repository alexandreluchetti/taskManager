package br.com.lucchetta.task_manager.controller.lista.dto;

import br.com.lucchetta.task_manager.controller.item.dto.ItemDto;
import br.com.lucchetta.task_manager.model.Item;
import br.com.lucchetta.task_manager.model.Lista;

import java.util.List;

public record ListaDto(

        String nome,

        List<ItemDto> itemDtos

) {

    public Lista toObject() {
        List<Item> itens = this.itemDtos.stream().map(ItemDto::toObject).toList();
        Lista lista = new Lista(this.nome, itens);
        itens.forEach(item -> item.setLista(lista));
        return lista;
    }

}
