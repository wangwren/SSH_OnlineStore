package vvr.onlinestore.order;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.onlinestore.cart.Cart;
import vvr.onlinestore.cart.CartItem;
import vvr.onlinestore.product.ProductService;
import vvr.onlinestore.product.Size;
import vvr.onlinestore.user.User;
import vvr.onlinestore.utils.PageBean;
import vvr.onlinestore.utils.PaymentUtil;

public class OrdersAction extends ActionSupport implements SessionAware,RequestAware{

	private static final long serialVersionUID = 5424956490917354714L;

	private Map<String,Object> session;
	private Map<String,Object> request;
	private Orders order;
	private Integer page;
	private Integer state;
	
	//֧��ͨ������
	private String pd_FrpId;
	
	//����ɹ�����Ҫ�Ĳ���
	private String r3_Amt;		//֧�����
	private String r6_Order;	//�̻�������
	
	private Integer oid;
	
	private OrdersService ordersService;
	private ProductService productService;
	
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getR3_Amt() {
		return r3_Amt;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public String getR6_Order() {
		return r6_Order;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	
	public String getPd_FrpId() {
		return pd_FrpId;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
			oitem.setSize(item.getSize());
			oitem.setCount(item.getCount());
			oitem.setSubtotal(item.getSubtotal());
			oitem.setProduct(item.getProduct());
			
			//����ָ����Ʒ��ָ���ߴ�Ŀ��
			Integer count = productService.findSize(item.getProduct().getPid(), item.getSize().toLowerCase());
			Size size = productService.findSizeByPid(item.getProduct().getPid());
			//Size size = new Size();
			//size.setId(sid);
			if(item.getSize().equalsIgnoreCase("XL")) {
				count = count - item.getCount();
				size.setXlSize(count);
			}else if(item.getSize().equalsIgnoreCase("XXL")) {
				count = count - item.getCount();
				size.setXxlSize(count);;
			}else if(item.getSize().equalsIgnoreCase("S")) {
				count = count - item.getCount();
				size.setsSize(count);;
			}else if(item.getSize().equalsIgnoreCase("M")) {
				count = count - item.getCount();
				size.setmSize(count);;
			}
			
			//productService.updateSize(item.getSize().toLowerCase() + "Size", size);
			//����
			productService.updateSize(size);
			
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
		
		//��order����request����
		request.put("order", order);
		
		return "saveOrderSuccess";
	}

	/**
	 * ʵ��֧������
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException {
		
		//�޸Ķ���������ַ���ջ��ˡ���ϵ��ʽ���������
		Orders currOrder = ordersService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setPhone(order.getPhone());
		currOrder.setName(order.getName());
		ordersService.update(currOrder);
		
		//֧��������ֻ���̻����ܹ����֧���Ĳ�������������ʹ�ô��ǲ����ṩ�ģ���������̻���ţ��Ϳ������֧����������̻���Ų���ʹ�ˡ�
		//���帶��Ĳ���
		String p0_Cmd = "Buy";	//ҵ������
		String p1_MerId = "10001126856";	//�̻����
		String p2_Order = order.getOid().toString();	//�̻�������
		String p3_Amt = "0.01";		//֧�����
		String p4_Cur = "CNY";		//���ױ���
		String p5_Pid = "";			//��Ʒ����
		String p6_Pcat = "";		//��Ʒ����
		String p7_Pdesc = "";		//��Ʒ����
		//�˴�����Ե�ip��ַ192.168.43.182
		
		String p8_Url = "http://192.168.1.4/OnlineStore/order_callBack.action";	//�̻�����֧���ɹ����ݵĵ�ַ
		String p9_SAF = "";		//�ͻ���ַ
		String pa_MP = "";		//�̻���չ��Ϣ
		String pr_NeedResponse = "1";		//Ӧ�����
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		//ǩ������
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,pd_FrpId, pr_NeedResponse, keyValue);
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		System.out.println(sb.toString());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.sendRedirect(sb.toString());
		
		return NONE;
	}
	
	/**
	 * ����ɹ���Ļص�����
	 * @return
	 */
	public String callBack() {
		
		//�޸Ķ���״̬
		Orders currOrder = ordersService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(1);
		ordersService.update(currOrder);
		
		request.put("message", "����ɹ��������ţ�" + r6_Order + "�����" + r3_Amt);
		
		return "callBackSuccess";
	}
	
	/**
	 * ͨ���û�id��ѯ�û��Ķ���
	 * @return
	 */
	public String findMyOrder() {
		
		User user = (User) session.get("user");
		List<Orders> orders = ordersService.findByUid(user);
		request.put("orders", orders);
		return "findMyOrderSuccess";
	}
	
	/**
	 * ��ѯ�����б���δ����Ķ�������ת������
	 * @return
	 */
	public String findByOid() {
		
		order = ordersService.findByOid(oid);
		request.put("order", order);
		return "findByOidSuccess";
	}
	
	/**
	 * ��̨��ѯ���ж���
	 * @return
	 */
	public String adminFindAll() {
		PageBean<Orders> pageBean = ordersService.findAll(page);
		request.put("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	
	/**
	 * ��̨��ѯָ��״̬�Ķ���
	 * @return
	 */
	public String adminFindByState() {
		PageBean<Orders> pageBean = ordersService.findByState(state,page);
		request.put("pageBean", pageBean);
		return "adminFindByStateSuccess";
	}
	
	/**
	 * ��̨��Ʒ����
	 * @return
	 */
	public String adminUpdateState() {
		
		order = ordersService.findByOid(oid);
		order.setState(2);
		ordersService.update(order);
		return "adminUpdateStateSuccess";
	}
	
	/**
	 * ǰ̨ȷ���ջ�
	 * @return
	 */
	public String updateState() {
		order = ordersService.findByOid(oid);
		order.setState(3);
		ordersService.update(order);
		return "updateStateSuccess";
	}
}
