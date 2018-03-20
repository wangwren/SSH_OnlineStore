package vvr.onlinestore.product;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.onlinestore.category.Category;
import vvr.onlinestore.category.CategoryService;
import vvr.onlinestore.utils.PageBean;
/**
 * 商品
 * @author wwr
 *
 */
public class ProductAction extends ActionSupport implements RequestAware,ModelDriven<Product> {

	private static final long serialVersionUID = -4230888613608692044L;
	private ProductService productService;
	private CategoryService categoryService;
	
	private Integer cid;
	private Integer page;	//页数
	private Product product = new Product();
	
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
	
	public Product getModel() {
		return product;
	}

	
	/**
	 * 查询出一级分类与对应的二级分类以及对应的所有商品
	 * @return
	 */
	public String findByCid() {
		
		//查询出全部一级分类与二级分类
		List<Category> caList = categoryService.findAll();
		request.put("caList", caList);
		request.put("cid", cid);
		
		//查询一级分类中的商品
		PageBean<Product> pageList = productService.findProductToCid(cid,page);
		request.put("pageList", pageList);
		
		return "findByCid";
	}
	
	/**
	 * 查询出某个商品的详细信息
	 * @return
	 */
	public String findById() {
		
		//查询出全部一级分类与二级分类
		List<Category> caList = categoryService.findAll();
		request.put("caList", caList);
		request.put("cid", cid);
		
		product = productService.findById(product.getPid());
		//可以不必存至request域中，有模型驱动，前台可以使用 model.属性名 来取值
		
		return "findById";
	}

}
