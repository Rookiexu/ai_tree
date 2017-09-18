package cn.rookiex.ai.node;

import cn.rookiex.ai.Condition;
import cn.rookiex.aiTree.AIContext;

public class AIIsBirthday extends Condition{

	@Override
	public boolean is(AIContext context) {
		
		if(context.getProperty("birthday") == null){
			return false;
		}
		
		if(context.getProperty("birthday").equals("today")){
			return true;
		}else {
			return false;
		}
	}

}
