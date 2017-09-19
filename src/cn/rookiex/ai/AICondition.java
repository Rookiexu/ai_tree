package cn.rookiex.ai;

import cn.rookiex.aiTree.AIContext;
import cn.rookiex.aiTree.Node;
import cn.rookiex.constant.Contsant;

public abstract class AICondition implements Node {

	@Override
	public int excute(AIContext context) {
		if(is(context)){
			int size = sonNode.size();
			for (int i = 0; i < size; i++) {
				int ecResult = sonNode.get(i).excute(context);
				//如果子节点返回错误或者运行中,向父节点返回错误或运行中
				if (ecResult == Contsant.IS_FALSE || ecResult == Contsant.IS_RUN) {
					return ecResult;
				}
			}
			return Contsant.IS_TRUE;
		}else {
			return Contsant.IS_FALSE;
		}
	}

	public abstract boolean is(AIContext context);
	
	@Override
	public void addSon(Node son) {
		sonNode.add(son);
	}

}
