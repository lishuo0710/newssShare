package net.lsshare.service;

import org.springframework.stereotype.Service;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */
@Service
public class LikeService {
    public int getLikeStatus(int userId, int entityType, int entityId) {
        /*String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        if(jedisAdapter.sismember(likeKey, String.valueOf(userId))) {
            return 1;
        }
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        return jedisAdapter.sismember(disLikeKey, String.valueOf(userId)) ? -1 : 0;*/
        return 0;
    }
}
