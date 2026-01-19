package hello.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    private final Logger logger;

    public void save(String itemId) {
        logger.log("save called", 2);
        if (itemId.equals("ex")) {
            throw new IllegalStateException("ex");
        }

        sleep(1000);
        logger.log("save finished", 2);
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
