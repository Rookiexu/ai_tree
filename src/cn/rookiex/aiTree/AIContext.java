package cn.rookiex.aiTree;


import java.util.Map;

import com.google.common.collect.Maps;

/**
 * AI�������ⲿ�ӿ�
 * @author �β�
 *
 */
public final class AIContext {

	private Map<Object, Object> properties = Maps.newConcurrentMap();
	
	/**
	 * ���AI��Ϊ��ض���
	 * @param key �Զ�������
	 * @param value ����
	 */
	public void addProperty (Object key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * ��ȡAI��Ϊ��ض���
	 * @param key �Զ�������
	 * @return AI��ض���
	 */
	public Object getProperty (Object key) {
		return properties.get(key);
	}
	
	public void removeProperty (Object key) {
		this.properties.remove(key);
	}
}