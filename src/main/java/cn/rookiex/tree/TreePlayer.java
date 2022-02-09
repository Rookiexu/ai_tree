package cn.rookiex.tree;

import com.alibaba.fastjson.JSONObject;

public class TreePlayer {

    // |0-1-2-3-4-5-6-7-8-9-10----------------|
    // |家-1-2-书店-4-电影院-6-公园-8-9-10----------------|

    private int playerId;

    private int local = 0;

    private int money = 30;

    private int energy = 50;


    private JSONObject info = new JSONObject();

    public TreePlayer() {
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public JSONObject getInfo() {
        return info;
    }

    public void setInfo(JSONObject info) {
        this.info = info;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void addItem(JSONObject add){
        for (String itemId : add.keySet()) {
            int count = add.getIntValue(itemId);
            switch (itemId) {
                case "money":
                    setMoney(getMoney() + count);
                    System.out.println(getPlayerId() + " 获得money " + count);
                    break;
                case "energy":
                    setEnergy(getEnergy() + count);
                    System.out.println(getPlayerId() + " 获得energy " + count);
                    break;
            }
        }
    }

    public void decrItem(JSONObject decr){
        for (String itemId : decr.keySet()) {
            int count = decr.getIntValue(itemId);
            switch (itemId) {
                case "money":
                    setMoney(getMoney() - count);
                    System.out.println(getPlayerId() + " 失去money " + count);
                    break;
                case "energy":
                    setEnergy(getEnergy() - count);
                    System.out.println(getPlayerId() + " 失去energy " + count);
                    break;
            }
        }
    }

    public boolean isEnough(JSONObject need){
        boolean rich = true;
        if (need != null) {
            for (String itemId : need.keySet()) {
                int count = need.getIntValue(itemId);
                switch (itemId) {
                    case "money":
                        rich &= getMoney() >= count;
                        break;
                    case "energy":
                        rich &= getEnergy() >= count;
                        break;
                }
            }
        }
        return rich;
    }
}
