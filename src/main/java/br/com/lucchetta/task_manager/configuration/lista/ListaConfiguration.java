package br.com.lucchetta.task_manager.configuration.lista;

import br.com.lucchetta.task_manager.core.lista.useCase.ListaUseCase;
import br.com.lucchetta.task_manager.dataprovider.lista.ListaRepository;
import br.com.lucchetta.task_manager.core.lista.useCase.impl.ListaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaConfiguration {

    @Bean
    public ListaUseCase loadListaUseCase(ListaRepository listaRepository) {
        return new ListaUseCaseImpl(listaRepository);
    }
}
