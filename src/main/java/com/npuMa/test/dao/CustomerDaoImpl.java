package com.npuMa.test.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.npuMa.test.models.CustomerEntity;
import com.npuMa.test.utils.HibernateSessionFactoryUtil;



public class CustomerDaoImpl implements CustomerDao{
	@Override
	public CustomerEntity findById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CustomerEntity customer = session.get(CustomerEntity.class, id);
        session.close();
        return customer;
	}
	
	@Override
	public void save(CustomerEntity customer) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		
		Transaction tx1 = session.beginTransaction();
		session.save(customer);
		tx1.commit();
		session.close();
	}
	
	@Override
    public void update(CustomerEntity customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(customer);
        tx1.commit();
        session.close();
    }
	
	@Override
	public void delete(CustomerEntity customer) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(customer);
		tx1.commit();
		session.close();
	}
		
	@Override
	public List<CustomerEntity> findAll(){
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<CustomerEntity> criteriaQuery = session.getCriteriaBuilder().createQuery(CustomerEntity.class);
        criteriaQuery.from(CustomerEntity.class);
        List<CustomerEntity> customers = session.createQuery(criteriaQuery).getResultList();
        session.close();
		return customers;
	}
}
