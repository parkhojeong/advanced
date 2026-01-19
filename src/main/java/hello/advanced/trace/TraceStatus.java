package hello.advanced.trace;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.pattern.PathPattern;

@Getter
public class TraceStatus {
    private final TraceId traceId;
    private final Long startTimeMs;
    private final String message;

    public TraceStatus(String message) {
        this.traceId = new TraceId();
        this.startTimeMs = System.currentTimeMillis();
        this.message = message;
    }

    public TraceStatus(TraceId traceId, String message) {
        this.traceId = traceId;
        this.startTimeMs = System.currentTimeMillis();
        this.message = message;
    }
}
