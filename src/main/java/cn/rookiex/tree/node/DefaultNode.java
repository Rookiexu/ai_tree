package cn.rookiex.tree.node;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class DefaultNode implements Node {

    List<Node> sonNode = new ArrayList<>();

    @Override
    public void addSonNode(Node son) {
        sonNode.add(son);
    }

    @Override
    public List<Node> getSonNode() {
        return sonNode;
    }

    @Override
    public Node init(JSONObject jsonObject, Map<String, Class<Node>> stringClassMap) throws IllegalAccessException, InstantiationException {
        JSONArray node = jsonObject.getJSONArray("node");
        if (node != null) {
            for (int i = 0; i < node.size(); i++) {
                JSONObject nodeJson = node.getJSONObject(i);
                String act = nodeJson.getString("act");
                Node sonNode = stringClassMap.get(act).newInstance().init(nodeJson, stringClassMap);
                addSonNode(sonNode);
            }
        }
        return this;
    }
}
