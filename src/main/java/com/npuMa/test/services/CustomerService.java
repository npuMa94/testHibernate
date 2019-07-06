package com.npuMa.test.services;

import java.util.List;
import com.npuMa.test.models.CustomerEntity;

public interface CustomerService {
	CustomerEntity findCustomer(int id);
    void saveCustomer(CustomerEntity customer);
    void deleteCustomer(CustomerEntity customer);
    void updateCustomer(CustomerEntity customer);
    List<CustomerEntity> findAllCustomers();
}
