package vvr.onlinestore.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ���ﳵ
 * @author wwr
 *
 */
public class Cart {

	//����map���ϴ�Ź�����,key�����Ʒ��id���������ɾ��������
	private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();
	
	//�ṩһ�����map����������value�ķ�����������ǰ̨ҳ��ѭ����ʾ����
	//�൱�ڵ�ǰ������һ�����ԣ�cartItems����ǰ̨�����п���ֱ��.�����
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//�ܼ�
	private Double total = 0d;
	public Double getTotal() {
		return total;
	}

	//�ṩ������������������ﳵ��ɾ��ĳһ�������������ﳵ
	//��������ﳵ
	public void addCart(CartItem cartItem) {
		
		//containsKey �ж��Ƿ����ָ���ļ���
		if(map.containsKey(cartItem.getProduct().getPid())) {
			//����Ѵ��ڸù�����
			//��ȡԭ�еĹ�����õ�ԭ�е��������������ڵ������������õ�������
			CartItem item = map.get(cartItem.getProduct().getPid());
			item.setCount(item.getCount() + cartItem.getCount());
		}else {
			//��������ڸù�����
			map.put(cartItem.getProduct().getPid(), cartItem);
		}
		
		//�����ܼ�,��Ϊ�����Ƿ���ڹ��ﳵ�У�������Ŀǰ�������е�С�Ƽ���
		total = total +cartItem.getSubtotal();
	}
	
	//ɾ��������
	public void del(Integer pid) {
		//�÷������ر��Ƴ��Ķ���
		CartItem item = map.remove(pid);
		//�����ܼ�
		total = total - item.getSubtotal();
	}
	
	//������ﳵ
	public void clear() {
		map.clear();
		//���ܼ�����Ϊ0
		total = 0d;
	}
}
