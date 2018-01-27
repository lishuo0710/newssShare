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
    private int entityId;//作用对象id
    private int entityType;//事件作用的对象类型
    private int entityOwnerId;//事件主人
    private Map<String, String> exts = new ConcurrentHashMap<>();

    public Map<String, String> getExts() {
        return exts;
    }
    public EventModel() {

    }
    public EventModel(EventType type) {
        this.type = type;
    }


    public String getExt(String name) {
        return exts.get(name);
    }

    public EventModel setExt(String name, String value) {
        exts.put(name, value);
        return this;
    }

    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }

    public int getActorId() {
        return actorId;
    }

    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }
}
