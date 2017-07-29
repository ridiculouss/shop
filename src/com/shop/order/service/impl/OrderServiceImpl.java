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
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		Integer eachPageCount = 5;
		pageBean.setEachPageCount(eachPageCount);
		//�����ܼ�¼��
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(id);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		Integer totalPage = null;
		if (totalCount % eachPageCount == 0) {
			totalPage = totalCount / eachPageCount;
		}else {
			totalPage = totalCount / eachPageCount +1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿҳ��ʾ���ݼ���
		Integer begin = (page - 1) * eachPageCount;
		List<Order> list = orderDao.findByPageUid(id,begin,eachPageCount);
		pageBean.setList(list);
		return null;
	}
}
