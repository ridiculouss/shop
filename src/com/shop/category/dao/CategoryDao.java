package com.shop.category.dao;

import java.util.List;

import com.shop.category.entity.Category;

public interface CategoryDao {

	public List<Category> findAll();

	public void save(Category category);

}
