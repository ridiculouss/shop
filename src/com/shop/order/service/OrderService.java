package com.shop.order.service;

import com.shop.order.entity.Order;
import com.shop.utils.PageBean;

public interface OrderService {

	public void save(Order order);

	public PageBean<Order> findByPageUid(Integer id, Integer page);

	public Order findByOid(Integer oid);

	public void update(Order currOrder);

}
