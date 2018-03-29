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
 * ��������
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
	 * ��̨��ѯ���ж������࣬����ҳ
	 * @return
	 */
	public String adminFindAll() {
		
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		request.put("pageBean", pageBean);
		
		return "adminFindAllSuccess";
	}
	
	/**
	 * ��̨�ṩ����������ӽ��棬�Ȳ�ѯ������һ������
	 * @return
	 */
	public String addPage() {
		
		List<Category> clist = categoryService.findAll();
		request.put("clist", clist);
		
		return "addPageSuccess";
	}
	
	/**
	 * ��Ӷ������࣬������Ӧ��һ�����ำ����������
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
	 * ɾ����������
	 * @return
	 */
	public String adminDelete() {
		
		categorySecondService.delete(categorySecond);
		
		return "adminDeleteSuccess";
	}
	
	/**
	 * ��ѯָ���������������һ������
	 * @return
	 */
	public String adminFindByCsid() {
		
		//��ѯ��������
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		request.put("categorySecond", categorySecond);
		
		//��ѯ����һ������
		List<Category> clist = categoryService.findAll();
		request.put("clist", clist);
		
		return "adminFindByCsidSuccess";
	}
	
	/**
	 * �޸Ķ�������
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
