package service;

import model.Order;
import model.enums.OrderStatus;

import java.util.List;

/**
 * Created by Andrey on 13.05.2017.
 */
public interface OrderService {
    Order saveNewOrder(Order order);
    List<Order> getAllOrdersByUser(long userid);
    List<Order> getAllOrders();
    Order changeStatus(long id, OrderStatus status);
}
