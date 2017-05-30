package util;

import form.AddWarehouseForm;
import model.Warehouse;

/**
 * Created by Andrey on 27.04.2017.
 */
public class AddWarehouseFormToWarehouse {

    public static Warehouse transform(AddWarehouseForm form){
        Warehouse warehouse = new Warehouse();
        warehouse.setCity(form.getCity());
        warehouse.setAddress(form.getAddress());
        return warehouse;
    }
}
