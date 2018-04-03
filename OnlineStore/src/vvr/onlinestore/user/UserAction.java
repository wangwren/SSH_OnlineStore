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
 * �û�action
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
	 * �ṩע�����
	 * @return
	 */
	public String registPage(){
		
		return "registPageSuccess";
	}
	
	/**
	 * �û�ע��
	 * ����ǰ̨��¼����Ҳ��Ҫ����̨У�飬���Ե�����ʱ������input����ʱ����Ҫ��ע��ı�input
	 */
	@InputConfig(resultName="registInput")
	public String regist() {
		
		/*//�ж���֤��
		String imgCode = (String) session.get("checkcode");
		//��֤��Ϊ�գ���ƥ�䣬���ﲻ���ִ�Сд
		if(checkCode == null || !checkCode.equalsIgnoreCase(imgCode)) {
			request.put("message", "��֤���������������룡����");
			return "registInput";
		}*/
		
		userService.regist(user);
		request.put("message", "ע��ɹ�����ǰ���������伤�");
		
		return "registSuccess";
	}
	
	/**
	 * �û�����
	 */
	public String active(){
		
		//��ͨ���ʼ��еļ������ѯ���û�
		user = userService.findByCode(user.getCode());
		
		//�ж��û��Ƿ����
		if(user != null) {
			userService.update(user);
			request.put("message", "����ɹ�����ǰ����¼ҳ�棡");
			return "activeSuccess";
		}else {
			request.put("message", "����ʧ�ܣ�������ע�ᣡ����");
			return "activeSuccess";
		}
				
	}
	
	/**
	 * �ṩ�û���¼�Ľ���
	 * @return
	 */
	public String loginPage() {
		
		return "loginPageSuccess";
	}

	/**
	 * �û���¼
	 * @return
	 */
	@InputConfig(resultName="loginInput")
	public String login() {
		
		//�ж���֤��
		//�ж���֤��
		String imgCode = (String) session.get("checkcode");
		//��֤��Ϊ�գ���ƥ�䣬���ﲻ���ִ�Сд
		if(checkCode == null || !checkCode.equalsIgnoreCase(imgCode)) {
			request.put("message", "��֤���������������룡����");
			return "loginInput";
		}
		
		//��ѯ����¼���û�
		user = userService.login(user);
		if(user == null) {
			request.put("message", "�û���������������˺�δ�������");
			return "loginInput";
		}else {
			session.put("user", user);
			return "loginSuccess";
		}
	}
	
	/**
	 * ��ѯ���ݿ����Ƿ���ڸ��û���
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
	 * �û��˳�������session
	 * @return
	 */
	public String quit() {
		
		session.remove("user");
		
		/*
		 * Ҳ����
		ServletActionContext.getRequest().getSession().invalidate();
		������session
		*/
		
		return "quitSuccess";
	}

}
