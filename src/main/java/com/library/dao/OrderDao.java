package com.library.dao;

import com.library.ConnectionPool;
import com.library.dao.interfaces.IOrderDao;
import com.library.entities.Book;
import com.library.entities.Order;
import com.library.entities.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    protected Connection connection = null;
    protected final Logger logger = LogManager.getLogger("Order Dao");
    protected final String findAllSqlQuery = "select * from `" + getTableName() + "`";
    protected final String findByIdSqlQuery = "select * from `" + getTableName() + "` where order_id=?";
    protected final String findByReaderSqlQuery = "select * from `" + getTableName() + "` where reader_id=?";
    protected final String findByStatusSqlQuery = "select * from `" + getTableName() + "` where status=?";
    protected final String saveSqlQuery = "insert into `" + getTableName() +
            "` (status, start_date, end_date, reader_id, book_id)" + " values (\"ongoing\", ?, ?, ?, ?)";
    protected final String deleteSqlQuery = "delete from `" + getTableName() + "` where order_id=?";

    public OrderDao() {
        try {
            if (this.connection == null || this.connection.isClosed())
                this.connection = ConnectionPool.createConnection();
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger.warn(exception);
        }
    }

    String getTableName() {
        return "order";
    }

    private Order getOrder(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong("order_id");
        OrderStatus status = OrderStatus.valueOf(resultSet.getString("status"));
        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        long readerId = resultSet.getLong("reader_id");
        long bookId = resultSet.getLong("book_id");
        return new Order(orderId, status, startDate, endDate, readerId, bookId);
    }

    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findAllSqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrder(resultSet);
                orderList.add(order);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return orderList;
    }

    public Order findById(long id) {
        Order order = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByIdSqlQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = getOrder(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return order;
    }

    public List<Order> findByReader(long readerId) {
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByReaderSqlQuery);
            preparedStatement.setLong(1, readerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrder(resultSet);
                orderList.add(order);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return orderList;
    }

    public List<Order> findByStatus(OrderStatus status) {
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByStatusSqlQuery);
            preparedStatement.setString(1, status.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = getOrder(resultSet);
                orderList.add(order);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return orderList;
    }


    public long save(LocalDate startDate, LocalDate endDate, long readerId, long bookId) {
        long rowsAffected = 0;
        try {
            BookDao bookDao = DaoFactory.getBookDao();
            Book book = bookDao.findById(bookId);
            if (book == null || book.getReader() != 0) throw new Exception("Book is not available");
            if (ChronoUnit.DAYS.between(startDate, endDate) > 30 || ChronoUnit.DAYS.between(startDate, endDate) < 0)
                throw new Exception("Invalid date period");
            PreparedStatement preparedStatement = this.connection.prepareStatement(saveSqlQuery);
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            preparedStatement.setLong(3, readerId);
            preparedStatement.setLong(4, bookId);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            bookDao.update(bookId, "reader", readerId);
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public long update(long id, String property, String value) {
        final String updateSqlQuery = "update `" + getTableName() + "` set " + property + " = ? where order_id = ?";
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateSqlQuery);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, id);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public void markAsReturned(long id) {
        BookDao bookDao = DaoFactory.getBookDao();
        this.update(id, "status", OrderStatus.returned.toString());
        bookDao.update(this.findById(id).getBookId(), "reader", 0);
    }

    public void markAsLate(long id) {
        Order order = this.findById(id);
        if (order != null && order.getStatus() == OrderStatus.ongoing && order.getEndDate().isBefore(LocalDate.now())) {
            this.update(id, "status", OrderStatus.late.toString());
        }
    }

    public void delete(long id) {
        try {
            Order order = this.findById(id);
            if (order.getStatus() != OrderStatus.returned)
                throw new Exception("Cannot delete order that is not completed");
            PreparedStatement preparedStatement = this.connection.prepareStatement(deleteSqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception exception) {
            logger.error(exception);
        }
    }
}
