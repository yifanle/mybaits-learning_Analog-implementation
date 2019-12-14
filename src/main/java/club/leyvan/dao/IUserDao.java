package club.leyvan.dao;

import club.leyvan.domain.User;
import club.leyvan.mybatis.annotation.Select;

import java.util.List;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.dao
 * @version: 1.0
 */
public interface IUserDao {

    @Select("select * from user")
    public List<User> findAll();

}
