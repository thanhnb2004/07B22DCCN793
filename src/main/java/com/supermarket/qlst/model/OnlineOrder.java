package com.supermarket.qlst.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class OnlineOrder {
    public enum Status {
        PENDING,
        APPROVED,
        DELIVERED
    }

    private final String onlineOrderCode;
    private Customer customer;
    private WarehouseStaff warehouseStaff;
    private DeliveryStaff deliveryStaff;
    private final List<OrderItem> items = new ArrayList<>();
    private double totalAmount;
    private Status status = Status.PENDING;
    private LocalDate orderDate = LocalDate.now();
    private String deliveryAddress;
    private String note;

    public OnlineOrder() {
        this.onlineOrderCode = UUID.randomUUID().toString();
    }

    public String getOnlineOrderCode() {
        return onlineOrderCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WarehouseStaff getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public DeliveryStaff getDeliveryStaff() {
        return deliveryStaff;
    }

    public void setDeliveryStaff(DeliveryStaff deliveryStaff) {
        this.deliveryStaff = deliveryStaff;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItem item) {
        items.add(item);
        recalculateTotalAmount();
    }

    private void recalculateTotalAmount() {
        this.totalAmount = items.stream().mapToDouble(i -> i.getUnitPrice() * i.getQuantity()).sum();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
