package pc.historico.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pc.historico.dtos.HistoricoRequestDTO;
import pc.historico.negocio.HistoricoNegocio;
import pc.historico.negocio.excepciones.NegocioExcepcion;
import lombok.AllArgsConstructor;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
@RequestMapping
@Slf4j
public class HistoricoController {

    private final HistoricoNegocio historicoServicio;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHistorico() throws NegocioExcepcion {
        return new ResponseEntity<>(this.historicoServicio.listado(), HttpStatus.OK);
    }

    @GetMapping(value = "/ultimo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUltimo() throws NegocioExcepcion {
        return new ResponseEntity<>(this.historicoServicio.getUltimo(), HttpStatus.OK);
    }

    @GetMapping("/{fechaHora}")
    public ResponseEntity<?> getHistoricoFechaHora(@PathVariable Date fechaHora) throws NegocioExcepcion {
        return new ResponseEntity<>(this.historicoServicio.listadoPorFechaHora(fechaHora), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> crearHistorico (@RequestBody HistoricoRequestDTO h) {
        try {
            this.historicoServicio.create(h);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NegocioExcepcion e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } //haría falta una excepción de notfound?
    }
    
}
