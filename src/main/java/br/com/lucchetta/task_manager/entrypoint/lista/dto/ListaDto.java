package br.com.lucchetta.task_manager.entrypoint.lista.dto;

import br.com.lucchetta.task_manager.entrypoint.item.dto.ItemDto;
import br.com.lucchetta.task_manager.core.entity.Item;
import br.com.lucchetta.task_manager.core.entity.Lista;

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
