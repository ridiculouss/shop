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
	// ����֧��ͨ������
	private String pd_FrpId;
	//���ո���ɹ�����Ӧ������
	private String r3_Amt;
	private String r6_Order;
	// ���ɶ����ķ���
	public String save() {
		// 1.���浽���ݿ�
		order.setOrdertime(new Date());
		// 1.δ���� 2.�Ѹ����û�з��� 3.�Ѿ�����,��δȷ���ջ� 4.�������
		order.setState(1);
		// �ӹ��ﳵ�л���ܼƵ���Ϣ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("�ף�����û�й����κ���Ʒ������ȥ��ѡ��Ʒ��");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// ���ö����еĶ�����
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// ���ö��������û�
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("�ף����Ƚ��е�¼");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 2.������������ʾ��ҳ����
		// ͨ��ֵջ�ķ�ʽ��ʾ(��ΪOrder��ʾ�Ķ������ģ��������ʹ�õĶ���)

		return "saveSuccess";
	}

	// �ҵĶ����Ĳ�ѯ
	public String findByUid() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getId(), page);
		// ����ҳ��������ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}

	// ���ݶ�����id��ѯ�����ķ���
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}

	// Ϊ��������ķ���
	public String payOrder() throws IOException {
		// �޸Ķ���
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddress(order.getAddress());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// Ϊ��������
		String p0_Cmd = "Buy"; // ҵ������
		String p1_MerId = "10001126856"; // �̻����
		String p2_Order = order.getOid().toString(); // �̻�������
		String p3_Amt = "0.01"; // ֧�����
		String p4_Cur = "CNY"; // ���ױ���
		String p5_Pid = ""; // ��Ʒ����
		String p6_Pcat = ""; // ��Ʒ����
		String p7_Pdesc = ""; // ��Ʒ����
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; // �̻�����֧���ɹ�����ת��ҳ��·��
		String p9_SAF = ""; // �ͻ���ַ
		String pa_MP = ""; // �̻���չ��Ϣ
		String pd_FrpId = this.pd_FrpId;
		String pr_NeedResponse = "1"; // Ӧ�����
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue ); // ǩ������
		//���ױ�����:
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
		//�ض����ױ�:
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	//����ɹ����ת��
	public String callBack() {
		//�޸Ķ���״̬���޸�״̬Ϊ�Ѹ���
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		//��ҳ����ʾ����ɹ���Ϣ��
		this.addActionMessage("��������ɹ���    ������ţ�"+r6_Order+"������"+r3_Amt);
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
