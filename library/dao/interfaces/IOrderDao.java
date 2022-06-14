package com.library.dao.interfaces;

import com.library.entities.Order;
import com.library.entities.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface IOrderDao {
    List<Order> findAll() throws Exception;

    Order findById(long id) throws Exception;

    List<Order> findByReader(long readerId) throws Exception;

    List<Order> findByStatus(OrderStatus status) throws Exception;

    long save(LocalDate startDate, LocalDate endDate, long readerId, long bookId) throws Exception;

    void markAsReturned(long id);

    void markAsLate(long id);

    void delete(long id);
}
