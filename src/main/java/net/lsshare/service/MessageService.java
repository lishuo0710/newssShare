package net.lsshare.service;

import net.lsshare.dao.MessageDAO;
import net.lsshare.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author create by lishuo
 * @date 2018/1/27
 */
@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    @Autowired
    private MessageDAO messageDAO;
/*    @Autowired
    HostHolder hostHolder;
    @Autowired
    JedisAdapter jedisAdapter;*/

    public int addMessage(Message message){
        //logger.info("");
        return messageDAO.addMessage(message);
    }
    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
        return messageDAO.getConversationDetail(conversationId, offset, limit);
    }
    public List<Message> getConversationList(int userId, int offset, int limit) {
        return messageDAO.getConversationList(userId, offset, limit);
    }

    public int getConvesationUnreadCount(int userId, String conversationId) {
        return messageDAO.getConvesationUnreadCount(userId, conversationId);
    }
}
