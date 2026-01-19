package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long startTime = System.currentTimeMillis();
        // business logic start
        call();
        // business logic end
        long endTime = System.currentTimeMillis();
        log.info("business end. elapsedTime={}", endTime - startTime);
    }

    protected abstract void call();
}
