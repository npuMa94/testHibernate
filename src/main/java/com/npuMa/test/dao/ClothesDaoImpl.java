package com.npuMa.test.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.npuMa.test.models.ClothesEntity;
import com.npuMa.test.utils.HibernateSessionFactoryUtil;


public class ClothesDaoImpl implements ClothesDao{
	@Override
	public ClothesEntity findById(int id) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        ClothesEntity clothes = session.get(ClothesEntity.class, id);
        session.close();
        return clothes;
	}
	
	@Override
	public void save(ClothesEntity clothes) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.save(clothes);
		tx1.commit();
		session.close();
	}
	
	@Override
    public void update(ClothesEntity clothes) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(clothes);
        tx1.commit();
        session.close();
    }
	
	@Override
	public void delete(ClothesEntity clothes) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction tx1 = session.beginTransaction();
		session.delete(clothes);
		tx1.commit();
		session.close();
	}
		
	@Override
	public List<ClothesEntity> findAll(){
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaQuery<ClothesEntity> criteriaQuery = session.getCriteriaBuilder().createQuery(ClothesEntity.class);
        criteriaQuery.from(ClothesEntity.class);
        List<ClothesEntity> clothes = session.createQuery(criteriaQuery).getResultList();
        session.close();
		return clothes;
	}

}


