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
	 * 访问首页的执行方法
	 */
	public String execute() throws Exception {
		//查询所有一级分类集合
		List<Category> cList = categoryService.findAll();
		//将一级分类存入到Session的范围  (ActionContext)
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品
		List<Product> hList = productService.findHotProduct();
		//将热门商品保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新商品
		List<Product> nList = productService.findNewProduct();
		//将最新商品保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}
}
