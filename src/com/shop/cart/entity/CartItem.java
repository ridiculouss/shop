package com.shop.cart.entity;

import com.shop.product.entity.Product;

@SuppressWarnings("unused")
public class CartItem {
	private Product product;	//����������Ʒ��Ϣ
	private int count;			//����ĳ����Ʒ����
	private double subtotal;	//����ĳ����ƷС��
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//С���Զ�����
	public double getSubtotal() {
		return count * product.getShopPrice();
	}
}
