package br.com.lucchetta.task_manager.repository;

import br.com.lucchetta.task_manager.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaRepository extends JpaRepository<Lista, Long> {
}
