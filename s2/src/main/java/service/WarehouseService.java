package service;

import form.AddWarehouseForm;
import model.Warehouse;

import java.util.List;

/**
 * Created by Andrey on 27.04.2017.
 */
public interface WarehouseService {

    void saveNewWarehouse(AddWarehouseForm form);
    List<Warehouse> getAllWarehouses();
    Warehouse getOneById(long id);
}
