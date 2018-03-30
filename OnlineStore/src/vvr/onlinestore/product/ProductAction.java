package vvr.onlinestore.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.onlinestore.category.Category;
import vvr.onlinestore.category.CategoryService;
import vvr.onlinestore.categorysecond.CategorySecond;
import vvr.onlinestore.categorysecond.CategorySecondService;
import vvr.onlinestore.utils.PageBean;
/**
 * 商品
 * @author wwr
 *
 */
public class ProductAction extends ActionSupport implements RequestAware,ModelDriven<Product> {

	private static final long serialVersionUID = -4230888613608692044L;
	
	//上传图片的三个参数
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private ProductService productService;
	private CategoryService categoryService;
	private CategorySecondService categorySecondService;
	
	private Integer cid;
	private Integer csid;
	private Integer page;	//页数
	private Product product = new Product();
	
	private Map<String,Object> request;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public void setCsid(Integer csid) {
		this.csid = csid;
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
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
	
	/**
	 * 查询二级分类对应的商品
	 * @return
	 */
	public String findByCsid() {
		
		//查询出全部一级分类与二级分类
		List<Category> caList = categoryService.findAll();
		request.put("caList", caList);
		request.put("csid", csid);
		
		//查询二级分类对应的商品，也需要做分页
		PageBean<Product> pageList = productService.findByCsid(csid,page);
		request.put("pageList", pageList);
		
		return "findByCsid";
	}
	
	/**
	 * 后台查询所有商品并分页
	 */
	public String adminFindAll(){
		
		PageBean<Product> pageBean = productService.findByPage(page);
		request.put("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	
	/**
	 * 后台提供添加商品的界面
	 * @return
	 */
	public String AdminaddPage() {
		List<CategorySecond> cslist = categorySecondService.findAll();
		request.put("cslist", cslist);
		return "AdminaddPageSuccess";
	}
	
	/**
	 * 后台添加商品
	 * @return
	 * @throws IOException 
	 */
	public String amdinSave() throws IOException {
		//获取上传的路径
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String realPath = path + "/" + csid + "/" + uploadFileName;
		File destFile = new File(realPath);
		FileUtils.copyFile(upload, destFile);
		
		//保存至数据库
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		product.setPdate(new Date());
		product.setImage("products/" + csid + "/" + uploadFileName);
		productService.save(product);
		
		return "amdinSaveSuccess";
	}
	
	/**
	 * 删除指定产品和图片
	 * @return
	 */
	public String adminDelete() {
		
		Product pro = productService.findByPid(product.getPid());
		
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String imgPath = pro.getImage();
		String realPath = path + imgPath.substring(imgPath.indexOf("/"));
		
		//删除产品
		productService.delete(pro);
		
		//删除文件
		File file = new File(realPath);
		if(file.exists()) {
			file.delete();
		}
		
		return "adminDeleteSuccess";
	}
	
	/**
	 * 查询指定产品和所有二级分类
	 * @return
	 */
	public String findByPid() {
		Product pro = productService.findByPid(product.getPid());
		List<CategorySecond> cslist = categorySecondService.findAll();
		request.put("cslist", cslist);
		request.put("product", pro);
		return "findByPidSuccess";
	}
}
