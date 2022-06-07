package pc.historico.negocio;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public List<HistoricoResponseDTO> listado() throws NegocioExcepcion {
        try {
            List<Historico> list = historicoDAO.findAll();
            List<HistoricoResponseDTO> list2 = new ArrayList<>();
            HistoricoResponseDTO historicoResponse = new HistoricoResponseDTO();
            log.warn("*********Size = ", list.toString());
            ObjectMapper mapper = new ObjectMapper();

            for (int i = 0; i < list.size(); i++) {
                historicoResponse = HistoricoResponseDTO.builder()
                        .fechaHora(list.get(i).getFechaHoraCreacion())
                        .categoria(list.get(i).getCategoria())
                        .subCategoria(list.get(i).getSubCategoria())
                        .identificador(list.get(i).getIdentificador())
                        .rawData(mapper.readValue(list.get(i).getRawData(), Map.class))
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

            return historicoDAO.save(historico);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new NegocioExcepcion(e);
        }
    }

}
