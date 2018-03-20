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
 * ��Ʒ
 * @author wwr
 *
 */
public class ProductAction extends ActionSupport implements RequestAware,ModelDriven<Product> {

	private static final long serialVersionUID = -4230888613608692044L;
	private ProductService productService;
	private CategoryService categoryService;
	
	private Integer cid;
	private Integer page;	//ҳ��
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
	 * ��ѯ��һ���������Ӧ�Ķ��������Լ���Ӧ��������Ʒ
	 * @return
	 */
	public String findByCid() {
		
		//��ѯ��ȫ��һ���������������
		List<Category> caList = categoryService.findAll();
		request.put("caList", caList);
		request.put("cid", cid);
		
		//��ѯһ�������е���Ʒ
		PageBean<Product> pageList = productService.findProductToCid(cid,page);
		request.put("pageList", pageList);
		
		return "findByCid";
	}
	
	/**
	 * ��ѯ��ĳ����Ʒ����ϸ��Ϣ
	 * @return
	 */
	public String findById() {
		
		//��ѯ��ȫ��һ���������������
		List<Category> caList = categoryService.findAll();
		request.put("caList", caList);
		request.put("cid", cid);
		
		product = productService.findById(product.getPid());
		//���Բ��ش���request���У���ģ��������ǰ̨����ʹ�� model.������ ��ȡֵ
		
		return "findById";
	}

}
