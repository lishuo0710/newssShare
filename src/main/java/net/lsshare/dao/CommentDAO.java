package net.lsshare.dao;

import net.lsshare.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */
@Mapper
public interface CommentDAO {
    String ENTITY_NAME = " comment ";
    String INSERT_FIELDS = " user_id, content, created_date, entity_id, entity_type, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    /**
     * 添加一条评论
     * @param comment
     * @return
     */
    @Insert({"insert into ",ENTITY_NAME,"(",INSERT_FIELDS,") " +
            "values (#{userId},#{content},#{createDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);
    /**
     * 查询一条评论
     */

    @Select({"select ",SELECT_FIELDS,"from",ENTITY_NAME,"where id = #{id}"})
    Comment selectById(int id);

    @Update({"update",ENTITY_NAME,"set status = #{status} where entity_id = #{entityId} and entity_type = #{entityType}"})
    int updateCommentStatus(@Param("entityId") int entityId,@Param("entityType") int entityType,@Param("status") int status);

    /**
     * 查询同一来源类型来源id的评论
     * @param entityId
     * @param entityType
     * @return
     */
    @Select({"select ", SELECT_FIELDS, " from ", ENTITY_NAME,
            " where entity_id=#{entityId} and entity_type=#{entityType} order by id desc"})
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    /**
     * 获取当前评论数
     * @param entityId
     * @param entityType
     * @return
     */
    @Select({"select count(id) from ", ENTITY_NAME, " where entity_id=#{entityId} and entity_type=#{entityType} "})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
}
