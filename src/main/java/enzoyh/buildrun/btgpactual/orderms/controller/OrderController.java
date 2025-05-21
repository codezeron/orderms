package enzoyh.buildrun.btgpactual.orderms.controller;

import enzoyh.buildrun.btgpactual.orderms.controller.dto.ApiResponse;
import enzoyh.buildrun.btgpactual.orderms.controller.dto.OrderResponse;
import enzoyh.buildrun.btgpactual.orderms.controller.dto.PaginationResponse;
import enzoyh.buildrun.btgpactual.orderms.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ){

        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, size));
        var totalOnOrders = orderService.findTotalOnOrderByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }
}
