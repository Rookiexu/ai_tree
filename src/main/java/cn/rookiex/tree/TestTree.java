package cn.rookiex.tree;

import cn.rookiex.tree.node.IsNode;
import cn.rookiex.tree.node.Node;
import cn.rookiex.tree.util.PackageScanner;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestTree {

    static String json = "{\n" +
            "  \"act\": \"SelectorNode\",\n" +
            "  \"node\": [\n" +
            "    {\n" +
            "      \"act\": \"SequenceNode\",\n" +
            "      \"node\": [\n" +
            "        {\n" +
            "          \"act\": \"IsRich\",\n" +
            "          \"need\": {\n" +
            "            \"money\": 20,\n" +
            "            \"energy\": 10\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"act\": \"AOtherwiseBNode\",\n" +
            "          \"is\": {\n" +
            "            \"act\": \"IsHere\",\n" +
            "            \"here\": 5\n" +
            "          },\n" +
            "          \"planB\": {\n" +
            "            \"act\": \"GoTo\",\n" +
            "            \"targetName\": \"电影院\",\n" +
            "            \"target\": 5\n" +
            "          },\n" +
            "          \"planA\": {\n" +
            "            \"act\": \"WatchMovie\",\n" +
            "            \"costTicks\": 3,\n" +
            "            \"cost\": {\n" +
            "              \"money\": 20,\n" +
            "              \"energy\": 10\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"act\": \"AOtherwiseBNode\",\n" +
            "      \"is\": {\n" +
            "        \"act\": \"IsRich\",\n" +
            "        \"need\": {\n" +
            "          \"energy\": 60\n" +
            "        }\n" +
            "      },\n" +
            "      \"planA\": {\n" +
            "        \"act\": \"AOtherwiseBNode\",\n" +
            "        \"is\": {\n" +
            "          \"act\": \"IsHere\",\n" +
            "          \"here\": 7\n" +
            "        },\n" +
            "        \"planA\": {\n" +
            "          \"act\": \"Get\",\n" +
            "          \"add\": {\n" +
            "            \"money\": 30\n" +
            "          },\n" +
            "          \"decr\": {\n" +
            "            \"energy\": 60\n" +
            "          }\n" +
            "        },\n" +
            "        \"planB\": {\n" +
            "          \"act\": \"GoTo\",\n" +
            "          \"targetName\": \"工地打工\",\n" +
            "          \"target\": 7\n" +
            "        }\n" +
            "      },\n" +
            "      \"planB\": {\n" +
            "        \"act\": \"AOtherwiseBNode\",\n" +
            "        \"is\": {\n" +
            "          \"act\": \"IsHere\",\n" +
            "          \"here\": 0\n" +
            "        },\n" +
            "        \"planA\": {\n" +
            "          \"act\": \"Get\",\n" +
            "          \"add\": {\n" +
            "            \"energy\": 60\n" +
            "          }\n" +
            "        },\n" +
            "        \"planB\": {\n" +
            "          \"act\": \"GoTo\",\n" +
            "          \"targetName\": \"回家休息\",\n" +
            "          \"target\": 0\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n" +
            "\n";


    public static void main(String[] args) throws InterruptedException {
        Map<String, Class<Node>> stringClassMap = initNode();

        Node node = initTree(stringClassMap);

        AIContext aiContext = new AIContext();
        TreePlayer treePlayer = new TreePlayer();
        treePlayer.setPlayerId(101);
        aiContext.setPlayer(treePlayer);
        for (int i = 0; i < 50; i++) {
            node.execute(aiContext);
            Thread.sleep(500);//0.5秒驱动一次机器人
        }
    }

    private static Node initTree(Map<String, Class<Node>> stringClassMap) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String act = jsonObject.getString("act");
            Class<Node> nodeClass = stringClassMap.get(act);
            Node node = nodeClass.newInstance();
            node.init(jsonObject, stringClassMap);
            return node;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Class<Node>> initNode(){
        Map<String, Class<Node>> nodeMap = new HashMap<>();
        Set<Class<?>> clazzs = PackageScanner.getClasses("cn.rookiex.tree");
        Iterator<Class<?>> it = clazzs.iterator();
        Class<?> clazz = null;
        while (it.hasNext()) {
            clazz = it.next();
            IsNode isEventClazz = clazz.getAnnotation(IsNode.class);
            if (isEventClazz != null){
                nodeMap.put(clazz.getSimpleName(), (Class<Node>) clazz);
            }
        }

        return nodeMap;
    }
}
