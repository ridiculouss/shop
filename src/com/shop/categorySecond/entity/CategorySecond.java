package com.shop.categorySecond.entity;

import java.util.HashSet;
import java.util.Set;

import com.shop.category.entity.Category;
import com.shop.product.entity.Product;

public class CategorySecond {
	private Integer cs_id;
	private String cs_name;
	//所属一级分类ID
	private Category category;
	private Set<Product> products = new HashSet<Product>();
	
	public Integer getCs_id() {
		return cs_id;
	}
	public void setCs_id(Integer cs_id) {
		this.cs_id = cs_id;
	}
	public String getCs_name() {
		return cs_name;
	}
	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
