package com.npuMa.test.dao;

import java.util.List;

import com.npuMa.test.models.ClothesEntity;

public interface ClothesDao {
	ClothesEntity findById(int id);
    void save(ClothesEntity clothes);
    void update(ClothesEntity clothes);
    void delete(ClothesEntity clothes);
    List<ClothesEntity> findAll();
}
