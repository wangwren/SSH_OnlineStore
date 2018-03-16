package vvr.onlinestore.user;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * �û�action
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
	 * �ṩע�����
	 * @return
	 */
	public String registPage(){
		
		return "registPageSuccess";
	}
	
	/**
	 * �û�ע��
	 */
	public String regist() {
		
		userService.regist(user);
		request.put("message", "ע��ɹ�����ǰ���������伤�");
		
		return "registSuccess";
	}


}
