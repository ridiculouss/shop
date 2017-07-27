package com.shop.category.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shop.category.dao.CategoryDao;
import com.shop.category.entity.Category;
import com.shop.category.service.CategoryService;

@Component("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;


	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
	
}
