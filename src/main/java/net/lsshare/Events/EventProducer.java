package net.lsshare.Events;

import com.alibaba.fastjson.JSONObject;
import net.lsshare.service.UserService;
import net.lsshare.utils.JedisAdapter;
import net.lsshare.utils.MD5Util;
import net.lsshare.utils.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author create by lishuo
 * @date 2018/1/27
 */
@Component
public class EventProducer {
    private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);
    @Autowired
    JedisAdapter jedisAdapter;
    public boolean fireEvents(EventModel eventModel){
        try{
            String jason = JSONObject.toJSONString(eventModel);//序列化事件模型
            String key = RedisKeyUtil.getEventQueueKey();//获取异步队列的key
            jedisAdapter.lpush(key,jason);
            return true;
        }catch (Exception ex){
            logger.info("发送事件失败");
            return false;
        }
    }
}
