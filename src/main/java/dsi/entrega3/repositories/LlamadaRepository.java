package dsi.entrega3.repositories;

import dsi.entrega3.models.Llamada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LlamadaRepository extends JpaRepository<Llamada, Long> {
}
