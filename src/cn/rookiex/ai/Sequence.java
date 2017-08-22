package cn.rookiex.ai;

import java.util.ArrayList;
import java.util.List;

import cn.rookiex.aiTree.AIContext;
import cn.rookiex.aiTree.Node;

public class Sequence implements Node {

	private List<Node> son = new ArrayList<>();
	
	public List<Node> getSon() {
		return son;
	}

	public void addSon(Node son) {
		this.son.add(son);
	}
	
	/**顺序节点执行逻辑**/
	@Override
	public int excute(AIContext context){
		int size = son.size();
		for (int i = 0; i < size; i++) {
			int ecResult = son.get(0).excute(context);
			//如果子节点返回错误或者运行中,向父节点返回错误或运行中
			if (ecResult == 0 || ecResult == 1) {
				return ecResult;
			}
		}
		//如果全部返回正确,向父节点返回正确
		return 1;
	}

}
