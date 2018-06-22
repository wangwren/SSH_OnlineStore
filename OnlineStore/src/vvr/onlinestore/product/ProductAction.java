package vvr.onlinestore.product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
 * ��Ʒ
 * @author wwr
 *
 */
public class ProductAction extends ActionSupport implements RequestAware,ModelDriven<Product> {

	private static final long serialVersionUID = -4230888613608692044L;
	
	//�ϴ�ͼƬ����������
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private ProductService productService;
	private CategoryService categoryService;
	private CategorySecondService categorySecondService;
	
	private Integer cid;
	private Integer csid;
	private Integer page;	//ҳ��
	private Product product = new Product();
	
	private Map<String,Object> request;
	
	//�ͻ�ѡ�е��·�����
	private String sizeValue;
	
	private Size size;
	
	
	
	
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public void setSizeValue(String sizeValue) {
		this.sizeValue = sizeValue;
	}

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
		
		Integer cid = productService.findCidByPid(product.getPid());
		request.put("cid", cid);
		
		product = productService.findById(product.getPid());
		//���Բ��ش���request���У���ģ��������ǰ̨����ʹ�� model.������ ��ȡֵ
		
		
		//��ѯָ����Ʒ�ͺſ��
		/*Integer num = productService.findSize(2, "xl");
		System.out.println("-----------------" + num);*/
		
		return "findById";
	}
	
	/**
	 * ��ѯ���������Ӧ����Ʒ
	 * @return
	 */
	public String findByCsid() {
		
		//��ѯ��ȫ��һ���������������
		List<Category> caList = categoryService.findAll();
		request.put("caList", caList);
		request.put("csid", csid);
		
		//��ѯ���������Ӧ����Ʒ��Ҳ��Ҫ����ҳ
		PageBean<Product> pageList = productService.findByCsid(csid,page);
		request.put("pageList", pageList);
		
		return "findByCsid";
	}
	
	/**
	 * ��̨��ѯ������Ʒ����ҳ
	 */
	public String adminFindAll(){
		
		PageBean<Product> pageBean = productService.findByPage(page);
		request.put("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	
	/**
	 * ��̨�ṩ�����Ʒ�Ľ���
	 * @return
	 */
	public String AdminaddPage() {
		List<CategorySecond> cslist = categorySecondService.findAll();
		request.put("cslist", cslist);
		return "AdminaddPageSuccess";
	}
	
	/**
	 * ��̨�����Ʒ
	 * @return
	 * @throws IOException 
	 */
	public String amdinSave() throws IOException {
		//��ȡ�ϴ���·��
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String realPath = path + "/" + csid + "/" + uploadFileName;
		File destFile = new File(realPath);
		FileUtils.copyFile(upload, destFile);
		
		//���������ݿ�
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		product.setPdate(new Date());
		product.setImage("products/" + csid + "/" + uploadFileName);
		productService.save(product);
		
		return "amdinSaveSuccess";
	}
	
	/**
	 * ɾ��ָ����Ʒ��ͼƬ
	 * @return
	 */
	public String adminDelete() {
		
		Product pro = productService.findByPid(product.getPid());
		
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String imgPath = pro.getImage();
		String realPath = path + imgPath.substring(imgPath.indexOf("/"));
		
		//ɾ����Ʒ
		productService.delete(pro);
		
		//ɾ���ļ�
		File file = new File(realPath);
		if(file.exists()) {
			file.delete();
		}
		
		return "adminDeleteSuccess";
	}
	
	/**
	 * ��ѯָ����Ʒ�����ж�������
	 * @return
	 */
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> cslist = categorySecondService.findAll();
		request.put("cslist", cslist);
		return "findByPidSuccess";
	}
	
	/**
	 * �޸���Ʒ
	 * @return
	 * @throws IOException 
	 */
	public String amdinUpdate() throws IOException {
		
		if(uploadFileName != null) {
			//�û��ϴ����µ�ͼƬ
			//ɾ���ɵ��ļ�
			String path = ServletActionContext.getServletContext().getRealPath("/products");
			String imgPath = product.getImage();
			String oldPath = path + imgPath.substring(imgPath.indexOf("/"));
			if(oldPath != null && !oldPath.trim().isEmpty()) {
				File file = new File(oldPath);
				file.delete();
			}
			
			//�ϴ��µ��ļ�
			String realPath = path + "/" + product.getCategorySecond().getCsid() + "/" + uploadFileName;
			File destFile = new File(realPath);
			FileUtils.copyFile(upload, destFile);
			//�������ݿ��е��ļ�·��
			product.setImage("products/" + product.getCategorySecond().getCsid() + "/" + uploadFileName);
		}
		
		productService.update(product);
		
		return "amdinUpdateSuccess";
	}
	
	/**
	 * ��ѯ�ͻ�ѡ���·�����Ŀ������
	 * @return
	 * @throws Exception
	 */
	public String findSize() throws Exception{
		
		/*System.out.println(product.getPid());
		System.out.println(sizeValue);*/
		
		Integer num = productService.findSize(product.getPid(), sizeValue);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		writer.print(num);
		
		return NONE;
	}
	
	/**
	 * ��̨��ѯָ����Ʒ�Ŀ��
	 * @return
	 * @throws Exception
	 */
	public String findSizeByPid() throws Exception{
		
		Size size = productService.findSizeByPid(product.getPid());
		request.put("size", size);
		request.put("pid", product.getPid());
		
		return "findSizeByPidSuccess";
	}
	
	/**
	 * ������޸ĳߴ���
	 * @return
	 * @throws Exception
	 */
	public String updateOrSaveSize() throws Exception{
		
		productService.updateOrSaveSize(size);
		
		return "updateOrSaveSize";
	}
}
