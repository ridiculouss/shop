package com.shop.category.entity;

import java.util.HashSet;
import java.util.Set;

import com.shop.categorySecond.entity.CategorySecond;

/**
 * һ�������ʵ����
 *
 */
public class Category {
	private Integer c_id;
	private String c_name;
	//һ���д�Ŷ�������ļ���
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	
	public Integer getC_id() {
		return c_id;
	}
	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
}
