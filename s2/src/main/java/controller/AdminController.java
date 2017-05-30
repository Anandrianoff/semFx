package controller;

import form.AddProductForm;
import form.AddWarehouseForm;
import form.RemoveUserForm;
import model.Order;
import model.User;
import model.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.impl.WarehouseService;
import util.AddWarehouseFormToWarehouse;
import util.EMailer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Andrey on 27.04.2017.
 */
@Controller
public class AdminController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "admin/addProduct", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddProductPage(Model model){
        model.addAttribute("productform", new AddProductForm());
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        return "admin/addProduct";
    }

    @RequestMapping(value = "admin/addProduct", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProduct(@ModelAttribute("productform") @Valid AddProductForm form, BindingResult res, Model model){
        AddProductForm apf = new AddProductForm();
        apf.setWarehouses(warehouseService.getAllWarehouses());
        model.addAttribute("productform", apf);
//        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        if(!res.hasErrors()){
            productService.saveNewProduct(form);
        }

        return "redirect:/admin/addProduct";
    }

    @RequestMapping(value = "admin/warehouses", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddWarehousePage(Model model){
        model.addAttribute("warehouseform", new AddWarehouseForm());
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        return "admin/warehouses";
    }

    @RequestMapping(value = "/admin/warehouses/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addWarehouse(@ModelAttribute("warehouseform") @Valid AddWarehouseForm form, BindingResult result){
        if(!result.hasErrors()){
            warehouseService.saveNewWarehouse(form);
            return "redirect:/admin/warehouses";
        }else {
            //TODO: Обработка неправильного ввода
            return "redirect:/admin/warehouses";
        }
    }

    @RequestMapping(value = "/admin/users")
    public String getUserAdminPage(Model model, HttpServletRequest request){
        model.addAttribute("token", new HttpSessionCsrfTokenRepository().loadToken(request).toString());
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @RequestMapping(value = "/admin/removeUser", method = RequestMethod.POST)
    public String removeUser(@RequestParam long id){
        if(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId() != id){
            userService.removeUser(id);
        }
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/changeConfirmation")
    public String changeConfirmation(@RequestParam long id){
        userService.changeUserConfirmatiion(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String getOrdersPage(Model model, HttpServletRequest request){
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("order", new Order());
        model.addAttribute("_csrf", new HttpSessionCsrfTokenRepository().loadToken(request));
        return "admin/orders";
    }

    @RequestMapping(value = "/admin/changeStatus", method = RequestMethod.POST)
    public String changeConfirmation(Model model, @RequestParam long order, @RequestParam OrderStatus status){
        Order o = orderService.changeStatus(order, status);
        //Отправка письма с уведомлением
        String stat = "";
        switch (o.getStatus()){
            case ORDER_CHOOSE:
                stat = "Набирается";
                break;
            case ORDER_SENT:
                stat = "Отправлен";
                break;
            case ORDER_SET:
                stat = "Заказ набран";
                break;
            case ORDER_DONE:
                stat = "Заказ получен";
                break;
        }
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Mail.xml");
        EMailer eMailer = (EMailer) context.getBean("EMailer");
        //Надо сделать какой-то токен для подтвержения, но я сделаю чуть иначе, хотя понимаю, что так лучше не надо
        eMailer.sendMail("hikkas.from.506@gmail.com", o.getUser().getEmail(),
                "Статус вашего заказа изменен",
                "Статус заказа изменен на \"" + stat + "\"");
        return "redirect:/admin/orders";
    }
}
