package pc.historico.persistencia;

import pc.historico.entities.Historico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

   //List<Historico> findAllByfechaHoraCreacion(Date fechaHora);
    
}
