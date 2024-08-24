package br.com.lucchetta.task_manager.dataprovider.item;

import br.com.lucchetta.task_manager.core.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
