package ua.com.cashup.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cashup.application.entity.Client;
import ua.com.cashup.application.entity.Order;
import ua.com.cashup.application.service.ClientService;
import ua.com.cashup.application.service.OrderService;

import java.util.List;

/**
 * Created by Вадим on 05.10.2017.
 */
@RestController
public class OrderController {

    private ClientService clientService;

    private OrderService orderService;

    @Autowired
    public OrderController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @PostMapping(value = "/orders/add/{TIN}")
    public ResponseEntity createOrder(@PathVariable int TIN,
                                     @RequestBody Order order){

        Client client = clientService.getClientByTIN(TIN);
        client.getOrders().add(order);
        clientService.edit(client);
        return ResponseEntity.ok("Order created successfully");
    }

    @GetMapping(value = "/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/orders/{TIN}")
    public ResponseEntity getAllOrdersByClient(@PathVariable int TIN){

        Client client = clientService.getClientByTIN(TIN);
        if (client == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Client with Taxpayer Identification Number %d does not exist", TIN));
        }

        List<Order> orders = orderService.getAllOrdersByClient(client);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value = "/orders/confirm")
    public ResponseEntity confirmOrder(@RequestBody Order order){
        Order confirmedOrder = orderService.confirmOrder(order);
        return ResponseEntity.ok(confirmedOrder);

    }



}
