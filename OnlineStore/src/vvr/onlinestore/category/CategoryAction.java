package vvr.onlinestore.category;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>,RequestAware {

	private static final long serialVersionUID = -947548055142368663L;
	private CategoryService categoryService;
	private Map<String,Object> request;
	private Category category = new Category();
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public Category getModel() {
		return category;
	}
	
	/**
	 * ��̨��ѯȫ��һ������
	 * @return
	 */
	public String adminFindAll() {
		List<Category> clist = categoryService.findAll();
		request.put("clist", clist);
		return "adminFindAllSuccess";
	}
	
	/**
	 * ��̨���һ������
	 * @return
	 */
	public String adminSave() {
		categoryService.save(category);
		return "adminSave";
	}
	
	/**
	 * ��̨ɾ��һ������
	 * @return
	 */
	public String adminDelete() {
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	/**
	 * ��̨��ѯָ��һ������
	 * @return
	 */
	public String adminFindByCid() {
		category = categoryService.findByCid(category.getCid());
		return "adminFindByCidSuccess";
	}
	
	/**
	 * ��̨�޸�һ������
	 * @return
	 */
	public String adminUpdate() {
		categoryService.update(category);
		return "adminUpdateSuccess";
	}

}
