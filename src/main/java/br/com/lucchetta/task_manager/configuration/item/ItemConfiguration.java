package br.com.lucchetta.task_manager.configuration.item;

import br.com.lucchetta.task_manager.core.item.useCase.ItemUseCase;
import br.com.lucchetta.task_manager.core.item.useCase.impl.ItemUseCaseImpl;
import br.com.lucchetta.task_manager.dataprovider.item.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfiguration {

    @Bean
    public ItemUseCase loadItemUseCase(ItemRepository itemRepository) {
        return new ItemUseCaseImpl(itemRepository);
    }
}
