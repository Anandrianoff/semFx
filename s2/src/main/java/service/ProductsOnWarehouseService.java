package service;

import model.ProductsOnWarehouses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Andrey on 30.05.2017.
 */
public interface ProductsOnWarehouseService {

    void replaseProduct(long from, long to, long prod, int amount);
    void updateAmount(long prodId, long warehouseId, int newAmount);
    Page<ProductsOnWarehouses> findAllProductsOnWarehouse(long id, Pageable pageable);
}
