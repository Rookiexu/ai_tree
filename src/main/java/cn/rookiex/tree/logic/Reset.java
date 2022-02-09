package cn.rookiex.tree.logic;

import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreePlayer;
import cn.rookiex.tree.TreeStates;
import cn.rookiex.tree.node.ActNode;

public class Reset extends ActNode {
    @Override
    public TreeStates execute0(TreePlayer player, AIContext context) {
        System.out.println(player.getPlayerId() + " 在家休息");
        return TreeStates.IS_RUN;
    }
}
