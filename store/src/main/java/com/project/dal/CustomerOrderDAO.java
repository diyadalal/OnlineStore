package com.project.dal;

import com.project.model.CustomerOrder;
import java.util.List;

public interface CustomerOrderDAO {
    CustomerOrder findById(int orderId);
    List<CustomerOrder> findByCustomerId(int customerId);
    boolean insert(CustomerOrder order);
    boolean delete(int orderId);
}
