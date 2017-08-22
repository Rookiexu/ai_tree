package cn.rookiex.aiTree;

import java.util.HashMap;
import java.util.Map;




public final class AIExcutor {

	private Map<Object, Node> ai;
	
	private static AIExcutor excutor = null;
	
	private Object key;
	
	
	public final static AIExcutor getInstance () {
		if (excutor == null) {
			excutor = new AIExcutor();
		}
		return excutor;
	}

	public void setAi(Map<Object, Node> ai) {
		this.ai = ai;
	}
	
	public void addAi (Object key, Node value) {
		if (ai == null) {
			ai = new HashMap<>();
		}
		ai.put(key, value);
	}
	
	public void addAiAll (Map<Object, Node> ai) {
		if (this.ai == null) {
			this.ai = ai;
		}
		this.ai.putAll(ai);
	}
	
	public void setKey (Object key) {
		this.key = key;
	}
	
	public int excute (AIContext context) {
		return ai.get(key).excute(context);
	}
}
