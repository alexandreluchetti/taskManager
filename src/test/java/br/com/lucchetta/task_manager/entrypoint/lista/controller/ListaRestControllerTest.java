package br.com.lucchetta.task_manager.entrypoint.lista.controller;

import br.com.lucchetta.task_manager.core.entity.Lista;
import br.com.lucchetta.task_manager.entrypoint.lista.dto.ListaDto;
import br.com.lucchetta.task_manager.entrypoint.lista.dto.ListaSemItensDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ListaRestControllerTest {

    private static final ListaDto LISTA_DTO = new ListaDto("TestLista", List.of());
    private static final ListaSemItensDto LISTA_NO_ITEMS_DTO = new ListaSemItensDto("TestLista");

    private final ConsultAllListsRestController consultAllListsRestController;
    private final ConsultOneListRestController consultOneListRestController;
    private final CreateListRestController createListRestController;
    private final DeleteListRestController deleteListRestController;
    private final UpdateListRestController updateListRestController;

    @Autowired
    public ListaRestControllerTest(
            ConsultAllListsRestController consultAllListsRestController,
            ConsultOneListRestController consultOneListRestController,
            CreateListRestController createListRestController,
            DeleteListRestController deleteListRestController,
            UpdateListRestController updateListRestController
    ) {
        this.consultAllListsRestController = consultAllListsRestController;
        this.consultOneListRestController = consultOneListRestController;
        this.createListRestController = createListRestController;
        this.deleteListRestController = deleteListRestController;
        this.updateListRestController = updateListRestController;
    }

    @Test
    public void assertListsNotNull() {
        List<Lista> lists = consultAllListsRestController.getAllListas();
        Assertions.assertNotNull(lists);
    }

    @Test
    public void assertListNotNullAndEquals() {
        List<Lista> lists = consultAllListsRestController.getAllListas();
        lists.forEach(listFromList -> {
            Lista list = consultOneListRestController.getListaById(listFromList.getId()).getBody();
            Assertions.assertNotNull(list);
            Assertions.assertEquals(listFromList.getId(), list.getId());
            Assertions.assertEquals(listFromList.getNome(), list.getNome());
        });
    }

    @Test
    public void assertCreateAndDeleteLista() {
        Lista lista = createListRestController.createLista(LISTA_DTO);
        Assertions.assertNotNull(lista);
        Assertions.assertEquals(LISTA_DTO.nome(), lista.getNome());
        Assertions.assertEquals(LISTA_DTO.itemDtos().size(), lista.getItens().size());

        deleteListRestController.deleteLista(lista.getId());
    }

    @Test
    public void assertCreateUpdateAndDeleteLista() {
        Lista lista = assertCreateAndCompareListas(LISTA_DTO);
        Lista updatedLista = assertUpdateAndCompareListas(lista, LISTA_NO_ITEMS_DTO);
        deleteListRestController.deleteLista(updatedLista.getId());
    }

    private Lista assertCreateAndCompareListas(ListaDto listaDto) {
        Lista lista = createListRestController.createLista(listaDto);
        Assertions.assertNotNull(lista);
        Assertions.assertEquals(listaDto.nome(), lista.getNome());
        Assertions.assertEquals(listaDto.itemDtos().size(), lista.getItens().size());
        return lista;
    }

    private Lista assertUpdateAndCompareListas(Lista lista, ListaSemItensDto listaDto) {
        Lista updatedLista = updateListRestController.updateLista(lista.getId(), listaDto).getBody();
        Assertions.assertNotNull(updatedLista);
        Assertions.assertEquals(listaDto.nome(), updatedLista.getNome());
        return updatedLista;
    }
}
