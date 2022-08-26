package com.example.marck.OrderingService.order;

import com.example.marck.OrderingService.order.models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    private final JdbcTemplate jtm;

    public OrderService(JdbcTemplate jtm) {
        this.jtm = jtm;
    }

    public List<Order> getOrders() {
        String sql = "SELECT * FROM orders";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    public Order getOrderByOrderId(Integer orderId) {
        String sql = "SELECT * FROM orders";
        return jtm.queryForObject(sql, new BeanPropertyRowMapper<>(Order.class), orderId);
    }

    public Order createOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, client_id, state, product_name, quantity) VALUES (?,?,?,?,?)";
        jtm.update(sql, order.getOrderId(), order.getClientId(), order.getState(), order.getProductName(), order.getQuantity());
        return order;
    }

    // TODO: Ensure consistency by "reserving" the requested quantity of product by reducing stock in ProductCatalog DB
    public boolean productExistsAndIsInStock(String productName, Integer quantity) {
        RestTemplate restTemplate = new RestTemplate();
        String getStockForProductByNameUrl = "http://localhost:8080/api/product/";
        ResponseEntity<Integer> response = restTemplate.getForEntity(getStockForProductByNameUrl + productName + "/stock", Integer.class);

        if (response.getStatusCode() != HttpStatus.OK) return false;
        Integer stock = response.getBody();

        if (stock == null || stock <= 0) return false;

        return stock >= quantity;
    }

    public boolean productHasRunningOrdersByName(String productName) {
        // TODO: Implement this method, fetch all orders matching specified productName and state "running"
    }
}
