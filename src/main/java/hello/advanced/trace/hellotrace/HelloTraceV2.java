package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(String message) {
        TraceStatus traceStatus = new TraceStatus(message);
        start(traceStatus);
        return traceStatus;
    }

    public TraceStatus beginSync(TraceId prevTraceId, String message) {
        TraceId nextId = prevTraceId.createNextId();
        TraceStatus traceStatus = new TraceStatus(nextId, message);
        start(traceStatus);
        return traceStatus;
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void start(TraceStatus traceStatus) {
        log.info("[{}] {}{}",
                traceStatus.getTraceId().getId(),
                addSpace(START_PREFIX, traceStatus.getTraceId().getLevel()),
                traceStatus.getMessage());
    }

    private void complete(TraceStatus status, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                    status.getTraceId().getId(),
                    addSpace(COMPLETE_PREFIX, status.getTraceId().getLevel()),
                    status.getMessage(),
                    stopTimeMs - status.getStartTimeMs());
        } else {
            log.info("[{}] {}{} time={}ms ex={}",
                    status.getTraceId().getId(),
                    addSpace(EX_PREFIX, status.getTraceId().getLevel()),
                    status.getMessage(),
                    stopTimeMs - status.getStartTimeMs(),
                    e.toString());
        }
    }

    /**
     * level=0
     * level=1 |-->
     * level=2 |  |-->
     *
     * level=2 |  |<--
     * level=1 <--|
     *
     * exception
     * level=2 |  |<X-
     * level=1 <X-|
     */
    private String addSpace(String prefix, int level) {
        if (level == 0) {
            return "";
        }

        return "| ".repeat(level - 1) +
                "|" + prefix;
    }
}
