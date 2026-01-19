package hello.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        // business logic start
        log.info("business logic1 start");
        // business logic end
        long endTime = System.currentTimeMillis();
        log.info("business end. elapsedTime={}", endTime - startTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        // business logic start
        log.info("business logic2 start");
        // business logic end
        long endTime = System.currentTimeMillis();
        log.info("business end. elapsedTime={}", endTime - startTime);
    }
}
