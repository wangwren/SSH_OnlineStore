package vvr.onlinestore.order;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.onlinestore.cart.Cart;
import vvr.onlinestore.cart.CartItem;
import vvr.onlinestore.user.User;

public class OrdersAction extends ActionSupport implements SessionAware,RequestAware{

	private static final long serialVersionUID = 5424956490917354714L;

	private Map<String,Object> session;
	private Map<String,Object> request;
	private Orders order;
	
	private OrdersService ordersService;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	/**
	 * ���涩���Լ�������
	 * @return
	 */
	public String saveOrder() {
		
		//��ȡ���ﳵ
		Cart cart = (Cart) session.get("cart");
		if(cart == null) {
			request.put("message", "ȷ�϶���ʧ�ܣ����Ĺ��ﳵΪ�գ�����");
			return "saveOrderError";
		}
		
		//��ȡ�û�
		User user = (User) session.get("user");
		if(user == null) {
			request.put("message", "���ȵ�¼������");
			return "saveOrderError";
		}
		
		//��װ����
		order = new Orders();
		order.setOrdertime(new Date());
		order.setState(0); 		//0������δ֧��     1����֧��δ����        2������δȷ���ջ�    3����ȷ���ջ�
		order.setTotal(cart.getTotal());
		order.setUser(user);
		
		//��װ������,��ӳ�������ļ������ü�������
		for(CartItem item:cart.getCartItems()) {
			OrderItem oitem = new OrderItem();
			oitem.setCount(item.getCount());
			oitem.setSubtotal(item.getSubtotal());
			oitem.setProduct(item.getProduct());
			oitem.setOrders(order);
			
			//�����������õ�������
			order.getOrderItems().add(oitem);
		}
		
		//���涩����
		Integer oid = ordersService.saveOrder(order);
		//����ɹ����ٽ�oid���õ�order�����У�������ʾ��������ȥ�����ݿ�
		order.setOid(oid);
		//��չ��ﳵ
		cart.clear();
		
		return "saveOrderSuccess";
	}

}
