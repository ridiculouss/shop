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

	//将购物项添加到购物车的执行方法
	public String addCart() {
		//封装一个CartItem对象
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findById(pid);
		cartItem.setProduct(product);
		//购物车应该存入session
		Cart cart = getCart();
		cart.addCart(cartItem);
		
		return "addCart";
	}
	
	//从购物车中移除购物项的方法
	public String removeCart() {
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	//清空购物车的执行方法
	public String clearCart() {
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	//跳转到我的购物车的执行方法
	public String myCart() {
		return "myCart";
	}
	
	/**
	 * 从session中获得购物车
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
