package main.java.com.bookmanagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Dao {
    /*
    数据访问层常用 DAO 来表示，是Data Access Object 的缩写，
    DAO 是一种设计模式，用于将 数据访问 与 业务逻辑 分离，是一种经典的设计模式。

    但是现代开发替代方案有：
    Repository 模式
    ORM 模式

    DAO 的核心思想（职责分离）仍然使用。
     */



    //驱动包，用于数据库连接，
    private Dao(){



    }


    //数据库查询操作，
    private static ResultSet executeQuery(String sql){


        return null;
    }


    //数据库更新操作
    private static int executeUpdate(){


        return 0;
    }


    //避免运行程序时资源的浪费，优化运行速度，关闭数据库连接
    public static void close(){

    }


}
