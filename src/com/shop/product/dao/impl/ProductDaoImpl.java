package com.shop.product.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.shop.product.dao.ProductDao;
import com.shop.product.entity.Product;
import com.shop.utils.PageHibernateCallback;

@Component("productDao")
@SuppressWarnings("unchecked")
public class ProductDaoImpl implements ProductDao{
	
	@Resource
	private HibernateTemplate hibernateTemplate;

	//首页热门商品查询
	public List<Product> findHotProduct() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询热门的商品，条件就是 is_hot = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序输出
		criteria.addOrder(Order.desc("date"));
		//执行查询(findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults))
		List<Product> list = (List<Product>) hibernateTemplate.findByCriteria(criteria, 0, 10);
		return list;
	}

	//首页最新商品查询
	public List<Product> findNewProduct() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//按日期进行倒序排序
		criteria.addOrder(Order.desc("date"));
		//执行查询
		List<Product> list = (List<Product>) hibernateTemplate.findByCriteria(criteria, 0, 10);
		return list;
	}

	//根据ID查询商品
	public Product findById(Integer id) {
		return hibernateTemplate.get(Product.class, id);
	}
	
	//根据分类ID查询所有商品的总数
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.c_id = ?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//根据分类ID查询商品 (分页显示)
	public List<Product> findByPageCid(Integer cid, int begin, int eachPageCount) {
		//SQL语句查询	select p.* from category c,categorysecond cs,product p where c.c_id = cs.c_id and cs.cs_id = p.cs_id and c.c_id = 2
		//HQL语句查询	select p from Category c,CategorySecond cs,Product p where c.c_id = cs.category.c_id and cs.cs_id = p.categorySecond.cs_id and c.c_id = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.c_id = ?";
		//分页查询
		List<Product> list = hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[] {cid}, begin, eachPageCount));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.cs_id = ?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int eachPageCount) {
		String hql = "select p from Product p join p.categorySecond cs where cs.cs_id = ?";
		List<Product> list = hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, eachPageCount));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
}
