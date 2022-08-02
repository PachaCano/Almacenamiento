package pc.historico.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import pc.historico.dtos.HistoricoDTO;

import java.io.IOException;
import java.net.InetSocketAddress;

//@SuppressWarnings("serial")
@Slf4j
public class Memcache {
    private MemcachedClient mcc;

    public Memcache() throws IOException {
        this.mcc = new MemcachedClient((new InetSocketAddress("143.0.100.214", 11211)));
    }

    public boolean ultimo(Historico ult, int tExp) {
        /*HistoricoDTO aux = HistoricoDTO.builder().id(ult.getId())
                .fechaHoraCreacion(ult.getFechaHoraCreacion().getTime())
                .categoria(ult.getCategoria())
                .identificador(ult.getIdentificador())
                .subCategoria(ult.getSubCategoria())
                .rawData(ult.getRawData()).build();*/
        //log.warn("********************** ultimo" + aux.toString());
        if (mcc.add("ultimo", tExp, ult.toString()).isDone()) {
            log.warn("ultimo guardado");
            return true;
        }
        return false;
    }

    public boolean actualizarUltimo(Historico ult, int tExp) {
        /*HistoricoDTO aux = HistoricoDTO.builder().id(ult.getId())
                .fechaHoraCreacion(ult.getFechaHoraCreacion().getTime())
                .categoria(ult.getCategoria())
                .identificador(ult.getIdentificador())
                .subCategoria(ult.getSubCategoria())
                .rawData(ult.getRawData()).build();
        log.warn("********************** " + aux.toString());*/
        if (mcc.set("ultimo", tExp, ult.toString()).isDone()) {
            log.warn("ultimo actualizado");
            return true;
        }
        return false;
    }

    public Historico getUltimo() throws JsonProcessingException {
        log.warn("++++++++++++++" + mcc.get("ultimo").toString());
        return mcc.get("ultimo") == null ? null : (new ObjectMapper().readValue("\""+mcc.get("ultimo").toString() +"\"", Historico.class));
    }
}
