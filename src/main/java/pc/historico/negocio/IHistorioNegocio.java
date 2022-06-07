package pc.historico.negocio;

import pc.historico.entities.Historico;
import pc.historico.dtos.HistoricoRequestDTO;
import pc.historico.dtos.HistoricoResponseDTO;
import pc.historico.negocio.excepciones.NegocioExcepcion;

import java.util.Date;
import java.util.List;

public interface IHistorioNegocio {

    List<HistoricoResponseDTO> listado() throws NegocioExcepcion;
    List<Historico> listadoPorFechaHora(Date fechaHora) throws NegocioExcepcion;
    Historico create(HistoricoRequestDTO h) throws NegocioExcepcion;

    Historico getUltimo() throws NegocioExcepcion;
}
