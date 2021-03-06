package ru.tsystem.javaschool.ordinaalena.services.api;


import ru.tsystem.javaschool.ordinaalena.DTO.AddressDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.CustomerDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.OrdersDTO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.entities.Customer;
import ru.tsystem.javaschool.ordinaalena.entities.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * Create new order for user.
     *
     * @param ordersDTO  Order.
     */
     void makeNewOrder(OrdersDTO ordersDTO);

    /**
     * Get all user's orders.
     *
     * @param email User's email.
     * @return List with orders.
     */
     List<OrdersDTO> getCustomerOrders(String email);

     List<OrdersDTO> getAll();

     OrdersDTO getOrder(int orderId);


     List<ProductDTO> getAllBuyingCounts();


     List<CustomerDTO>  getAllCustomersCounts();
     List <CustomerDTO> findTopCustomer();
     void sendMessage(OrdersDTO orders, CustomerDTO customer, List<ProductDTO> cart,
                      AddressDTO address, String source, String target, String title);


}
