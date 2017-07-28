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

	//��ҳ������Ʒ��ѯ
	public List<Product> findHotProduct() {
		//ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//��ѯ���ŵ���Ʒ���������� is_hot = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		//�����������
		criteria.addOrder(Order.desc("date"));
		//ִ�в�ѯ(findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults))
		List<Product> list = (List<Product>) hibernateTemplate.findByCriteria(criteria, 0, 10);
		return list;
	}

	//��ҳ������Ʒ��ѯ
	public List<Product> findNewProduct() {
		//ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//�����ڽ��е�������
		criteria.addOrder(Order.desc("date"));
		//ִ�в�ѯ
		List<Product> list = (List<Product>) hibernateTemplate.findByCriteria(criteria, 0, 10);
		return list;
	}

	//����ID��ѯ��Ʒ
	public Product findById(Integer id) {
		return hibernateTemplate.get(Product.class, id);
	}
	
	//���ݷ���ID��ѯ������Ʒ������
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.c_id = ?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//���ݷ���ID��ѯ��Ʒ (��ҳ��ʾ)
	public List<Product> findByPageCid(Integer cid, int begin, int eachPageCount) {
		//SQL����ѯ	select p.* from category c,categorysecond cs,product p where c.c_id = cs.c_id and cs.cs_id = p.cs_id and c.c_id = 2
		//HQL����ѯ	select p from Category c,CategorySecond cs,Product p where c.c_id = cs.category.c_id and cs.cs_id = p.categorySecond.cs_id and c.c_id = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.c_id = ?";
		//��ҳ��ѯ
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
