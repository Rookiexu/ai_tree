package cn.rookiex.aiTree;

import java.util.ArrayList;
import java.util.List;

public interface Node {
	
	List<Node> sonNode = new ArrayList<>();
	
	/***/
	public int excute(AIContext context);

	public void addSon(Node son);
}
