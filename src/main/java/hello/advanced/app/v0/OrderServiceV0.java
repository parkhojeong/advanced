package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepository;
    private final Logger logger;

    public void orderItem(String itemId, Logger logger) {
        logger.log("orderItem called", 1);
        orderRepository.save(itemId);
        logger.log("orderItem finished", 1);
    }
}
