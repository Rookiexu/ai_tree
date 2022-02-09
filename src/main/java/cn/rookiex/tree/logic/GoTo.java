package cn.rookiex.tree.logic;

import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreePlayer;
import cn.rookiex.tree.TreeStates;
import cn.rookiex.tree.node.ActNode;
import cn.rookiex.tree.node.IsNode;
import cn.rookiex.tree.node.Node;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

@IsNode
public class GoTo extends ActNode {

    private String targetName;//Cinema
    private int target;//Cinema


    @Override
    public TreeStates execute0(TreePlayer player, AIContext context) {
        int local = player.getLocal();
        System.out.println(player.getPlayerId() + " from " + local + " to " + target + " ," + targetName);
        if (target > local) {
            local += 1;
        } else if (target < local){
            local -= 1;
        }else {
            return TreeStates.IS_TRUE;
        }
        player.setLocal(local);
        return TreeStates.IS_RUN;
    }

    @Override
    public Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) {
        this.target = jsonObject.getIntValue("target");
        this.targetName = jsonObject.getString("targetName");
        return this;
    }
}
