package com.shop.product.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.category.service.CategoryService;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;
import com.shop.utils.PageBean;

@Component("productAction")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Product product = new Product();
	
	@Resource(name="productService")
	private ProductService productService;
	
	//ע��һ�������Service
	@Resource(name="categoryService")
	private CategoryService categoryService;
		
	private Integer cid;
	private int page;
	
	
	//������Ʒ��ID��ѯ��Ʒ
	public String findById() {
		product = productService.findById(product.getId());
		return "findById";
	}
	
	//���ݷ����ID��ѯ��Ʒ
	public String findByCid() {
		/**
		 *	//��ѯ����һ�����༯��
		 *	List<Category> cList = categoryService.findAll();
		 *	//��һ�����༯�ϱ��浽ֵջ��
		 *	ActionContext.getContext().getValueStack().set("cList", cList);
		 *	
		 *	���������ֲ��ҵ�д��Ҳ���Բ��ã���Ϊindex�Ѿ���session��������һ������ļ���
		 */
		//����һ�������ѯ��Ʒ(��ҳ��ѯ)
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);
		//��pageBean����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCid";
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
	
}