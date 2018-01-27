package net.lsshare.dao;

import net.lsshare.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author create by lishuo
 * @date 2018/1/24
 */

/**
 * User Crud
 */
@Mapper
public interface UserDAO {
     String  ENTITY_TABLE = "user";
     String INSET_FIELDS = " name, password, salt, head_url ";
     String SELECT_FIELDS = " id, name, password, salt, head_url ";

    /**
     * 添加一名用户
     * @param user
     * @return
     */
     @Insert({"insert into",ENTITY_TABLE,"(",INSET_FIELDS,") values (#{name},#{password},#{salt},#{headUrl})"})
     int addUser(User user);

    /**
     * 根据id查询一名用户
     * @param id
     * @return
     */
     @Select({"select",SELECT_FIELDS,"from",ENTITY_TABLE,"where id = #{id}"})
     User selectById(int id);

    /**
     * 根据用户名查询一名用户
     * @param name
     * @return
     */
     @Select({"select",SELECT_FIELDS,"from",ENTITY_TABLE,"where name = #{name}"})
     User selectByName(String name);

    /**
     * 修改密码
     * @param user
     */
     @Update({"update ", ENTITY_TABLE, " set password=#{password} where id=#{id}"})
     void updatePassword(User user);

    /**
     * 删除一名用户
     * @param id
     */
     @Delete({"delete from ", ENTITY_TABLE, " where id=#{id}"})
     void deleteById(int id);
}
