package pc.historico.negocio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pc.historico.dtos.HistoricoDTO;
import pc.historico.negocio.excepciones.NegocioExcepcion;

import java.util.List;

public interface IHistorioNegocio {

    List<HistoricoDTO> listado() throws NegocioExcepcion;
    Page<HistoricoDTO> listadoHistoricoPaginado(Pageable pageable, String categoria) throws NegocioExcepcion;
    Page<HistoricoDTO> listadoPaginado(Pageable pageable) throws NegocioExcepcion;
    Page<HistoricoDTO> listadoHistoricoPaginadoSub(Pageable pageable, String categoria, String subCategoria) throws NegocioExcepcion;
    HistoricoDTO ultimoPorIdentificador(String identificador) throws NegocioExcepcion;
    HistoricoDTO create(HistoricoDTO h) throws NegocioExcepcion;
    String getUltimo();
}
