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
	
	/**˳��ڵ�ִ���߼�**/
	@Override
	public int excute(AIContext context){
		int size = son.size();
		for (int i = 0; i < size; i++) {
			int ecResult = son.get(0).excute(context);
			//����ӽڵ㷵�ش������������,�򸸽ڵ㷵�ش����������
			if (ecResult == 0 || ecResult == 1) {
				return ecResult;
			}
		}
		//���ȫ��������ȷ,�򸸽ڵ㷵����ȷ
		return 1;
	}

}
