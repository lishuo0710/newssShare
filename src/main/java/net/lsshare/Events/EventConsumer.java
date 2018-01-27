package net.lsshare.Events;

import com.alibaba.fastjson.JSON;
import net.lsshare.utils.JedisAdapter;
import net.lsshare.utils.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author create by lishuo
 * @date 2018/1/27
 */

/**
 * 创建消费者
 */
@Component
public class EventConsumer implements InitializingBean, ApplicationContextAware {

  /*  InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，
    凡是继承该接口的类，在初始化bean的时候会执行该方法。*/

   /* 实现了这个ApplicationContextAware接口之后，可以方便获得ApplicationContext中的所有bean*/

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private Map<EventType, List<EventHandler>> config = new HashMap<>();
    private ApplicationContext applicationContext;
    @Autowired
    private JedisAdapter jedisAdapter;


    @Override
    public void afterPropertiesSet() throws Exception {
        //1.获取所有的EventHandler
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if(beans != null){
            for(Map.Entry<String,EventHandler> item : beans.entrySet()){
                //2.从Handler中获取其支持的事件类型
                List<EventType> eventTypes = item.getValue().getSupportEventTypes();
                //3.初始话config
                for(EventType eventType : eventTypes){
                    if(!config.containsKey(eventType)){
                        config.put(eventType,new ArrayList<EventHandler>());
                    }
                    //4.为事件注册对应的handler函数
                    config.get(eventType).add(item.getValue());
                }
            }
        }
        //5.创建一个线程去消费事件
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //6.消费队列中的事件
                while (true){
                    String key = RedisKeyUtil.getEventQueueKey();
                    List<String> msgs = jedisAdapter.brpop(0,key);//获取队列名字
                    for(String msg : msgs){
                        if(msg.equals(key)){
                            continue;
                        }
                        EventModel eventModel = JSON.parseObject(msg, EventModel.class);
                        //7. 找到这个事件的处理handler列表
                        if(!config.containsKey(eventModel.getType())){
                            logger.error("不能被识别的事件类型");
                            continue;
                        }
                        //8.处理事件
                        for (EventHandler handler : config.get(eventModel.getType())){
                            handler.doHandler(eventModel);
                        }
                    }
                }
            }
        });



    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;//获取应用上下文
    }
}
