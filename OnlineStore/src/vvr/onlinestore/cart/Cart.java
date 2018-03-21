package vvr.onlinestore.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 * @author wwr
 *
 */
public class Cart {

	//设置map集合存放购物项,key代表产品的id，方便添加删除购物项
	private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();
	
	//提供一个获得map集合中所有value的方法，方便在前台页面循环显示数据
	//相当于当前类中有一个属性：cartItems，在前台界面中可以直接.点出来
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//总计
	private Double total = 0d;
	public Double getTotal() {
		return total;
	}

	//提供三个方法，添加至购物车，删除某一个购物项，清除购物车
	//添加至购物车
	public void addCart(CartItem cartItem) {
		
		//containsKey 判断是否包含指定的键名
		if(map.containsKey(cartItem.getProduct().getPid())) {
			//如果已存在该购物项
			//获取原有的购物项，得到原有的数量，加上现在的数量，再设置到数量中
			CartItem item = map.get(cartItem.getProduct().getPid());
			item.setCount(item.getCount() + cartItem.getCount());
		}else {
			//如果不存在该购物项
			map.put(cartItem.getProduct().getPid(), cartItem);
		}
		
		//设置总计,因为不管是否存在购物车中，都加上目前购物项中的小计即可
		total = total +cartItem.getSubtotal();
	}
	
	//删除购物项
	public void del(Integer pid) {
		//该方法返回被移除的对象
		CartItem item = map.remove(pid);
		//设置总计
		total = total - item.getSubtotal();
	}
	
	//清除购物车
	public void clear() {
		map.clear();
		//将总计设置为0
		total = 0d;
	}
}
