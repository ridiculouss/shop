package com.shop.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shop.order.dao.OrderDao;
import com.shop.order.entity.Order;
import com.shop.order.service.OrderService;
import com.shop.utils.PageBean;

@Component("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Resource(name="orderDao")
	private OrderDao orderDao;

	public void save(Order order) {
		orderDao.save(order);
	}
	
	public PageBean<Order> findByPageUid(Integer id, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer eachPageCount = 5;
		pageBean.setEachPageCount(eachPageCount);
		//设置总记录数
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(id);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		Integer totalPage = null;
		if (totalCount % eachPageCount == 0) {
			totalPage = totalCount / eachPageCount;
		}else {
			totalPage = totalCount / eachPageCount +1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据集合
		Integer begin = (page - 1) * eachPageCount;
		List<Order> list = orderDao.findByPageUid(id,begin,eachPageCount);
		pageBean.setList(list);
		return null;
	}
}
