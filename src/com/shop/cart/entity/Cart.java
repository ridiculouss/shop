package com.shop.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//购物项集合(Map集合移除购物车时比较方便)
	//(KEY:商品ID,VALUE:购物项)
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer,CartItem>();
	
	//Cart对象中有一个叫cartItem属性
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//购物总计
	private double total;

	public double getTotal() {
		return total;
	}
	
	//购物车的功能:
	//1.将购物车项添加到购物车
	public void addCart(CartItem cartItem) {
		//判断购物车中是否已经存在该购物项
		/*
		 * 	*如果存在:
		 * 		*数量增加
		 * 		*总计 = 总计  + 购物项小计
		 * 	*如果不存在:
		 * 		*向map中添加购物项
		 * 		*总计 = 总计 + 购物项小计
		 */
		//获得商品ID
		Integer pid = cartItem.getProduct().getId();
		//判断购物车中是否已将存在该购物项
		if (map.containsKey(pid)) {
			// 存在
			//购物车中原来的购物项
			CartItem oldCartItem = map.get(pid);
			oldCartItem.setCount(oldCartItem.getCount()+cartItem.getCount());
		}else {
			//不存在
			map.put(pid, cartItem);
		}
		//设置总计的值
		total += cartItem.getSubtotal();
	}
	//2.从购物车移除购物项
	public void removeCart(Integer pid) {
		//将购物项移除购物车
		CartItem cartItem = map.remove(pid);
		//总计 = 总计 - 移除的购物项小计
		total -= cartItem.getSubtotal();
	}
	//3.清空购物车
	public void clearCart() {
		//将所有购物项清空
		map.clear();
		//将总计设置为0
		total = 0;
	}
}
