package cn.rookiex.aiTree;


import java.util.Map;

import com.google.common.collect.Maps;

/**
 * AI上行文外部接口
 *
 */
public final class AIContext {

	private Map<Object, Object> properties = Maps.newConcurrentMap();
	
	/**
	 * 添加AI行为相关对象
	 * @param key 自定义类型
	 * @param value 对象
	 */
	public void addProperty (Object key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * 获取AI行为相关对象
	 * @param key 自定义类型
	 * @return AI相关对象
	 */
	public Object getProperty (Object key) {
		return properties.get(key);
	}
	
	public void removeProperty (Object key) {
		this.properties.remove(key);
	}
}