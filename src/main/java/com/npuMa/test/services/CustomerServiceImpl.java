package com.npuMa.test.services;

import java.util.List;

import com.npuMa.test.dao.CustomerDao;
import com.npuMa.test.dao.CustomerDaoImpl;
import com.npuMa.test.models.CustomerEntity;


public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao = new CustomerDaoImpl();

	public CustomerServiceImpl() {
	}
	
	public CustomerEntity findCustomer(int id) {
		return customerDao.findById(id);
	}
    public void saveCustomer(CustomerEntity customer){
		customerDao.save(customer);
	}
    public void deleteCustomer(CustomerEntity customer){
		customerDao.delete(customer);
	}
    public void updateCustomer(CustomerEntity customer){
		customerDao.update(customer);
	}
    public List<CustomerEntity> findAllCustomers(){
		return customerDao.findAll();
	}

}
