package pc.Historico.Controller;

import pc.Historico.Negocio.HistoricoNegocio;
import pc.Historico.Negocio.excepciones.NegocioExcepcion;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class HistoricoController {

    private final HistoricoNegocio historicoServicio;

    @GetMapping("/historico")
    public ResponseEntity<?> getHistorico() throws NegocioExcepcion {
        return new ResponseEntity<>(this.historicoServicio.listado(), HttpStatus.OK);
    }

    @GetMapping("/historico/{fechaHora}")
    public ResponseEntity<?> getHistoricoFechaHora(@PathVariable Timestamp fechaHora) throws NegocioExcepcion {
        return new ResponseEntity<>(this.historicoServicio.listadoPorFechaHora(fechaHora), HttpStatus.OK);
    }
    
}
