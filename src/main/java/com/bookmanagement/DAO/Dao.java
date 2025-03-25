package main.java.com.bookmanagement.DAO;

import main.java.com.bookmanagement.model.Operator;

import java.sql.*;


public class Dao {
    /*
    数据访问层常用 DAO 来表示，是Data Access Object 的缩写，
    DAO 是一种设计模式，用于将 数据访问 与 业务逻辑 分离，是一种经典的设计模式。

    但是现代开发替代方案有：
    Repository 模式
    ORM 模式

    DAO 的核心思想（职责分离）仍然使用。
     */


    private static String URL="jdbc:mysql://localhost:3306/LibraryMS";
    private static String User="root";
    private static String Password="123456qin";

    public static Connection conn;

    //驱动包，用于数据库连接，构造函数
    private Dao(){
        try{
            if(conn==null){
                conn=DriverManager.getConnection(URL,User,Password);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    //数据库查询操作，
    public static ResultSet executeQuery(String sql){
        try{
            if(conn==null){
                new Dao();
                return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
                //创建一个可以滚动（向前或向后查看）的，
                //可以更新的结果集对象
            }
        }catch( SQLException e){
            e.printStackTrace();
            return null;
        }

        return null;
    }


    //数据库更新操作
    private static int executeUpdate(String sql){


        return 0;
    }


    //避免运行程序时资源的浪费，优化运行速度，关闭数据库连接
    public static void close(){
        try{
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            conn=null;
        }
    }

    public static Operator check(String name,char[] password){

        Operator operator=new Operator();

        String sql="select * from tb_operator\n" +
                "where name="+name+" and password="+password+" and admin=1";
        ResultSet rt=Dao.executeQuery(sql);
        try{
            while(rt.next()){
                operator.setID(rt.getInt("id"));
                operator.setName(rt.getString("name"));
                operator.setGrade(rt.getInt("admin"));
                operator.setPassword(rt.getString("password"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Dao.close();

        return operator;
    }


    public static int InsertBookInfo(String ISBN, int typeID, String bookName, String author,
                                     String translator, String publisher, Date date,int price){
        int i=0;
        try{
            String sql="";
            i=Dao.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        Dao.close();
        return i;
    }


}
