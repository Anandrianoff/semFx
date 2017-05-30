package service.impl;

import model.ProductsOnWarehouses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductOnWarehousesRepository;
import service.ProductsOnWarehouseService;

/**
 * Created by Andrey on 30.05.2017.
 */
@Service
public class PriductsOnWarehouseServiceImpl implements ProductsOnWarehouseService {

    @Autowired
    ProductOnWarehousesRepository productOnWarehousesRepository;

    @Override
    public void updateAmount(long prodId, long warehouseId, int newAmount) {
        ProductsOnWarehouses pow = productOnWarehousesRepository.findOneByProductIdAndWarehouseId(prodId, warehouseId);
        pow.setAmount(newAmount);
        productOnWarehousesRepository.save(pow);
    }
}
