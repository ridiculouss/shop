package com.shop.cart.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.cart.entity.Cart;
import com.shop.cart.entity.CartItem;
import com.shop.product.entity.Product;
import com.shop.product.service.ProductService;

@Component("cartAction")
@Scope("prototype")
public class CartAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="productService")
	private ProductService productService;
	private Integer pid;
	private Integer count;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	//����������ӵ����ﳵ��ִ�з���
	public String addCart() {
		//��װһ��CartItem����
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findById(pid);
		cartItem.setProduct(product);
		//���ﳵӦ�ô���session
		Cart cart = getCart();
		cart.addCart(cartItem);
		
		return "addCart";
	}
	
	//�ӹ��ﳵ���Ƴ�������ķ���
	public String removeCart() {
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	//��չ��ﳵ��ִ�з���
	public String clearCart() {
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	//��ת���ҵĹ��ﳵ��ִ�з���
	public String myCart() {
		return "myCart";
	}
	
	/**
	 * ��session�л�ù��ﳵ
	 * @return
	 */
	private Cart getCart() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Cart cart = (Cart) session.getAttribute("cart") ;
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
