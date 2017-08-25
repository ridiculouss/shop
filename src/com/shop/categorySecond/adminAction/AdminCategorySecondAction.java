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
	
	//��ѯ��������ķ���
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//��ת�����ҳ��
	public String addPage() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	
	//�����������ķ���
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	//ɾ����������ķ���
	public String delete() {
		//�������ɾ�����Ȳ�ѯ��ɾ��������cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCs_id());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//�༭��������ķ���
	public String editPage() {
		categorySecond = categorySecondService.findByCsid(categorySecond.getCs_id());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editPage";
	}
	
	//�޸Ķ�������ķ���
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
