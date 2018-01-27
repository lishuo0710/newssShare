package net.lsshare.Events;

/**
 * @author create by lishuo
 * @date 2018/1/25
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 携带事件信息的数据模型
 */
public class EventModel {
    private EventType type;
    private int actorId;
    private int entityId;
    private int entityType;
    private int entityOwnerId;
    private Map<String, String> exts = new ConcurrentHashMap<>();

    public Map<String, String> getExts() {
        return exts;
    }
    public EventModel() {

    }
    public EventModel(EventType type) {
        this.type = type;
    }
}
