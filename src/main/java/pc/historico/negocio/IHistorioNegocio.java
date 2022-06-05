package pc.historico.negocio;

import pc.historico.entities.Historico;
import pc.historico.entities.HistoricoRequestDTO;
import pc.historico.entities.HistoricoResponseDTO;
import pc.historico.negocio.excepciones.NegocioExcepcion;

import java.util.Date;
import java.util.List;

public interface IHistorioNegocio {

    public List<HistoricoResponseDTO> listado() throws NegocioExcepcion;
    public List<Historico> listadoPorFechaHora(Date fechaHora) throws NegocioExcepcion;
    public Historico create(HistoricoRequestDTO h) throws NegocioExcepcion;
}
