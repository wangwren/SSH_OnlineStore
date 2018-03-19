package vvr.onlinestore.product;

import java.util.Date;

import vvr.onlinestore.categorysecond.CategorySecond;

/**
 * ��Ʒ
 * ɾ����ԭ�����е�num��
 * @author wwr
 *CREATE TABLE `product` (
  `pid` INT(11) NOT NULL AUTO_INCREMENT,
  `pname` VARCHAR(255) DEFAULT NULL,
  `market_price` DOUBLE DEFAULT NULL,	--�г���
  `shop_price` DOUBLE DEFAULT NULL,		--������
  `image` VARCHAR(255) DEFAULT NULL,	
  `pdesc` VARCHAR(255) DEFAULT NULL,	--����
  `is_hot` INT(11) DEFAULT NULL,		--�Ƿ���������Ʒ��1��������
  `pdate` DATETIME DEFAULT NULL,		-- ����
  `csid` INT(11) DEFAULT NULL,			--�������Ӧ��������
  PRIMARY KEY (`pid`),
  KEY `FKED8DCCEFB9B74E02` (`csid`),
  CONSTRAINT `FKED8DCCEFB9B74E02` FOREIGN KEY (`csid`) REFERENCES `categorysecond` (`csid`)
) ENGINE=INNODB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
 */
public class Product {

	private int pid;
	private String pname;
	private double market_price;
	private double shop_price;
	private String image;
	private String pdesc;
	private int is_hot;
	private Date pdate;
	private CategorySecond categorySecond;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
}
