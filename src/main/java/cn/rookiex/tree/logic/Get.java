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
public class Get extends ActNode {

    private JSONObject add;

    private JSONObject decr;

    @Override
    public TreeStates execute0(TreePlayer player, AIContext context) {

        if (add != null)
            player.addItem(add);

        if (decr != null)
            player.decrItem(decr);


        return TreeStates.IS_RUN;
    }

    @Override
    public Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) throws IllegalAccessException, InstantiationException {
        super.init(jsonObject, stringClassMap);
        this.add = jsonObject.getJSONObject("add");
        this.decr = jsonObject.getJSONObject("decr");
        return this;
    }
}