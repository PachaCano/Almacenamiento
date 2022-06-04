package pc.Historico.Negocio;

import pc.Historico.entities.Historico;
import pc.Historico.Negocio.excepciones.NegocioExcepcion;
import java.util.List;

public interface IHistorioNegocio {

    public List<Historico> listado() throws NegocioExcepcion;
    public List<Historico> listadoPorFechaHora(java.sql.Timestamp fechaHora) throws NegocioExcepcion;
}
