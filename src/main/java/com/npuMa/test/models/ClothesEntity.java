package com.npuMa.test.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table (name = "clothes")
public class ClothesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "kind")
	private String kind;
	@Column(name = "price")
	private int price;
	@Column(name = "cust_id", insertable = false, updatable = false)
	private int cust_id;
//	(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private CustomerEntity customer;
	
	public ClothesEntity() {
	}
		
	public ClothesEntity(String kind, int price) {
		this.kind = kind;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
		
	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
	public CustomerEntity getCustomer() {
		return this.customer;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothesEntity clothes = (ClothesEntity) o;
        return id == clothes.id &&
                price == clothes.price &&
                kind.equals(clothes.kind) &&
                customer.equals(clothes.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kind, price, customer);
    }
    
    @Override
    public String toString() {
    	return "models.ClothesEntity{id = " + id + ", kind = " + kind + ", price = " + price + ", customer = " + customer  + "}";
    }
}
