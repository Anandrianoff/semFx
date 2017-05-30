package repository;

import model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrey on 13.05.2017.
 */
@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long>{
    List<ProductInOrder> findAllByOrderId(long orderId);
}
