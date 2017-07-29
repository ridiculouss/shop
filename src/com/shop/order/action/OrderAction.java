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
	
	
	//���ɶ����ķ���
	public String save() {
		//1.���浽���ݿ�
		order.setOrdertime(new Date());
		//1.δ����	2.�Ѹ����û�з���	3.�Ѿ�����,��δȷ���ջ�	4.�������
		order.setState(1);
		//�ӹ��ﳵ�л���ܼƵ���Ϣ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("�ף�����û�й����κ���Ʒ������ȥ��ѡ��Ʒ��");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//���ö����еĶ�����
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//���ö��������û�
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("�ף����Ƚ��е�¼");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//2.������������ʾ��ҳ����
		//ͨ��ֵջ�ķ�ʽ��ʾ(��ΪOrder��ʾ�Ķ������ģ��������ʹ�õĶ���)

		return "saveSuccess";
	}
	
	//�ҵĶ����Ĳ�ѯ
	public String findByUid() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getId(),page);
		//����ҳ��������ʾ��ҳ����
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
