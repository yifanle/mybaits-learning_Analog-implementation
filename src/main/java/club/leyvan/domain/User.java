package club.leyvan.domain;

import java.util.Date;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.domain
 * @version: 1.0
 */
public class User {
    /**
     * `id` int(11) NOT NULL AUTO_INCREMENT,
     *   `username` varchar(32) NOT NULL COMMENT '用户名称',
     *   `birthday` date DEFAULT NULL COMMENT '生日',
     *   `sex` char(1) DEFAULT NULL COMMENT '性别',
     *   `address` varchar(256) DEFAULT NULL COMMENT '地址',
     */
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
