package com.shop.categorySecond.service;

import com.shop.categorySecond.entity.CategorySecond;
import com.shop.utils.PageBean;

public interface CategorySecondService {

	public PageBean<CategorySecond> findByPage(Integer page);

}
