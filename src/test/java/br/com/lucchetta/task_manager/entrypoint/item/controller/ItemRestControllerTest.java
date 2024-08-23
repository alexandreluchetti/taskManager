package br.com.lucchetta.task_manager.entrypoint.item.controller;

import br.com.lucchetta.task_manager.core.entity.Item;
import br.com.lucchetta.task_manager.entrypoint.item.dto.ItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ItemRestControllerTest {

    private static final Long LISTA_ID = 1L;
    private static final ItemDto ITEM_DTO = new ItemDto(("TestListaId" + LISTA_ID), true, false);
    private static final ItemDto UPDATED_ITEM_DTO = new ItemDto(("UpdatedTestListaId" + LISTA_ID), false, true);

    private final ConsultAllItemsRestController consultAllItemsRestController;
    private final ConsultOneItemRestController consultOneItemRestController;
    private final CreateItemRestController createItemRestController;
    private final DeleteItemRestController deleteItemRestController;
    private final UpdateItemRestController updateItemRestController;

    @Autowired
    public ItemRestControllerTest(
            ConsultAllItemsRestController consultAllItemsRestController,
            ConsultOneItemRestController consultOneItemRestController,
            CreateItemRestController createItemRestController,
            DeleteItemRestController deleteItemRestController,
            UpdateItemRestController updateItemRestController
    ) {
        this.consultAllItemsRestController = consultAllItemsRestController;
        this.consultOneItemRestController = consultOneItemRestController;
        this.createItemRestController = createItemRestController;
        this.deleteItemRestController = deleteItemRestController;
        this.updateItemRestController = updateItemRestController;
    }

    @Test
    public void assertListItemsNotNull() {
        List<Item> items = consultAllItemsRestController.getAllItems(LISTA_ID);
        Assertions.assertNotNull(items);
    }

    @Test
    public void assertItemNotNullAndEquals() {
        List<Item> items = consultAllItemsRestController.getAllItems(LISTA_ID);
        items.forEach(itemFromList -> {
            Item item = consultOneItemRestController.getItem(LISTA_ID, itemFromList.getId());
            Assertions.assertNotNull(item);
            Assertions.assertEquals(itemFromList.getId(), item.getId());
            Assertions.assertEquals(itemFromList.isEstado(), item.isEstado());
            Assertions.assertEquals(itemFromList.isPrioridade(), item.isPrioridade());
        });
    }

    @Test
    public void assertCreateAndDeleteItem() {
        Item item = createItemRestController.createItem(LISTA_ID, ITEM_DTO);
        Assertions.assertNotNull(item);
        Assertions.assertEquals(LISTA_ID, item.getLista().getId());
        Assertions.assertEquals(ITEM_DTO.estado(), item.isEstado());
        Assertions.assertEquals(ITEM_DTO.prioridade(), item.isPrioridade());

        deleteItemRestController.deleteItem(LISTA_ID, item.getId());
    }

    @Test
    public void assertCreateUpdateAndDeleteItem() {
        Item item = assertCreateAndCompareItems(ITEM_DTO);
        Item updatedItem = assertUpdateAndCompareItems(item, UPDATED_ITEM_DTO);
        deleteItemRestController.deleteItem(LISTA_ID, updatedItem.getId());
    }

    private Item assertCreateAndCompareItems(ItemDto itemDto) {
        Item item = createItemRestController.createItem(LISTA_ID, itemDto);
        Assertions.assertNotNull(item);
        Assertions.assertEquals(LISTA_ID, item.getLista().getId());
        Assertions.assertEquals(itemDto.estado(), item.isEstado());
        Assertions.assertEquals(itemDto.prioridade(), item.isPrioridade());
        return item;
    }

    private Item assertUpdateAndCompareItems(Item item, ItemDto updatedItemDto) {
        Item updatedItem = updateItemRestController.updateItem(LISTA_ID, item.getId(), updatedItemDto).getBody();
        Assertions.assertNotNull(updatedItem);
        Assertions.assertEquals(LISTA_ID, updatedItem.getLista().getId());
        Assertions.assertEquals(updatedItemDto.estado(), updatedItem.isEstado());
        Assertions.assertEquals(updatedItemDto.prioridade(), updatedItem.isPrioridade());
        return updatedItem;
    }
}
