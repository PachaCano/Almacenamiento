package pc.Historico.persistencia;

import pc.Historico.entities.Historico;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

   List<Historico> findAllByfechaHoraCreacion(Timestamp fechaHora);
    
}
