package pc.historico.config;

import lombok.Data;
import net.spy.memcached.MemcachedClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@ConfigurationProperties(prefix = "memcache")
@Data
public class MemCachedConfig {

    private String ip;
    private Integer port;

    @Bean
    public MemcachedClient memCachedClient() {
        try {
            return new MemcachedClient(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
