package com.shop.order.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.cart.entity.Cart;
import com.shop.cart.entity.CartItem;
import com.shop.order.entity.Order;
import com.shop.order.entity.OrderItem;
import com.shop.order.service.OrderService;
import com.shop.user.entity.User;
import com.shop.utils.PageBean;

@Component("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Order order = new Order();
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	private Integer page;
	
	
	//生成订单的方法
	public String save() {
		//1.保存到数据库
		order.setOrdertime(new Date());
		//1.未付款	2.已付款，但没有发货	3.已经发货,但未确认收货	4.交易完成
		order.setState(1);
		//从购物车中获得总计的信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲！您还没有购买任何商品！请先去挑选商品！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//设置订单所属用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("亲！请先进行登录");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//2.将订单对象显示到页面上
		//通过值栈的方式显示(因为Order显示的对象就是模型驱动的使用的对象)

		return "saveSuccess";
	}
	
	//我的订单的查询
	public String findByUid() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getId(),page);
		//将分页的数据显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	public Order getModel() {
		return order;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
