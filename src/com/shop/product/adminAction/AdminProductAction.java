package com.shop.product.adminAction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.categorySecond.entity.CategorySecond;
import com.shop.categorySecond.service.CategorySecondService;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;
import com.shop.utils.PageBean;

@SuppressWarnings("all")
@Component("adminProductAction")
@Scope("prototype")
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	private Integer page;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "categorySecondService")
	private CategorySecondService categorySecondService;

	private File upload;
	private String uploadFileName;
	private String uploadContextType;

	// 带分页的查询商品的执行的方法
	public String findAll() {
		// 调用Service完成查询操作
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将数据传递到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 跳转到添加页面的方法
	public String addPage() {
		// 查找所有的二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}

	// 保存商品的方法
	public String save() throws IOException {
		// 调用Service完成保存的操作
		product.setDate(new Date());
		if (upload != null) {
			// 获得文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建一个文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			// 文件上传
			FileUtils.copyFile(upload, diskFile); 
			product.setImage("products/"+uploadFileName);
		}
		// 将数据保存到数据库
		productService.save(product);
		// 页面跳转
		return "saveSuccess";
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Product getModel() {
		return product;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

}
