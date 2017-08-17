package com.shop.categorySecond.adminAction;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.categorySecond.entity.CategorySecond;
import com.shop.categorySecond.service.CategorySecondService;
import com.shop.utils.PageBean;

@SuppressWarnings("serial")
@Component("adminCategorySecondAction")
@Scope("prototype")
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	private CategorySecond categorySecond = new CategorySecond();
	private Integer page;
	@Resource(name = "categorySecondService")
	private CategorySecondService categorySecondService;

	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}

}
