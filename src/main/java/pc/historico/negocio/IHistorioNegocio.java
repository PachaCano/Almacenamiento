package pc.historico.negocio;

import pc.historico.entities.Historico;
import pc.historico.entities.HistoricoDTO;
import pc.historico.negocio.excepciones.NegocioExcepcion;
import java.util.List;

public interface IHistorioNegocio {

    public List<Historico> listado() throws NegocioExcepcion;
    public List<Historico> listadoPorFechaHora(java.sql.Date fechaHora) throws NegocioExcepcion;
    public Historico create(HistoricoDTO h) throws NegocioExcepcion;
}
