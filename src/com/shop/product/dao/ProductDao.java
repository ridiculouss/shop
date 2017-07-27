package com.shop.product.dao;

import java.util.List;

import com.shop.product.entity.Product;

public interface ProductDao {

	public List<Product> findHotProduct();

	public List<Product> findNewProduct();

	public Product findById(Integer id);

	public int findCountByCid(Integer cid);

	public List<Product> findByPageCid(Integer cid, int begin, int eachPageCount);

}
