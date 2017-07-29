package com.shop.order.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.shop.order.dao.OrderDao;
import com.shop.order.entity.Order;
import com.shop.utils.PageHibernateCallback;

@Component("orderDao")
@SuppressWarnings("unchecked")
public class OrderDaoImpl implements OrderDao {
	
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void save(Order order) {
		hibernateTemplate.save(order);
	}

	public Integer findByCountUid(Integer id) {
		String hql = "select count(*) from Order o where o.user.id = ?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, id);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}

	public List<Order> findByPageUid(Integer id, Integer begin, Integer eachPageCount) {
		String hql = "from Order o where o.user.id = ? order by ordertime desc";
		List<Order> list = hibernateTemplate.execute(new PageHibernateCallback<>(hql, new Object[] {id}, begin, eachPageCount));
		return list;
	}
	
}
