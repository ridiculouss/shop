package com.shop.categorySecond.adminAction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.category.entity.Category;
import com.shop.category.service.CategoryService;
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
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	//查询二级分类的方法
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到添加页面
	public String addPage() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	
	//保存二级分类的方法
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	//删除二级分类的方法
	public String delete() {
		//如果级联删除，先查询再删除，配置cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCs_id());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//编辑二级分类的方法
	public String editPage() {
		categorySecond = categorySecondService.findByCsid(categorySecond.getCs_id());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editPage";
	}
	
	//修改二级分类的方法
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}

}
