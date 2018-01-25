package net.lsshare.model;

import java.util.Date;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */
public class LoginTicket {
    private int id;
    private int userId;//所属用户
    private Date expired;//失效日期
    private int status;// 0有效，1无效
    private String ticket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
