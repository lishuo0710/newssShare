package net.lsshare.service;

import net.lsshare.dao.LoginTicketDAO;
import net.lsshare.dao.UserDAO;
import net.lsshare.model.LoginTicket;
import net.lsshare.model.User;
import net.lsshare.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired(required = false)
    private UserDAO userDAO;

    private LoginTicketDAO loginTicketDAO;

    @Autowired
    public void setLoginTicketDAO(LoginTicketDAO loginTicketDAO) {
        this.loginTicketDAO = loginTicketDAO;
    }

    /**
     * 用户注册服务
     * @param name
     * @param pwd
     * @return
     */
    public ConcurrentHashMap<String,Object> register(String name, String pwd){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
        //校验注册信息
        if(StringUtils.isBlank(name)){
            map.put("msgname","用户名不能为空!");
            return map;
        }
        if(StringUtils.isBlank(pwd)){
            map.put("msgname","密码不能为空!");
            return map;
        }
        //存在性校验
        User user = userDAO.selectByName(name);
        if(user != null){
            map.put("msgname","该用户名已存在!");
            return map;
        }
        //加强密码强度
        user = new User();
        user.setName(name);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));//老板加盐
        String head = String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(MD5Util.MD5(pwd+user.getSalt()));
        userDAO.addUser(user);
        //注册成功为用户添加ticket并返回到前段页面直接完成登录认证
        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return map;
    }
    public ConcurrentHashMap<String,Object> login(String name , String pwd){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
        if(StringUtils.isBlank(name)){
            map.put("msgname","用户名不能为空!");
            return map;
        }
        if(StringUtils.isBlank(pwd)){
            map.put("msgname","密码不能为空!");
            return map;
        }
        User user = userDAO.selectByName(name);
        if(!MD5Util.MD5( pwd+user.getSalt()).equals(user.getPassword())){
            map.put("msgpwd","密码不正确!");
            return map;
        }
        map.put("userId", user.getId());
        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return map;
    }

    /**
     * 为用户添加ticket
     * @param userId
     * @return
     */
    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);//设置ticket失效时间
        ticket.setExpired(date);
        ticket.setStatus(0);//设置当前有效
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));//设置ticket
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }
    public User getUser(int id) {
        return userDAO.selectById(id);
    }

    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);
    }
}
