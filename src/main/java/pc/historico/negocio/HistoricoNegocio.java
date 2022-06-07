package pc.historico.negocio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import pc.historico.entities.Historico;
import pc.historico.dtos.HistoricoRequestDTO;
import pc.historico.dtos.HistoricoResponseDTO;
import pc.historico.negocio.excepciones.NegocioExcepcion;
import pc.historico.persistencia.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class HistoricoNegocio implements IHistorioNegocio{

    private final HistoricoRepository historicoDAO;
    private final MemCache cache;

    @Override
    public List<HistoricoResponseDTO> listado() throws NegocioExcepcion {
        try {
            List<Historico> list = historicoDAO.findAll();
            List<HistoricoResponseDTO> list2 = new ArrayList<>();
            HistoricoResponseDTO historicoResponse;
            ObjectMapper mapper = new ObjectMapper();

            for (Historico historico : list) {
                historicoResponse = HistoricoResponseDTO.builder()
                        .fechaHora(historico.getFechaHoraCreacion())
                        .categoria(historico.getCategoria())
                        .subCategoria(historico.getSubCategoria())
                        .identificador(historico.getIdentificador())
                        .rawData(mapper.readValue(historico.getRawData(), Map.class))
                        .build();
                log.warn(historicoResponse.getRawData().toString());
                list2.add(historicoResponse);
            }

            return list2;
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
    public Historico create(HistoricoRequestDTO h) throws NegocioExcepcion {
        try {
            log.warn(h.toString());
            Historico historico = Historico.builder()
                    .fechaHoraCreacion(new Date())
                    .categoria(h.getCategoria())
                    .subCategoria(h.getSubCategoria())
                    .identificador(h.getIdentificador())
                    .rawData(h.getRawData().toString())
                    .build();

            cache.set(historico.toString(), 3600);

            return historicoDAO.save(historico);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioExcepcion(e);
        }
    }

    @Override
    public Historico getUltimo() throws NegocioExcepcion {
        Historico h;
        try {
            h = new ObjectMapper().readValue(cache.get(), Historico.class);
        } catch (JsonProcessingException e) {
            throw new NegocioExcepcion(e.getMessage());
        }
        return h;
    }

}
