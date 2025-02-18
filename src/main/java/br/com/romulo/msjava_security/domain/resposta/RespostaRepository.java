package br.com.romulo.msjava_security.domain.resposta;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findByTopicoId(Long id);
}