package net.lsshare.utils;

/**
 * @author create by lishuo
 * @date 2018/1/25
 */

/**
 * 为每一次点击事件设置key值
 */
public class RedisKeyUtil {
    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";//喜欢
    private static String BIZ_DISLIKE = "DISLIKE";//不喜欢
    private static String BIZ_EVENT = "EVENT";//事件

    public static String getEventQueueKey() {
        return BIZ_EVENT;
    }

    public static String getLikeKey(int entityId, int entityType) {
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    public static String getDisLikeKey(int entityId, int entityType) {
        return BIZ_DISLIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }
}

