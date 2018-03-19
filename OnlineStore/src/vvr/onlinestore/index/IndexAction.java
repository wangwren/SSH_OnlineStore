package vvr.onlinestore.index;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.onlinestore.category.Category;
import vvr.onlinestore.category.CategoryService;
import vvr.onlinestore.product.Product;
import vvr.onlinestore.product.ProductService;

/**
 * ǰ̨��ҳ
 * @author wwr
 *
 */
public class IndexAction extends ActionSupport implements SessionAware,RequestAware{

	private static final long serialVersionUID = -1229327882524632360L;
	private CategoryService categoryService;
	private ProductService productService;
	private Map<String,Object> session;
	private Map<String,Object> request;
	

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	/**
	 * ������ҳʱִ��
	 */
	@Override
	public String execute() throws Exception {
		
		//��ѯһ������
		List<Category> categorys = categoryService.findAll();
		session.put("categorys", categorys);
		
		//��ѯ������Ʒ
		List<Product> hotList = productService.findHot();
		request.put("hotList", hotList);
		
		//��ѯ������Ʒ����ʱ�䵹����
		List<Product> newList = productService.findNew();
		request.put("newList", newList);
		
		return "indexSuccess";
	}

}
