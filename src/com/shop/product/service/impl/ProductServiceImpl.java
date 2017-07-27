package com.shop.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shop.product.dao.ProductDao;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;
import com.shop.utils.PageBean;

@Component("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Resource(name="productDao")
	private ProductDao productDao;

	
	public List<Product> findHotProduct() {
		return productDao.findHotProduct();
	}

	public List<Product> findNewProduct() {
		return productDao.findNewProduct();
	}

	public Product findById(Integer id) {
		return productDao.findById(id);
	}

	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前的页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int eachPageCount = 12;
		pageBean.setEachPageCount(eachPageCount);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		//  (向上取整)	  int totalPage = (int) Math.ceil(totalCount / eachPageCount);
		int totalPage = 0;
		if(totalCount % eachPageCount == 0) {
			totalPage = totalCount / eachPageCount;
		}else {
			totalPage = totalCount / eachPageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合 
		int begin = (page - 1) * eachPageCount;
		List<Product> list = productDao.findByPageCid(cid,begin,eachPageCount);
		pageBean.setList(list);
		return pageBean;
	}
	
}
