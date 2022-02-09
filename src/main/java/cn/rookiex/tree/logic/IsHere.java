package cn.rookiex.tree.logic;

import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreePlayer;
import cn.rookiex.tree.node.ConditionNode;
import cn.rookiex.tree.node.IsNode;
import cn.rookiex.tree.node.Node;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

@IsNode
public class IsHere extends ConditionNode {


    private int here;

    private int heroName;

    @Override
    public Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) {
        this.here = jsonObject.getIntValue("here");
        return this;
    }

    @Override
    public boolean isTrue(TreePlayer player, AIContext context) {
        int local = player.getLocal();
        return local == here;
    }
}
