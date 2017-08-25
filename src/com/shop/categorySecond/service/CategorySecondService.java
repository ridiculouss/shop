package com.shop.categorySecond.service;

import com.shop.categorySecond.entity.CategorySecond;
import com.shop.utils.PageBean;

public interface CategorySecondService {

	public PageBean<CategorySecond> findByPage(Integer page);

	public void save(CategorySecond categorySecond);

	public void delete(CategorySecond categorySecond);

	public CategorySecond findByCsid(Integer cs_id);

	public void update(CategorySecond categorySecond);

}
