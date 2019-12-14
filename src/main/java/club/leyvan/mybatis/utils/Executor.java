package club.leyvan.mybatis.utils;

import club.leyvan.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: http://www.leyvan.club
 * @Date: 2019/12/13
 * @Description: club.leyvan.mybatis.cfg
 * @version: 1.0
 */
public class Executor {
    
    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try{
            //从mapper中取得xml中得映射信息
            //1.查询语句
            String queryString = mapper.getQueryString();
            //2.查询后返回的实体类型
            String resultType = mapper.getResultType();
            //根据resultType（Stirng）获得Class对象用于后面得反射创建
            Class domainClass = Class.forName(resultType);
            //查询数据库
            psmt = conn.prepareStatement(queryString);
            rs = psmt.executeQuery();
            //封装结果用的结果集
            List<E> list = new ArrayList<E>();
            //封装读取得数据
            while(rs.next()){
                //实体化一个实例用于封装一条数据
                E domain = (E)domainClass.newInstance();
                //rs为数据库中的横向数据集,要获得纵向得每一个数据元得集
                ResultSetMetaData rsmd = rs.getMetaData();
                //获取每一条数据得列数
                int columnNum = rsmd.getColumnCount();
                //遍历每列数据得列名和值，列名用于给实体类赋值 列名序号从1开始
                for(int i=1;i<=columnNum;i++){
                    //获得列名
                    String columnName = rsmd.getColumnName(i);
                    //根据列名获得每列得值
                    Object columnValue = rs.getObject(columnName);
                    //PropertyDescriptor:java内省操作类 要求：columnName要和实体类得属性名相同
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);
                    //通过内省操作获得字段得写方法
                    Method writeMethod = pd.getWriteMethod();
                    //调用写方法：第一个参数：哪个对象得方法 第二个参数：该对象写方法的参数
                    writeMethod.invoke(domain,columnValue);
                }
                //实体封装完成，放入list
                list.add(domain);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            release(psmt,rs);
        }
    }


    private void release(PreparedStatement pstm,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
