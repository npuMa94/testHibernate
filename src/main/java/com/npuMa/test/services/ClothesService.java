package com.npuMa.test.services;

import java.util.List;

import com.npuMa.test.models.ClothesEntity;


public interface ClothesService {
	ClothesEntity findClothes(int id);
    void saveClothes(ClothesEntity clothes);
    void updateClothes(ClothesEntity clothes);
    void deleteClothes(ClothesEntity clothes);
    List<ClothesEntity> findAllClothes();

}
