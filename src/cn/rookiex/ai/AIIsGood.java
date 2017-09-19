package cn.rookiex.ai;

import cn.rookiex.aiTree.AIContext;

public class AIIsGood extends AICondition {

	@Override
	public boolean is(AIContext context) {
		
		if(context.getProperty("attitude") == null){
			return false;
		}
		
		if(context.getProperty("attitude").equals("good")){
			return true;
		}else {
			return false;
		}
	}

}
