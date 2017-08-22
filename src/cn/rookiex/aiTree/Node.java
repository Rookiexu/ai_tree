package cn.rookiex.aiTree;

public interface Node {
	/**执行的方法*/
	public int excute(AIContext context);

	public void addSon(Node son);
}
