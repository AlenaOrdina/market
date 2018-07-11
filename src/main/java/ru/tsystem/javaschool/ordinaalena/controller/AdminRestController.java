package ru.tsystem.javaschool.ordinaalena.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.tsystem.javaschool.ordinaalena.services.api.AdminService;
import ru.tsystem.javaschool.ordinaalena.services.api.SecurityService;

@RestController
public class AdminRestController {
    @Autowired
    AdminService adminService;

    @Autowired
    SecurityService securityService;


    private static final Logger logger = Logger.getLogger(AdminRestController.class);

    /**
     * Change order status
     * @param ordersId   order id
     * @return          new status
     */
    @RequestMapping(value = "/status/{ordersId}", method = RequestMethod.PUT)
    public String changeOrderStatus(@PathVariable Integer ordersId){
        logger.info("Change order status. OrderID: " + ordersId + ". Admin: " + securityService.findLoggedInEmail());
        return adminService.changeOrderStatus(ordersId);
    }

    /**
     * Change product status
     * @param productId      products id
     * @return               new status
     */
   /* @RequestMapping(value = "/status/{productId}", method = RequestMethod.PUT)
    public String changeProductStatus(@PathVariable Integer productId){
        logger.info("Change product status. OrderID: " + productId + ". Admin: " + securityService.findLoggedInEmail());
        return adminService.changeProductStatus(productId);
    }*/
}