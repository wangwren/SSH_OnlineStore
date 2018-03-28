package vvr.onlinestore.admin.adminuser;

public class AdminUserService {

	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 后台用户登录
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
