package cn.rookiex.tree.logic;

import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreePlayer;
import cn.rookiex.tree.node.ConditionNode;
import cn.rookiex.tree.node.IsNode;
import cn.rookiex.tree.node.Node;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;


@IsNode
public class IsRich extends ConditionNode {

    private JSONObject need;

    @Override
    public boolean isTrue(TreePlayer player, AIContext context) {
        return player.isEnough(need);
    }

    @Override
    public Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) {
        this.need = jsonObject.getJSONObject("need");
        return this;
    }
}
