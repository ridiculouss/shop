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
		//���õ�ǰ��ҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ��¼��
		int eachPageCount = 12;
		pageBean.setEachPageCount(eachPageCount);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		//  (����ȡ��)	  int totalPage = (int) Math.ceil(totalCount / eachPageCount);
		int totalPage = 0;
		if(totalCount % eachPageCount == 0) {
			totalPage = totalCount / eachPageCount;
		}else {
			totalPage = totalCount / eachPageCount + 1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݼ��� 
		int begin = (page - 1) * eachPageCount;
		List<Product> list = productDao.findByPageCid(cid,begin,eachPageCount);
		pageBean.setList(list);
		return pageBean;
	}
	
}
