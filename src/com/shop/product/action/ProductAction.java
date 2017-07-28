package com.shop.product.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.category.service.CategoryService;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;
import com.shop.utils.PageBean;

@Component("productAction")
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Product product = new Product();
	
	@Resource(name="productService")
	private ProductService productService;
	
	//注入一级分类的Service
	@Resource(name="categoryService")
	private CategoryService categoryService;
		
	private Integer cid;
	private int page;
	private Integer csid;
	
	
	//根据商品的ID查询商品
	public String findById() {
		product = productService.findById(product.getId());
		return "findById";
	}
	
	//根据一级分类的ID查询商品
	public String findByCid() {
		/*	//查询所有一级分类集合
		 *	List<Category> cList = categoryService.findAll();
		 *	//将一级分类集合保存到值栈中
		 *	ActionContext.getContext().getValueStack().set("cList", cList);
		 *	
		 *	可以用这种查找的写法也可以不用，因为index已经往session中设置了一级分类的集合
		 */
		//根据一级分类查询商品(分页查询)
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);
		//将pageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findByCid";
	}
	
	//根据二级分类的ID查询商品
	public String findByCsid() {
		//根据二级分类查询商品(分页查询)
		PageBean<Product> pageBean = productService.findByCsid(csid,page);
		//将pageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findByCsid";
	}
	
	

	
	public Product getModel() {
		return product;
	}	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
}
