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
	//�������(Map�����Ƴ����ﳵʱ�ȽϷ���)
	//(KEY:��ƷID,VALUE:������)
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer,CartItem>();
	
	//Cart��������һ����cartItem����
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//�����ܼ�
	private double total;

	public double getTotal() {
		return total;
	}
	
	//���ﳵ�Ĺ���:
	//1.�����ﳵ����ӵ����ﳵ
	public void addCart(CartItem cartItem) {
		//�жϹ��ﳵ���Ƿ��Ѿ����ڸù�����
		/*
		 * 	*�������:
		 * 		*��������
		 * 		*�ܼ� = �ܼ�  + ������С��
		 * 	*���������:
		 * 		*��map����ӹ�����
		 * 		*�ܼ� = �ܼ� + ������С��
		 */
		//�����ƷID
		Integer pid = cartItem.getProduct().getId();
		//�жϹ��ﳵ���Ƿ��ѽ����ڸù�����
		if (map.containsKey(pid)) {
			// ����
			//���ﳵ��ԭ���Ĺ�����
			CartItem oldCartItem = map.get(pid);
			oldCartItem.setCount(oldCartItem.getCount()+cartItem.getCount());
		}else {
			//������
			map.put(pid, cartItem);
		}
		//�����ܼƵ�ֵ
		total += cartItem.getSubtotal();
	}
	//2.�ӹ��ﳵ�Ƴ�������
	public void removeCart(Integer pid) {
		//���������Ƴ����ﳵ
		CartItem cartItem = map.remove(pid);
		//�ܼ� = �ܼ� - �Ƴ��Ĺ�����С��
		total -= cartItem.getSubtotal();
	}
	//3.��չ��ﳵ
	public void clearCart() {
		//�����й��������
		map.clear();
		//���ܼ�����Ϊ0
		total = 0;
	}
}
