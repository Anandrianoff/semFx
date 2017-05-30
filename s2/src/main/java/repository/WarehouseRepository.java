package repository;

import model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrey on 27.04.2017.
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>{

    Warehouse findOneById(long id);
}
