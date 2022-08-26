package com.example.marck.OrderingService.order;

import com.example.marck.OrderingService.AuthService;
import com.example.marck.OrderingService.order.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {
    // TODO: Add error handling

    private final OrderService orderService;
    private final AuthService authService;

    @Autowired
    public OrderController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(this.orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Integer orderId) {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.orderService.getOrderByOrderId(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!orderService.productExistsAndIsInStock(order.getProductName(), order.getQuantity())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            // TODO: Handle "out of stock" separately
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(path = "/byProductName/{productName}/running")
    public ResponseEntity<Boolean> productHasRunningOrdersByName(@PathVariable("productName") String productName) {
        if (!authService.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(this.orderService.productHasRunningOrdersByName(productName), HttpStatus.OK);
    }
}
