package net.lsshare.dao;

import net.lsshare.model.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */

/**
 * 咨询 Crud
 */
@Component
@Mapper
public interface NewsDAO {
    String ENTITY_NAME = "news";
    String INSERT_FIELDS = " title, link, image, like_count, comment_count, created_date, user_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
    /**
     * 添加一条咨询
     */
    @Insert({"insert into ",ENTITY_NAME,"(",INSERT_FIELDS,
            ") values (#{title},#{image},#{link},#{likeCount},#{commentCount},#{createdDate},#{userId}))"})
    int addNews(News news);

    /**
     * 查询一条咨询
     */
    @Select({"select ", SELECT_FIELDS , " from ", ENTITY_NAME, " where id=#{id}"})
    News selectByid(int id);

    /**
     * 更新评论数
     * @param id
     * @param commentCount
     * @return
     */
    @Update({"update ", ENTITY_NAME, " set comment_count = #{commentCount} where id=#{id}"})
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

    /**
     * 更新喜欢数
     * @param id
     * @param likeCount
     * @return
     */
    @Update({"update ", ENTITY_NAME, " set like_count = #{likeCount} where id=#{id}"})
    int updateLikeCount(@Param("id") int id, @Param("likeCount") int likeCount);

    /**
     * 分页显示咨询
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
   // @Select({"select",SELECT_FIELDS,"from",ENTITY_NAME,"where id={#userId} limit #{offset},#{limit} "})
    List<News> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
                                       @Param("limit") int limit);
}
