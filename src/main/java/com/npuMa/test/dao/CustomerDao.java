package com.npuMa.test.dao;

import java.util.List;

import com.npuMa.test.models.CustomerEntity;

public interface CustomerDao {
    CustomerEntity findById(int id);
    void save(CustomerEntity customer);
    void update(CustomerEntity customer);
    void delete(CustomerEntity customer);
    List<CustomerEntity> findAll();
}
