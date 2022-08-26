package com.example.marck.OrderingService.order.models;

public enum State {
    RUNNING("running"),
    DELIVERED("delivered"),
    CANCELLED("cancelled");

    private final String label;

    State(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
