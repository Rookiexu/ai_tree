package cn.rookiex.ai;

import cn.rookiex.aiTree.AIContext;
import cn.rookiex.aiTree.*;

public class AIAction extends AILeaf{
	
	@Override
	public int excute(AIContext context) {
		this.run(context);
		return 0;
	}
	public void run(AIContext context){
		System.out.println("hello ai");
	}
}
