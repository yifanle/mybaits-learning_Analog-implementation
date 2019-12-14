package club.leyvan.mybatis.cfg;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.mybaits.cfg
 * @version: 1.0
 */

public class Mapper {
    private String queryString; //
    private String resultType; //用于反射创建实体对象

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
