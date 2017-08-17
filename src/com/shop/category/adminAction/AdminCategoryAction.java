package com.shop.category.adminAction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.category.entity.Category;
import com.shop.category.service.CategoryService;

@SuppressWarnings("serial")
@Component("adminCategoryAction")
@Scope("prototype")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private Category category = new Category();
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	//��ִ̨�в�ѯ����һ������ķ���
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//��̨����һ������ķ���
	public String save() {
		categoryService.save(category);
		return "saveSuccess";
	}
	
	//��̨ɾ��һ������ķ���
	public String delete() {
		category = categoryService.findByCid(category.getC_id());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	//��̨�༭һ��������ת
	public String edit() {
		category = categoryService.findByCid(category.getC_id());
		return "editSuccess";
	}
	
	//��̨�޸�һ������ķ���
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
	
	public Category getModel() {
		return category;
	}
}
