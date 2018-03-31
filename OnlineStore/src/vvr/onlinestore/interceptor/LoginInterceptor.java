package vvr.onlinestore.interceptor;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import vvr.onlinestore.admin.adminuser.AdminUser;

public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		AdminUser adminUser = (AdminUser) ServletActionContext
				.getRequest().getSession().getAttribute("adminUser");
		if(adminUser != null){
			return actionInvocation.invoke();
		}else{
			ActionSupport action = (ActionSupport) actionInvocation.getAction();
			action.addActionError("Äú»¹Ã»ÓÐµÇÂ¼!");
			return "login";
		}
		
	}

}
