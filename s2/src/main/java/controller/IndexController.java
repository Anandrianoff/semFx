package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.ProductsRepository;
import service.ProductService;

/**
 * Created by Andrey on 22.04.2017.
 */

@Controller
public class IndexController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/")
    public String getIndexPage(Model model){
        model.addAttribute("products", productService.getAllProducts());

        return "index/index";
    }
}
