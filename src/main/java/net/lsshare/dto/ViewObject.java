package net.lsshare.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */
public class ViewObject {
    private Map<String, Object> objs = new HashMap<String, Object>();
    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
