package service;

import form.AddProductForm;
import model.Product;

import java.util.List;

/**
 * Created by Andrey on 28.04.2017.
 */
public interface ProductService {

    long saveNewProduct(AddProductForm form);
    List<Product> getAllProducts();
    Product findOneById(long id);
    int getAllProductsAmountByProdId(long id);
}
