package dsi.entrega3.repositories;

import dsi.entrega3.models.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
}
