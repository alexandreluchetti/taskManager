package br.com.lucchetta.task_manager.dataprovider.lista;

import br.com.lucchetta.task_manager.core.entity.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaRepository extends JpaRepository<Lista, Long> {
}
