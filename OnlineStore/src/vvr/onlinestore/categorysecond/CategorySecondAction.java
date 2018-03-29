package vvr.onlinestore.categorysecond;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.onlinestore.category.Category;
import vvr.onlinestore.category.CategoryService;
import vvr.onlinestore.utils.PageBean;
/**
 * 二级分类
 * @author wwr
 *
 */
public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>,RequestAware {

	private static final long serialVersionUID = -4761323774335998671L;
	private Integer page;
	private CategorySecondService categorySecondService;
	private	CategoryService categoryService;
	private Map<String,Object> request;
	private CategorySecond categorySecond = new CategorySecond();
	private Integer cid;
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public CategorySecond getModel() {
		return categorySecond;
	}

	/**
	 * 后台查询所有二级分类，并分页
	 * @return
	 */
	public String adminFindAll() {
		
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		request.put("pageBean", pageBean);
		
		return "adminFindAllSuccess";
	}
	
	/**
	 * 后台提供二级分类添加界面，先查询出所有一级分类
	 * @return
	 */
	public String addPage() {
		
		List<Category> clist = categoryService.findAll();
		request.put("clist", clist);
		
		return "addPageSuccess";
	}
	
	/**
	 * 添加二级分类，并将对应的一级分类赋给二级分类
	 * @return
	 */
	public String adminSave() {
		
		Category category = new Category();
		category.setCid(cid);
		categorySecond.setCategory(category);
		categorySecondService.save(categorySecond);
		
		return "adminSaveSuccess";
	}
	
	/**
	 * 删除二级分类
	 * @return
	 */
	public String adminDelete() {
		
		categorySecondService.delete(categorySecond);
		
		return "adminDeleteSuccess";
	}
	
	/**
	 * 查询指定二级分类和所有一级分类
	 * @return
	 */
	public String adminFindByCsid() {
		
		//查询二级分类
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		request.put("categorySecond", categorySecond);
		
		//查询所有一级分类
		List<Category> clist = categoryService.findAll();
		request.put("clist", clist);
		
		return "adminFindByCsidSuccess";
	}
	
	/**
	 * 修改二级分类
	 * @return
	 */
	public String adminUpdate() {
		
		//Category category = new Category();
		//category.setCid(cid);
		//categorySecond.setCategory(category);
		categorySecondService.update(categorySecond);
		
		return "adminUpdateSuccess";
	}

}
