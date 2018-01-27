package net.lsshare.Events.Handlers;

import net.lsshare.Events.EventHandler;
import net.lsshare.Events.EventModel;
import net.lsshare.Events.EventType;

import java.util.Arrays;
import java.util.List;

/**
 * @author create by lishuo
 * @date 2018/1/27
 */
public class LikeEventHandler implements EventHandler{
    /**
     * 处理对应的
     * @param eventModel
     */
    @Override
    public void doHandler(EventModel eventModel) {
        System.out.println("to do");
    }

    /**
     * 添加支持的事件类型
     * @return
     */
    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
