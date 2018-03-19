package vvr.onlinestore.category;

import java.util.HashSet;
import java.util.Set;

import vvr.onlinestore.categorysecond.CategorySecond;

/**
 * 一级分类实体
 * @author wwr
 *CREATE TABLE category(
	cid INT PRIMARY KEY AUTO_INCREMENT,
	cname VARCHAR(20)
);
 */
public class Category {
	
	private Integer cid;
	private String cname;
	
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
	
	
}
