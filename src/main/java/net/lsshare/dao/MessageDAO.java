package net.lsshare.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */
@Mapper
public interface MessageDAO {
    String ENTITY_NAME = " message ";
    String INSERT_FIELDS = " from_id, to_id, content, has_read, conversation_id, created_date ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
}
