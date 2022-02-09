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
public class WatchMovie extends ActNode {

    private int costTicks;
    private JSONObject cost;

    @Override
    public TreeStates execute0(TreePlayer player, AIContext context) {
        JSONObject info = player.getInfo();
        String curAct = info.getString("curAct");
        if (curAct == null) {
            info.put("curAct", "watchMovie");
            info.put("movieTick", 1);
            System.out.println(player.getPlayerId() + " 开始看电影, 还有money " + player.getMoney() );
            return TreeStates.IS_RUN;
        } else {
            int tick = info.getIntValue("movieTick");
            info.put("movieTick", tick + 1);

            System.out.println(player.getPlayerId() + " 看电影 " + (tick + 1));
            if (tick + 1 == costTicks){
                player.decrItem(cost);
                info.remove("curAct");
                info.remove("movieTick");
                return TreeStates.IS_TRUE;
            }else {
                return TreeStates.IS_RUN;
            }
        }
    }

    @Override
    public Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) {
        this.costTicks = jsonObject.getIntValue("costTicks");
        this.cost = jsonObject.getJSONObject("cost");
        return this;
    }
}
