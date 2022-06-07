package pc.historico.negocio;

import lombok.AllArgsConstructor;
import net.spy.memcached.MemcachedClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;

@Service
@AllArgsConstructor
public class MemCache {

    private final MemcachedClient mcc;

    public MemCache() throws IOException {
        this.mcc = new MemcachedClient(new InetSocketAddress("172.20.1.6", 11211));
    }

    public String get() {
        return mcc.get("ultimo") == null ? null : (String) mcc.get("ultimo");
    }

    public boolean put(String valor, int tiempo){
        return mcc.add("ultimo", tiempo, valor).isDone();
    }

    public boolean set(String valor, int tiempo) {
        return mcc.set("ultimo", tiempo, valor).isDone();
    }
}
