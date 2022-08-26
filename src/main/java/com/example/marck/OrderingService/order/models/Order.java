package com.example.marck.OrderingService.order.models;

public class Order {
    private Integer orderId;
    private String clientId;
    private State state;
    private String productName;
    private Integer quantity;

    // constructor for creating/saving new Orders
    public Order(String clientId, String productName, Integer quantity) {
        // TODO: Check how to handle orderId, which should only be set once SQL has generated it (auto increment)
        this.clientId = clientId;
        this.productName = productName;
        this.quantity = quantity;
        this.state = State.RUNNING;
    }

    public Order(Integer orderId, String clientId, String productName, Integer quantity, State state) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.productName = productName;
        this.quantity = quantity;
        this.state = state;
    }

    public Integer getOrderId() {
        return orderId;
    }

    // getters and setters
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
