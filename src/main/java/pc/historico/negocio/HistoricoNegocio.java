package pc.historico.negocio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pc.historico.entities.Historico;
import pc.historico.entities.HistoricoDTO;
import pc.historico.negocio.excepciones.NegocioExcepcion;
import pc.historico.persistencia.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    public List<Historico> listadoPorFechaHora(Date fechaHora) throws NegocioExcepcion {
        try {
            return historicoDAO.findAllByfechaHoraCreacion(fechaHora);
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }

    @Override
    public Historico create(HistoricoDTO h) throws NegocioExcepcion {
        try {
            Historico historico = Historico.builder()
                    //.fechaHoraCreacion(DateTimeFormatter.ofPattern("yyyy/MMMM/dd HH:mm:ss"));
                    .fechaHoraCreacion((Date) new java.util.Date())
                    .categoria(h.getCategoria())
                    .subCategoria(h.getSubCategoria())
                    .identificador(h.getIdentificador())
                    .build();

            return historicoDAO.save(historico);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioExcepcion(e);
        }
    }
}
