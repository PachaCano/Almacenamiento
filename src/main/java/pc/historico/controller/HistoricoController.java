package pc.historico.controller;

import javassist.NotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pc.historico.entities.Historico;
import pc.historico.entities.HistoricoDTO;
import pc.historico.negocio.HistoricoNegocio;
import pc.historico.negocio.excepciones.NegocioExcepcion;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?> getHistoricoFechaHora(@PathVariable Date fechaHora) throws NegocioExcepcion {
        return new ResponseEntity<>(this.historicoServicio.listadoPorFechaHora(fechaHora), HttpStatus.OK);
    }

    @PostMapping("/historico")
    public ResponseEntity<?> crearHistorico (@RequestBody HistoricoDTO h) {
        try {
            this.historicoServicio.create(h);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NegocioExcepcion e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } //haría falta una excepción de notfound?
    }
    
}
