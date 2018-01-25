package net.lsshare.model;

import org.springframework.stereotype.Component;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
