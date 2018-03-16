package vvr.onlinestore.user;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 用户action
 * @author wwr
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>,RequestAware{

	private static final long serialVersionUID = 1926087235639692402L;
	
	private User user = new User();
	private UserService userService;
	private Map<String,Object> request;
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	/**
	 * 提供注册界面
	 * @return
	 */
	public String registPage(){
		
		return "registPageSuccess";
	}
	
	/**
	 * 用户注册
	 */
	public String regist() {
		
		userService.regist(user);
		request.put("message", "注册成功！请前往您的邮箱激活！");
		
		return "registSuccess";
	}


}
