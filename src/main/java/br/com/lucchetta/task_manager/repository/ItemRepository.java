package br.com.lucchetta.task_manager.repository;

import br.com.lucchetta.task_manager.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
