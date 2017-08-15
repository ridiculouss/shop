package com.shop.category.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.shop.category.dao.CategoryDao;
import com.shop.category.entity.Category;

@SuppressWarnings("unchecked")
@Component("categoryDao")
public class CategoryDaoImpl implements CategoryDao{
	
	@Resource
	private HibernateTemplate hibernateTemplate;


	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = (List<Category>) hibernateTemplate.find(hql);
		return list;
	}

	public void save(Category category) {
		hibernateTemplate.save(category);
	}
	
}
