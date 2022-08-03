package pc.historico.entities;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pc.historico.dtos.HistoricoDTO;

@Slf4j
@AllArgsConstructor
@Service
public class Memcache {
    private final MemcachedClient mcc;

    @Async
    public void ultimo(HistoricoDTO ult, int tExp) {
        this.mcc.add("ultimo", tExp, ult.toString())
                .addListener(future -> log.warn("Guardado " + ult));
    }

    @Async
    public void actualizarUltimo(HistoricoDTO ult, int tExp) {
        this.mcc.set("ultimo", tExp, ult.toString())
                .addListener(future -> log.warn("Actualizado " + ult));
    }

    public String getUltimo() {
        return mcc.get("ultimo") == null ? null : (String) mcc.get("ultimo");
    }
}
