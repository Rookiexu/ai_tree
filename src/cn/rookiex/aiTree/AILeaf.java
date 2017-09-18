package cn.rookiex.aiTree;

public class AILeaf implements Node{

	@Override
	public int excute(AIContext context) {
		if(context.getProperty("attitude").equals("good")){
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public void addSon(Node son) {
		// TODO Auto-generated method stub
		
	}
	
}
