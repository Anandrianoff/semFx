package repository;

import model.Product;
import model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrey on 27.04.2017.
 */
@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>{

    Product findOneById(long id);

}
