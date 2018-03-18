package vvr.onlinestore.user;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
/**
 * 用户action
 * @author wwr
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>,RequestAware,SessionAware{

	private static final long serialVersionUID = 1926087235639692402L;
	
	private User user = new User();
	private UserService userService;
	private Map<String,Object> request;
	private Map<String,Object> session;
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
	 * 由于前台登录界面也需要做后台校验，所以当出错时都返回input，这时就需要用注解改变input
	 */
	@InputConfig(resultName="registInput")
	public String regist() {
		
		userService.regist(user);
		request.put("message", "注册成功！请前往您的邮箱激活！");
		
		return "registSuccess";
	}
	
	/**
	 * 用户激活
	 */
	public String active(){
		
		//先通过邮件中的激活码查询出用户
		user = userService.findByCode(user.getCode());
		
		//判断用户是否存在
		if(user != null) {
			userService.update(user);
			request.put("message", "激活成功！请前往登录页面！");
			return "activeSuccess";
		}else {
			request.put("message", "激活失败！请重新注册！！！");
			return "activeSuccess";
		}
				
	}
	
	/**
	 * 提供用户登录的界面
	 * @return
	 */
	public String loginPage() {
		
		return "loginPageSuccess";
	}

	/**
	 * 用户登录
	 * @return
	 */
	@InputConfig(resultName="loginInput")
	public String login() {
		
		//查询出登录的用户
		user = userService.login(user);
		if(user == null) {
			request.put("message", "用户名，密码有误或账号未激活！！！");
			return "loginInput";
		}else {
			session.put("user", user);
			return "loginSuccess";
		}
	}
	
	/**
	 * 查询数据库中是否存在该用户名
	 * @return
	 */
	public String checkName() throws Exception{
		
		user = userService.checkUsername(user.getUsername());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(user == null) {
			writer.println("yes");
		}else {
			writer.print("no");
		}
		
		return NONE;
	}

}
