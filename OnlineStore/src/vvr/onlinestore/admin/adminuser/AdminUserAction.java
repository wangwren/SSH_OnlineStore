package vvr.onlinestore.admin.adminuser;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 后台
 * @author wwr
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>,RequestAware,SessionAware {

	private static final long serialVersionUID = -4602388158341477735L;
	private AdminUserService adminUserService;
	private AdminUser adminUser = new AdminUser();
	private Map<String,Object> request;
	private Map<String,Object> session;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public AdminUser getModel() {
		return adminUser;
	}
	
	/**
	 * 后台用户登录
	 * @return
	 */
	public String login() {
		
		AdminUser admin = adminUserService.login(adminUser);
		if(admin == null) {
			request.put("message", "您输入的用户名密码错误！请重新登录！！！");
			return INPUT;
		}
		session.put("adminUser", admin);
		return "loginSuccess";
	}
	
}
