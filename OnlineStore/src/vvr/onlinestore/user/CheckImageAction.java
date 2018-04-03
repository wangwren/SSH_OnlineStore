package vvr.onlinestore.user;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 验证码异步校验
 * @author wwr
 *
 */
public class CheckImageAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -5004904106683449304L;
	private String checkCode;
	private Map<String,Object> session;
	
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() throws Exception {
		
		String imgCode = (String) session.get("checkcode");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(imgCode.equalsIgnoreCase(checkCode)) {
			out.write("yes");
		}else {
			out.write("no");
		}
		return NONE;
	}

}
