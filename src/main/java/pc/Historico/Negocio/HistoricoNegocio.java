package pc.Historico.Negocio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pc.Historico.entities.Historico;
import pc.Historico.Negocio.excepciones.NegocioExcepcion;
import pc.Historico.persistencia.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class HistoricoNegocio implements IHistorioNegocio{

    private final HistoricoRepository historicoDAO;

    @Override
    public List<Historico> listado() throws NegocioExcepcion {
        try {
            return historicoDAO.findAll();
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }
    @Override
    public List<Historico> listadoPorFechaHora(Timestamp fechaHora) throws NegocioExcepcion {
        try {
            return historicoDAO.findAllByfechaHoraCreacion(fechaHora);
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }
}
