package pc.historico.entities;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;

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
        if (mcc.add("ultimo", tExp, ult).isDone()) {
            log.warn("ultimo guardado");
            return true;
        }
        return false;
    }

    public boolean actualizarUltimo(Historico ult, int tExp) {
        if (mcc.set("ultimo", tExp, ult).isDone()) {
            log.warn("ultimo actualizado");
            return true;
        }
        return false;
    }

    public Historico getUltimo() {
        return mcc.get("ultimo") == null ? null : (((Historico) mcc.get("ultimo")));
    }
}
