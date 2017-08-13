package com.shop.order.action;

import java.io.IOException;
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
import com.shop.utils.PaymentUtil;

@Component("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Order order = new Order();

	@Resource(name = "orderService")
	private OrderService orderService;

	private Integer page;
	// 接受支付通道编码
	private String pd_FrpId;
	//接收付款成功后响应的数据
	private String r3_Amt;
	private String r6_Order;
	// 生成订单的方法
	public String save() {
		// 1.保存到数据库
		order.setOrdertime(new Date());
		// 1.未付款 2.已付款，但没有发货 3.已经发货,但未确认收货 4.交易完成
		order.setState(1);
		// 从购物车中获得总计的信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲！您还没有购买任何商品！请先去挑选商品！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// 设置订单所属用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("亲！请先进行登录");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 2.将订单对象显示到页面上
		// 通过值栈的方式显示(因为Order显示的对象就是模型驱动的使用的对象)

		return "saveSuccess";
	}

	// 我的订单的查询
	public String findByUid() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getId(), page);
		// 将分页的数据显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	// 根据订单的id查询订单的方法
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}

	// 为订单付款的方法
	public String payOrder() throws IOException {
		// 修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddress(order.getAddress());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// 为订单付款
		String p0_Cmd = "Buy"; // 业务类型
		String p1_MerId = "10001126856"; // 商户编号
		String p2_Order = order.getOid().toString(); // 商户订单号
		String p3_Amt = "0.01"; // 支付金额
		String p4_Cur = "CNY"; // 交易币种
		String p5_Pid = ""; // 商品名称
		String p6_Pcat = ""; // 商品种类
		String p7_Pdesc = ""; // 商品描述
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; // 商户接收支付成功后跳转的页面路径
		String p9_SAF = ""; // 送货地址
		String pa_MP = ""; // 商户扩展信息
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1"; // 应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue ); // 签名数据
		//向易宝发送:
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		//重定向到易宝:
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	//付款成功后的转向
	public String callBack() {
		//修改订单状态：修改状态为已付款
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		//在页面显示付款成功信息！
		this.addActionMessage("订单付款成功！    订单编号："+r6_Order+"订单金额："+r3_Amt);
		return "msg";
	}
	public Order getModel() {
		return order;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	
}
