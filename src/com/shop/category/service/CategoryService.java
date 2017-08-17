package com.shop.category.service;

import java.util.List;

import com.shop.category.entity.Category;

public interface CategoryService {

	public List<Category> findAll() ;

	public void save(Category category);

	public Category findByCid(Integer cid);

	public void delete(Category category);

	public void update(Category category);

}
