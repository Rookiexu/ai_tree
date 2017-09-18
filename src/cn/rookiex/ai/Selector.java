package cn.rookiex.ai;

import java.util.List;

import cn.rookiex.aiTree.AIContext;
import cn.rookiex.aiTree.Node;
import cn.rookiex.constant.Contsant;

public class Selector implements Node {

	/** 选择节点执行逻辑 **/
	@Override
	public int excute(AIContext context) {
		int size = sonNode.size();
		for (int i = 0; i < size; i++) {
			int ecResult = sonNode.get(i).excute(context);
			// 如果子节点返回正确或者运行中,向父节点返回正确或运行中
			if (ecResult == Contsant.IS_TRUE || ecResult == Contsant.IS_RUN) {
				return ecResult;
			}
		}
		// 如果全部返回失败,向父节点返回失败
		return Contsant.IS_FALSE;
	}

	public List<Node> getSon() {
		return sonNode;
	}

	public void addSon(Node son) {
		sonNode.add(son);
	}
}