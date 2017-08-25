package com.shop.categorySecond.dao;

import java.util.List;

import com.shop.categorySecond.entity.CategorySecond;

public interface CategorySecondDao {

	public int findCount();

	public List<CategorySecond> findByPage(int begin, int limit);

	public void save(CategorySecond categorySecond);

	public void delete(CategorySecond categorySecond);

	public CategorySecond findByCsid(Integer cs_id);

	public void update(CategorySecond categorySecond);

}
