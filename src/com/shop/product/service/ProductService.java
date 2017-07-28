package com.shop.product.service;

import java.util.List;

import com.shop.product.entity.Product;
import com.shop.utils.PageBean;

public interface ProductService {

	public List<Product> findHotProduct();

	public List<Product> findNewProduct();

	public Product findById(Integer id);

	public PageBean<Product> findByPageCid(Integer cid, int page);

	public PageBean<Product> findByCsid(Integer csid, int page);

}
