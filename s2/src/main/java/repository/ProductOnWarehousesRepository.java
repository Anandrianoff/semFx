package repository;

import model.ProductsOnWarehouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrey on 28.04.2017.
 */
@Repository
public interface ProductOnWarehousesRepository extends JpaRepository<ProductsOnWarehouses, Long> {
    ProductsOnWarehouses findOneByProductId(long id);
    ProductsOnWarehouses findOneByProductIdAndWarehouseId(long productId, long warehouseId);
}
