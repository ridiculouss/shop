package com.shop.categorySecond.dao;

import java.util.List;

import com.shop.categorySecond.entity.CategorySecond;

public interface CategorySecondDao {

	public int findCount();

	public List<CategorySecond> findByPage(int begin, int limit);

}
