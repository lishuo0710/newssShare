package net.lsshare.controller;

import net.lsshare.model.EntityType;
import net.lsshare.model.HostHolder;
import net.lsshare.service.LikeService;
import net.lsshare.service.NewsService;
import net.lsshare.utils.MD5Util;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author create by lishuo
 * @date 2018/1/25
 */
@Controller
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    NewsService newsService;

    /**
     * 更新喜欢数量
     * @param newsId
     * @return
     */
    @RequestMapping("/like")
    @ResponseBody
    public String like(@Param("newId") int newsId){
        long likecount = likeService.like(hostHolder.getUser().getId(),EntityType.ENTITY_NEWS,newsId);
        newsService.updateLikeCount(newsId, (int) likecount);
        return MD5Util.getJSONString(0, String.valueOf(likecount));
    }
    @RequestMapping(path = {"/dislike"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String dislike(@Param("newId") int newsId) {
        long likeCount = likeService.disLike(hostHolder.getUser().getId(), EntityType.ENTITY_NEWS, newsId);
        // 更新喜欢数
        newsService.updateLikeCount(newsId, (int) likeCount);
        return MD5Util.getJSONString(0, String.valueOf(likeCount));
    }
}
