package enzoyh.buildrun.btgpactual.orderms.controller;

import enzoyh.buildrun.btgpactual.orderms.factory.OrderResponseFactory;
import enzoyh.buildrun.btgpactual.orderms.service.OrderService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @Captor
    ArgumentCaptor<Long> customerIdCaptor;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestCaptor;

    @Nested
    class ListOrders {

        @Test
        void shouldReturnHttpOk() {
            // ARRANGE = prepara todos os mocks para a execucao
            var customerId = 1L;
            var page = 0;
            var size = 10;

            doReturn(OrderResponseFactory.buildWithOneItem()).when(orderService).findAllByCustomerId(anyLong(), any());
            doReturn(BigDecimal.valueOf(20.50)).when(orderService).findTotalOnOrderByCustomerId(anyLong());

            // ACT = executar o metodo a ser testado
            var response = orderController.listOrders(customerId, page, size);

            // ASSERT = verifica se a execucao foi feita
            assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
        }

        @Test
        void shouldPassCorrectParametersToService() {
            // ARRANGE = prepara todos os mocks para a execucao
            var customerId = 1L;
            var page = 0;
            var size = 10;

            doReturn(OrderResponseFactory.buildWithOneItem()).when(orderService).findAllByCustomerId(customerIdCaptor.capture(),pageRequestCaptor.capture());
            doReturn(BigDecimal.valueOf(20.50)).when(orderService).findTotalOnOrderByCustomerId(customerIdCaptor.capture());

            // ACT = executar o metodo a ser testado
            var response = orderController.listOrders(customerId, page, size);

            // ASSERT = verifica se a execucao foi feita
            assertEquals(2, customerIdCaptor.getAllValues().size());
            assertEquals(customerId, customerIdCaptor.getAllValues().get(0));
            assertEquals(customerId, customerIdCaptor.getAllValues().get(1));
            assertEquals(page, pageRequestCaptor.getValue().getPageNumber());
            assertEquals(size, pageRequestCaptor.getValue().getPageSize());
        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
            // ARRANGE = prepara todos os mocks para a execucao
            var customerId = 1L;
            var page = 0;
            var size = 10;
            var totalOnOrders = BigDecimal.valueOf(20.5);
            var pagination = OrderResponseFactory.buildWithOneItem();

            doReturn(pagination).when(orderService).findAllByCustomerId(anyLong(), any());
            doReturn(totalOnOrders).when(orderService).findTotalOnOrderByCustomerId(anyLong());

            // ACT = executar o metodo a ser testado
            var response = orderController.listOrders(customerId, page, size);

            // ASSERT = verifica se a execucao foi feita
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertNotNull(response.getBody().data());
            assertNotNull(response.getBody().summary());
            assertNotNull(response.getBody().pagination());

            assertEquals(totalOnOrders, response.getBody().summary().get("totalOnOrders"));

            assertEquals(pagination.getTotalElements(), response.getBody().pagination().totalElements());
            assertEquals(pagination.getTotalPages(), response.getBody().pagination().totalPages());
            assertEquals(pagination.getNumber(), response.getBody().pagination().page());
            assertEquals(pagination.getSize(), response.getBody().pagination().size());

            assertEquals(pagination.getContent(), response.getBody().data());
        }
    }
}