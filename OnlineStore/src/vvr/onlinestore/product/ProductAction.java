package vvr.onlinestore.product;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.onlinestore.category.Category;
import vvr.onlinestore.category.CategoryService;
/**
 * 商品
 * @author wwr
 *
 */
public class ProductAction extends ActionSupport implements RequestAware {

	private static final long serialVersionUID = -4230888613608692044L;
	private ProductService productService;
	private CategoryService categoryService;
	
	private Integer cid;
	private Integer page;
	
	private Map<String,Object> request;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	//查询出一级分类与对应的二级分类
	public String findByCid() {
		
		List<Category> caList = categoryService.findAll();
		request.put("caList", caList);
		request.put("cid", cid);
		
		return "findByCid";
	}

}
