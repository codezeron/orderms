package enzoyh.buildrun.btgpactual.orderms.controller.dto;

import enzoyh.buildrun.btgpactual.orderms.factory.OrderEntityFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderResponseTest {

    @Nested
    class fromEntity {

        @Test
        void shouldMapCorrectly() {
            // ARRANGE
            var input = OrderEntityFactory.build();
            // ACT
            var output = OrderResponse.fromEntity(input);
            // ASSERT

            assertEquals(input.getOrderid(), output.orderId());
            assertEquals(input.getCustomerId(), output.customerId());
            assertEquals(input.getTotal(), output.total());
        }
    }
}