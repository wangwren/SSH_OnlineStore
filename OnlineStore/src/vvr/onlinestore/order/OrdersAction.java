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
	
	//支付通道编码
	private String pd_FrpId;
	
	//付款成功后需要的参数
	private String r3_Amt;		//支付金额
	private String r6_Order;	//商户订单号
	
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
	 * 保存订单以及订单项
	 * @return
	 */
	public String saveOrder() {
		
		//获取购物车
		Cart cart = (Cart) session.get("cart");
		if(cart == null) {
			request.put("message", "确认订单失败，您的购物车为空！！！");
			return "saveOrderError";
		}
		
		//获取用户
		User user = (User) session.get("user");
		if(user == null) {
			request.put("message", "请先登录！！！");
			return "saveOrderError";
		}
		
		//封装订单
		order = new Orders();
		order.setOrdertime(new Date());
		order.setState(0); 		//0代表订单未支付     1代表支付未发货        2代表发货未确认收货    3代表确认收货
		order.setTotal(cart.getTotal());
		order.setUser(user);
		
		//封装订单项,在映射配置文件中设置级联操作
		for(CartItem item:cart.getCartItems()) {
			OrderItem oitem = new OrderItem();
			oitem.setSize(item.getSize());
			oitem.setCount(item.getCount());
			oitem.setSubtotal(item.getSubtotal());
			oitem.setProduct(item.getProduct());
			
			//更改指定商品的指定尺寸的库存
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
			//更新
			productService.updateSize(size);
			
			oitem.setOrders(order);
			
			//将订单项设置到订单中
			order.getOrderItems().add(oitem);
			
			
		}
		
		//保存订单；
		Integer oid = ordersService.saveOrder(order);
		//保存成功后，再将oid设置到order对象中，方便显示，不用再去查数据库
		order.setOid(oid);
		//清空购物车
		cart.clear();
		
		//将order存至request域中
		request.put("order", order);
		
		return "saveOrderSuccess";
	}

	/**
	 * 实现支付功能
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException {
		
		//修改订单，将地址、收货人、联系方式添加至订单
		Orders currOrder = ordersService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setPhone(order.getPhone());
		currOrder.setName(order.getName());
		ordersService.update(currOrder);
		
		//支付，由于只能商户才能够完成支付的操作，所以这里使用传智播客提供的，如果你有商户编号，就可以完成支付，这里的商户编号不好使了。
		//定义付款的参数
		String p0_Cmd = "Buy";	//业务类型
		String p1_MerId = "10001126856";	//商户编号
		String p2_Order = order.getOid().toString();	//商户订单号
		String p3_Amt = "0.01";		//支付金额
		String p4_Cur = "CNY";		//交易币种
		String p5_Pid = "";			//商品名称
		String p6_Pcat = "";		//商品种类
		String p7_Pdesc = "";		//商品描述
		//此处填电脑的ip地址192.168.43.182
		
		String p8_Url = "http://192.168.1.4/OnlineStore/order_callBack.action";	//商户接收支付成功数据的地址
		String p9_SAF = "";		//送货地址
		String pa_MP = "";		//商户扩展信息
		String pr_NeedResponse = "1";		//应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		//签名数据
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
	 * 付款成功后的回调方法
	 * @return
	 */
	public String callBack() {
		
		//修改订单状态
		Orders currOrder = ordersService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(1);
		ordersService.update(currOrder);
		
		request.put("message", "付款成功！订单号：" + r6_Order + "付款金额：" + r3_Amt);
		
		return "callBackSuccess";
	}
	
	/**
	 * 通过用户id查询用户的订单
	 * @return
	 */
	public String findMyOrder() {
		
		User user = (User) session.get("user");
		List<Orders> orders = ordersService.findByUid(user);
		request.put("orders", orders);
		return "findMyOrderSuccess";
	}
	
	/**
	 * 查询订单列表中未付款的订单，跳转至付款
	 * @return
	 */
	public String findByOid() {
		
		order = ordersService.findByOid(oid);
		request.put("order", order);
		return "findByOidSuccess";
	}
	
	/**
	 * 后台查询所有订单
	 * @return
	 */
	public String adminFindAll() {
		PageBean<Orders> pageBean = ordersService.findAll(page);
		request.put("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	
	/**
	 * 后台查询指定状态的订单
	 * @return
	 */
	public String adminFindByState() {
		PageBean<Orders> pageBean = ordersService.findByState(state,page);
		request.put("pageBean", pageBean);
		return "adminFindByStateSuccess";
	}
	
	/**
	 * 后台商品发货
	 * @return
	 */
	public String adminUpdateState() {
		
		order = ordersService.findByOid(oid);
		order.setState(2);
		ordersService.update(order);
		return "adminUpdateStateSuccess";
	}
	
	/**
	 * 前台确认收货
	 * @return
	 */
	public String updateState() {
		order = ordersService.findByOid(oid);
		order.setState(3);
		ordersService.update(order);
		return "updateStateSuccess";
	}
}
