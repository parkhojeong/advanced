package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace{
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder;

    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder;
        TraceStatus traceStatus = new TraceStatus(traceId, message);
        start(traceStatus);
        return traceStatus;
    }

    private void syncTraceId() {
        if(traceIdHolder == null) {
            traceIdHolder = new TraceId();
        } else{
            traceIdHolder = traceIdHolder.createNextId();
        }
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

        releaseTraceId();
    }

    private void releaseTraceId() {
        if(traceIdHolder.getLevel() == 0) {
            traceIdHolder = null;
        } else {
            traceIdHolder = traceIdHolder.createPrevId();
        }
    }

    private String addSpace(String prefix, int level) {
        if (level == 0) {
            return "";
        }

        return "|  ".repeat(level - 1) +
                "|" + prefix;
    }
}
