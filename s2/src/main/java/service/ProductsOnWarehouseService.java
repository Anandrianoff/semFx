package service;

/**
 * Created by Andrey on 30.05.2017.
 */
public interface ProductsOnWarehouseService {

    void updateAmount(long prodId, long warehouseId, int newAmount);
}
