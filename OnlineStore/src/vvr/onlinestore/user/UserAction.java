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
	private String checkCode;

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

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
		
		/*//判断验证码
		String imgCode = (String) session.get("checkcode");
		//验证码为空，或不匹配，这里不区分大小写
		if(checkCode == null || !checkCode.equalsIgnoreCase(imgCode)) {
			request.put("message", "验证码有误，请重新输入！！！");
			return "registInput";
		}*/
		
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
		
		//判断验证码
		//判断验证码
		String imgCode = (String) session.get("checkcode");
		//验证码为空，或不匹配，这里不区分大小写
		if(checkCode == null || !checkCode.equalsIgnoreCase(imgCode)) {
			request.put("message", "验证码有误，请重新输入！！！");
			return "loginInput";
		}
		
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
	
	/**
	 * 用户退出，销毁session
	 * @return
	 */
	public String quit() {
		
		session.remove("user");
		
		/*
		 * 也可以
		ServletActionContext.getRequest().getSession().invalidate();
		来销毁session
		*/
		
		return "quitSuccess";
	}

}
