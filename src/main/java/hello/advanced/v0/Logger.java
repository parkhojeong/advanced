package hello.advanced.v0;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Logger {
    private String id;
    private int startMillis;

    public Logger() {
        id = UUID.randomUUID().toString().substring(0, 8);
        startMillis = (int) (System.currentTimeMillis());
    }

    public void log(String message, int level) {
        int currentMillis = (int) (System.currentTimeMillis());
        log.info("[{}] level={}, message={} time={}ms", id, level, message, currentMillis - startMillis);
    }
}
