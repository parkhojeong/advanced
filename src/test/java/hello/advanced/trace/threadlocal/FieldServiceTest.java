package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("userA");
        Thread threadB = new Thread(userB);
        threadB.setName("userB");

        threadA.start();
        Thread.sleep(100);
        threadB.start();

        Thread.sleep(2000); // countdownlatch
        log.info("end");
    }
}
