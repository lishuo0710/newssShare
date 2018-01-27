package net.lsshare.Events;

import java.util.List;

/**
 * @author create by lishuo
 * @date 2018/1/27
 */
public interface EventHandler {

    void doHandler(EventModel eventModel);

    List<EventType> getSupportEventTypes();
}
