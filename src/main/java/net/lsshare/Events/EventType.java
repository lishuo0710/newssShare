package net.lsshare.Events;

/**
 * @author create by lishuo
 * @date 2018/1/25
 */
public enum EventType {
    LIKE(0),
    DISLIKE(1),
    REG(2),//注册事件
    LOGIN(3),
    MAIL(4);
    private int value;

    EventType(int value){
        this.value = value;
    }
}
