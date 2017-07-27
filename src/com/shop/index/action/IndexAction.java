package com.shop.index.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shop.category.entity.Category;
import com.shop.category.service.CategoryService;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;

@Component("indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	@Resource(name="productService")
	private ProductService productService;
	
	

	/**
	 * ������ҳ��ִ�з���
	 */
	public String execute() throws Exception {
		//��ѯ����һ�����༯��
		List<Category> cList = categoryService.findAll();
		//��һ��������뵽Session�ķ�Χ  (ActionContext)
		ActionContext.getContext().getSession().put("cList", cList);
		
		//��ѯ������Ʒ
		List<Product> hList = productService.findHotProduct();
		//��������Ʒ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//��ѯ������Ʒ
		List<Product> nList = productService.findNewProduct();
		//��������Ʒ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}
}
