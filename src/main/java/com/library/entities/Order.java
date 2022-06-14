package com.library.entities;

import java.time.LocalDate;

public class Order {
    private long orderId;
    private OrderStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private long readerId;
    private long bookId;

    public Order(long id, LocalDate startDate, LocalDate endDate, long readerId, long bookId){
        this.orderId = id;
        this.status = OrderStatus.ongoing;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readerId = readerId;
        this.bookId = bookId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String toString(){
        return this.getOrderId() + "\t" + this.getStatus() + "\t" + this.getStartDate() + "\t"
                + this.getEndDate() + "\t" + this.getBookId() + "\t" + this.getReaderId();
    }
}
