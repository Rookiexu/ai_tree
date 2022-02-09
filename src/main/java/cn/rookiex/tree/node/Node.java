package cn.rookiex.tree.node;


import cn.rookiex.tree.AIContext;
import cn.rookiex.tree.TreeStates;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface Node {

    TreeStates execute(AIContext context);

    void addSonNode(Node son);

    List<Node> getSonNode();

    Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) throws IllegalAccessException, InstantiationException;
}
