package pc.historico.negocio;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pc.historico.entities.Historico;
import pc.historico.entities.Memcache;
import pc.historico.dtos.HistoricoDTO;
import pc.historico.negocio.excepciones.NegocioExcepcion;
import pc.historico.persistencia.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class HistoricoNegocio implements IHistorioNegocio{

    private HistoricoRepository historicoDAO;
    private Memcache cache;

    @Override
    public List<HistoricoDTO> listado() throws NegocioExcepcion {
        try {
            return this.historicoDAO.findAll().stream().map(HistoricoDTO::new)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }

    @Override
    public Page<HistoricoDTO> listadoHistoricoPaginado(Pageable pageable, String categoria) throws NegocioExcepcion {
        try {
            return this.historicoDAO.findAllByCategoriaOrderByFechaHoraCreacionDesc(categoria, pageable).map(HistoricoDTO::new);
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }

    @Override
    public Page<HistoricoDTO> listadoPaginado(Pageable pageable) throws NegocioExcepcion {
        try {
            return this.historicoDAO.findAllOrderByFechaHoraCreacionDesc(pageable).map(HistoricoDTO::new);
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }

    @Override
    public Page<HistoricoDTO> listadoHistoricoPaginadoSub(Pageable pageable, String categoria, String subCategoria) throws NegocioExcepcion {
        try {
            return this.historicoDAO.findAllByCategoriaAndSubCategoriaOrderByFechaHoraCreacionDesc(categoria, subCategoria, pageable).map(HistoricoDTO::new);
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }

    @Override
    public HistoricoDTO ultimoPorIdentificador(String identificador) throws NegocioExcepcion {
        try {
            return new HistoricoDTO(this.historicoDAO.findFirstByIdentificadorOrderByFechaHoraCreacionDesc(identificador));
        } catch (Exception e) {
            log.error((e.getMessage()), e);
            throw new NegocioExcepcion(e);
        }
    }


    @Override
    public HistoricoDTO create(HistoricoDTO h) throws NegocioExcepcion {
        try {
            Historico historico = Historico.builder()
                    .fechaHoraCreacion(new Date())
                    .categoria(h.getCategoria())
                    .subCategoria(h.getSubCategoria())
                    .identificador(h.getIdentificador())
                    .rawData(h.getRawData().toString())
                    .build();

            if (cache.getUltimo() == null) {
                cache.ultimo(h, 3600);
            } else {
                cache.actualizarUltimo(h, 3600);
            }

            return new HistoricoDTO(this.historicoDAO.save(historico));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioExcepcion(e);
        }
    }

    @Override
    public String getUltimo() {
        return cache.getUltimo();
    }

}
