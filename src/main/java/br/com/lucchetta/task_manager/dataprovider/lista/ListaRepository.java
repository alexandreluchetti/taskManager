package br.com.lucchetta.task_manager.dataprovider.lista;

import br.com.lucchetta.task_manager.core.lista.entity.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaRepository extends JpaRepository<Lista, Long> {
}
