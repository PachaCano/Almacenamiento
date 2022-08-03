package pc.historico.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pc.historico.dtos.HistoricoDTO;
import pc.historico.negocio.HistoricoNegocio;
import pc.historico.negocio.excepciones.NegocioExcepcion;
import lombok.AllArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
@RequestMapping
@Slf4j
public class HistoricoController {

    private final HistoricoNegocio historicoServicio;

    @GetMapping(value = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHistoricoPorCategoria(@RequestParam String categoria,
                                                      @PageableDefault Pageable pageable) {
        try {
            return new ResponseEntity<>(this.historicoServicio.listadoHistoricoPaginado(pageable, categoria), HttpStatus.OK);
        } catch (NegocioExcepcion e) {
            log.error((e.getMessage()), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/paginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHistoricoPaginado(Pageable pageable) {
        try {
            return new ResponseEntity<>(this.historicoServicio.listadoPaginado(pageable), HttpStatus.OK);
        } catch (NegocioExcepcion e) {
            log.error((e.getMessage()), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/categoria/sub-categoria", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHistoricoPorCategoriaSubCategoria(@RequestParam String categoria,
                                                        @RequestParam String subCategoria,
                                                      @PageableDefault Pageable pageable) {
        try {
            return new ResponseEntity<>(this.historicoServicio.listadoHistoricoPaginadoSub(pageable, categoria, subCategoria), HttpStatus.OK);
        } catch (NegocioExcepcion e) {
            log.error((e.getMessage()), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/identificador", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHistoricoPorIdentificador(@RequestParam String identificador) {
        try {
            return new ResponseEntity<>(this.historicoServicio.ultimoPorIdentificador(identificador), HttpStatus.OK);
        } catch (NegocioExcepcion e) {
            log.error((e.getMessage()), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHistorico() throws NegocioExcepcion {
        return new ResponseEntity<>(this.historicoServicio.listado(), HttpStatus.OK);
    }

    @GetMapping(value = "/ultimo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUltimo() {
        return new ResponseEntity<>(this.historicoServicio.getUltimo(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> crearHistorico (@RequestBody HistoricoDTO h) {
        try {
            this.historicoServicio.create(h);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NegocioExcepcion e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
}
