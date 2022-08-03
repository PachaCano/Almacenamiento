package pc.historico.persistencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pc.historico.entities.Historico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

   //List<Historico> findAllByfechaHoraCreacion(Date fechaHora);

    Page<Historico> findAllByCategoriaOrderByFechaHoraCreacionDesc(String categoria, Pageable pageable);
    Page<Historico> findAllByCategoriaAndSubCategoriaOrderByFechaHoraCreacionDesc(String categoria, String subCategoria, Pageable pageable);
    Historico findFirstByIdentificadorOrderByFechaHoraCreacionDesc(String identificador);

}
