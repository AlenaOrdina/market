package ru.tsystem.javaschool.ordinaalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.services.api.CartService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private SecurityService securityService;

    /**
     * Return page with bucket.
     * @param model     page model
     * @param error     errors for validation
     * @return          jsp
     */
    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    public String cartGet(Model model, String error){
        String customerEmail = securityService.findLoggedInEmail();
        OrdersDTO cart = cartService.getCustomerCart(customerEmail);
        if(error!=null)
            model.addAttribute("error", error);
        model.addAttribute("products", cart.getProductDTOs());
        model.addAttribute("productsSize", cart.getProductDTOs().size());
        return "/cart";
    }

    /**
     * Delete products from bucket.
     * @param selected     errors for validation
     * @return             jsp
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteFromBucket(String[] selected){
        if(selected==null)
            return "redirect:/bucket?error='No one is selected'";
        String customerEmail = securityService.findLoggedInEmail();
        cartService.deleteFromCart(customerEmail, selected);
        return "redirect:/cart";
    }
}
