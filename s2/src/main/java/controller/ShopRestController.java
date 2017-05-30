package controller;

import model.Product;
import model.ProductsOnWarehouses;
import model.User;
import model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ProductsOnWarehouseService;
import service.UserService;
import service.WarehouseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 21.05.2017.
 */
@RestController
//@RequestMapping(value = "/rest/")
public class ShopRestController {

//    @Autowired
//    UserService userService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    ProductsOnWarehouseService productsOnWarehouseService;

    @RequestMapping(value = "/rest/warehouses", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Warehouse> getAllWarehouses(){
        return warehouseService.getAllWarehouses();
    }

    @RequestMapping(value = "/rest/warehouse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductsOnWarehouse(@PathVariable("id") Long id){
        Warehouse warehouse = warehouseService.getOneById(id);
        List<Product> products = new ArrayList<>();
        for(ProductsOnWarehouses pow : warehouse.getProductsOnWarehouses()){
            products.add(pow.getProduct());
        }
        return products;
    }

    @RequestMapping(value = "/rest/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAmount( @RequestParam long warehouse, @RequestParam long prod, @RequestParam int amount){
        productsOnWarehouseService.updateAmount(prod, warehouse, amount);
        return "success";
    }
}
