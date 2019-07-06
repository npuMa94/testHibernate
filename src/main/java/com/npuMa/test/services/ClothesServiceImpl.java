package com.npuMa.test.services;

import java.util.List;

import com.npuMa.test.dao.ClothesDao;
import com.npuMa.test.dao.ClothesDaoImpl;
import com.npuMa.test.models.ClothesEntity;


public class ClothesServiceImpl implements ClothesService {
	private ClothesDao clothesDao = new ClothesDaoImpl();

	public ClothesServiceImpl() {
	}
	
	public ClothesEntity findClothes(int id) {
		return clothesDao.findById(id);
	}
    public void saveClothes(ClothesEntity clothes){
    	clothesDao.save(clothes);
	}
    public void deleteClothes(ClothesEntity clothes){
    	clothesDao.delete(clothes);
	}
    public void updateClothes(ClothesEntity clothes){
    	clothesDao.update(clothes);
	}
    public List<ClothesEntity> findAllClothes(){
		return clothesDao.findAll();
	}

}
