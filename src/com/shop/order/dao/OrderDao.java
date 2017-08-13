package com.shop.order.dao;

import java.util.List;

import com.shop.order.entity.Order;

public interface OrderDao {

	public void save(Order order);

	public Integer findByCountUid(Integer id);

	public List<Order> findByPageUid(Integer id, Integer begin, Integer eachPageCount);

	public Order findByOid(Integer oid);

	public void update(Order currOrder);

}
