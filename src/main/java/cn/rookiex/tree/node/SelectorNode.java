package cn.rookiex.tree.node;


import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreeStates;

/**
 * 按节点全部执行一遍,直到有一个节点执行了就返回
 */
@IsNode
public class SelectorNode extends DefaultNode{
    /** 选择节点执行逻辑 **/
    @Override
    public TreeStates execute(AIContext context) {
        for (Node node : sonNode) {
            TreeStates ecResult = node.execute(context);
            // 如果子节点返回正确或者运行中,向父节点
            if (ecResult == TreeStates.IS_TRUE || ecResult == TreeStates.IS_RUN) {
                return ecResult;
            }
        }
        // 如果全部返回失败,向父节点返回失败
        return TreeStates.IS_FALSE;
    }

}
