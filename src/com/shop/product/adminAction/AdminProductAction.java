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

	// ����ҳ�Ĳ�ѯ��Ʒ��ִ�еķ���
	public String findAll() {
		// ����Service��ɲ�ѯ����
		PageBean<Product> pageBean = productService.findByPage(page);
		// �����ݴ��ݵ�ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// ��ת�����ҳ��ķ���
	public String addPage() {
		// �������еĶ�������ļ���
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}

	// ������Ʒ�ķ���
	public String save() throws IOException {
		// ����Service��ɱ���Ĳ���
		product.setDate(new Date());
		if (upload != null) {
			// ����ļ��ϴ��Ĵ��̾���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// ����һ���ļ�
			File diskFile = new File(realPath+"//"+uploadFileName);
			// �ļ��ϴ�
			FileUtils.copyFile(upload, diskFile); 
			product.setImage("products/"+uploadFileName);
		}
		// �����ݱ��浽���ݿ�
		productService.save(product);
		// ҳ����ת
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
