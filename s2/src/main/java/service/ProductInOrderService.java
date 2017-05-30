package service;

import model.Product;
import model.ProductInOrder;

import java.util.List;

/**
 * Created by Andrey on 14.05.2017.
 */
public interface ProductInOrderService {
    void saveNewProductInOrder(ProductInOrder productInOrder);
    List<ProductInOrder> findAllProductsInOrder(long orderId);
}
