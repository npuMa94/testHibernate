package com.npuMa.test.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table (name = "customer")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "phone")
	private String phone;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClothesEntity> clothesList;
	public CustomerEntity() {
	}
	
	public CustomerEntity(String name, String phone) {
		this.name = name;
		this.phone = phone;
		clothesList = new ArrayList<>();
		
	}
	
	public void addClothes(ClothesEntity clothes) {
		clothes.setCustomer(this);
		clothesList.add(clothes);
	}
	
	public void removeClothes(ClothesEntity clothes) {
		clothesList.remove(clothes);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<ClothesEntity> getClothes(){
		return clothesList;
	}
	
	public void setClothes(List<ClothesEntity> clothesList) {
		this.clothesList = clothesList;
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity customer = (CustomerEntity) o;
        return id == customer.id &&
                phone == customer.phone &&
                name.equals(customer.name) &&
                Objects.equals(clothesList, customer.clothesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, clothesList);
    }
    
	@Override 
	public String toString() {
		return "models.CustomerEntity{id = " + id + ", name = " + name + ", phone = " + phone + "}";
	}
	
}

