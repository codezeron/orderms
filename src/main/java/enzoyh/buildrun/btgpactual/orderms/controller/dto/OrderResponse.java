package enzoyh.buildrun.btgpactual.orderms.controller.dto;

import enzoyh.buildrun.btgpactual.orderms.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,
        Long customerId,
        BigDecimal total
) {
    public static OrderResponse fromEntity(final OrderEntity order) {
        return new OrderResponse(
                order.getOrderid(),
                order.getCustomerId(),
                order.getTotal()
        );
    }
}
