package cn.rookiex.tree.node;

import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreePlayer;
import cn.rookiex.tree.TreeStates;

public abstract class ActNode extends DefaultNode {
    @Override
    public TreeStates execute(AIContext context) {
        return execute0(context.getPlayer(), context);
    }

    public abstract TreeStates execute0(TreePlayer player, AIContext context);
}
