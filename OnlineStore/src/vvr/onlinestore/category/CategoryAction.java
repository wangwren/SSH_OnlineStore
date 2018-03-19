package vvr.onlinestore.category;

import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = -947548055142368663L;
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
}
