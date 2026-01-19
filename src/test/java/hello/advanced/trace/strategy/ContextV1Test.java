package hello.advanced.trace.strategy;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0() {
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

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("business logic1");
            }
        };
        log.info("class name={}", template1.getClass());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("business logic2");
            }
        };
        log.info("class name={}", template2.getClass());
        template2.execute();
    }

}
