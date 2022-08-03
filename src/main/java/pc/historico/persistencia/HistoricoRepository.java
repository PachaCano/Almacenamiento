package pc.historico.persistencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import pc.historico.entities.Historico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    Page<Historico> findAllByCategoriaOrderByFechaHoraCreacionDesc(String categoria, Pageable pageable);
    @Query(value = "select * from historico h order by h.fecha_hora_creacion DESC",
            countQuery = "select count(*) from historico",
            nativeQuery = true)
    Page<Historico> findAllOrderByFechaHoraCreacionDesc(Pageable pageable);
    Page<Historico> findAllByCategoriaAndSubCategoriaOrderByFechaHoraCreacionDesc(String categoria, String subCategoria, Pageable pageable);
    Historico findFirstByIdentificadorOrderByFechaHoraCreacionDesc(String identificador);

}
