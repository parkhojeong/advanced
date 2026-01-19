package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

    private final OrderServiceV0 orderService;
    private final Logger logger;

    @GetMapping("/v0/request")
    public String orderItem(@RequestParam String itemId) {
        logger.log("request received", 0);

        orderService.orderItem(itemId, logger);

        logger.log("request processed", 0);
        return "ok";
    }
}
