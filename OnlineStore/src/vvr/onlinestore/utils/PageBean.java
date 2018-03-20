package vvr.onlinestore.utils;

import java.util.List;

/**
 * ��ҳ
 * @author wwr
 *
 */
public class PageBean<T> {

	private Integer page;	//��ǰҳ
	private Integer limit;	//һҳ��ʾ�ĸ���
	private Integer total;	//�ܼ�¼��
	private Integer totalPage;	//��ҳ��
	
	private List<T> list;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
