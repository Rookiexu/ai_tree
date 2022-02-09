package cn.rookiex.tree.logic;

import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreePlayer;
import cn.rookiex.tree.node.ConditionNode;
import cn.rookiex.tree.node.Node;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Random;

public class IsRandomTrue extends ConditionNode {

    /**
     * 随机概率
     */
    private int rate = 0;

    /**
     * 可触发true的次数,默认不限次数
     */
    private int times = -1;

    @Override
    public Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) {
        this.rate = jsonObject.getIntValue("rate");
        if(jsonObject.containsKey("times")){
            this.times = jsonObject.getIntValue("times");
        }
        return this;
    }

    @Override
    public boolean isTrue(TreePlayer player, AIContext context) {
        Random random = new Random();
        int i = random.nextInt(10000);
        boolean result = i < rate;
        if (result){
            result = times == -1 || times > 0;
            if (times > 0){
                times--;
            }
        }
        return result;
    }
}
