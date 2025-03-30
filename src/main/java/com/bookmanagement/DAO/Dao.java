package main.java.com.bookmanagement.DAO;

import main.java.com.bookmanagement.model.BookBorrow;
import main.java.com.bookmanagement.model.Operator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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


    //以下这两个类本意应该是设置为私有方法，以便创建具体的SQL实现方法后调用这两个类，
    //可是开始没想到，现在是推测；
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

        try{
            if(conn==null){
                new Dao();
            }
            return conn.createStatement().executeUpdate(sql);
        }catch(SQLException ee){
//            ee.printStackTrace();
            System.out.println(ee.getMessage());
            return -1;
        }

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

    //如果说上面的几个是私有类，下面的就是其他函数所需的具体实现方法，
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
            String sql="insert into tb_bookInfo(ISBN,typeid,bookname,author,translator,publisher,date,price)\n" +
                    "value('"+ISBN+"','"+typeID+"','"+bookName+"','"+author+"','"+translator+"','"+publisher+"','"+date+"','"+price+")";
            i=Dao.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        Dao.close();
        return i;
    }


    //此类准备改改，这里，尝试JTable直接装完返回，试试
    public static JTable selectBookInfo(){
//        List<BookInfo> list=new ArrayList<>();
        //这里我直接使用数组，不使用Object[][];
//        BookInfo[] bookInfos=new BookInfo[];

        //创建表格
        String[] columnNames=new String[]{"图书编号","图书类别","图书名称","作者","译者","出版商","出版日期","价格"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(columnNames,0);
        JTable table=new JTable(defaultTableModel);

        //查询数据
        String sql="select * from tb_bookInfo";
        ResultSet rs=Dao.executeQuery(sql);

        //数据赋值
        try{
            while(rs.next()){
//                BookInfo bookInfo=new BookInfo();
//                bookInfo.setISBN(rs.getString("ISBN"));
//                bookInfo.setTypeID(rs.getInt("typeID"));
//                bookInfo.setBookName(rs.getString("bookName"));
//                bookInfo.setAuthor(rs.getString("author"));
//                bookInfo.setTranslator(rs.getString("translator"));
//                bookInfo.setPublisher(rs.getString("publisher"));
//                bookInfo.setDate(rs.getDate("date"));
//                bookInfo.setPrice(rs.getInt("price"));
//
//                list.add(bookInfo);
                String isbn=rs.getString("ISBN");
                int typeID =rs.getInt("typeID");
                String bookName=rs.getString("bookName");
                String author=rs.getString("author");
                String translator=rs.getString("translator");
                String publisher=rs.getString("publisher");
                Date date=rs.getDate("date");
                int price=rs.getInt("price");

                defaultTableModel.addRow(new Object[]{isbn,typeID,bookName,author,
                        translator,publisher,date,price});
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        Dao.close();
        return table;
    }


    public static int UpdateBookInfo(String ISBN, String bookName, String author,
                                     String translator, String publisher, String date, int price){

        try{
//            String updateSQL="update tb_bookInfo set bookName='ad' ,author=' ',translator=' ',\n" +
//                    "                       publisher=' ',date=' ',price=''\n" +
//                    "where ISBN=''";
            String updateSQL = "UPDATE tb_bookInfo SET bookName='" + bookName + "', " +
                    "author='" + author + "', translator='" + translator + "', " +
                    "publisher='" + publisher + "', date='" + date + "', " +
                    "price=" + price + " WHERE ISBN='" + ISBN + "'";
            //这种方式是不推荐的容易收到SQL注入的攻击，
            //以下是推荐的写法，这中可以放着SQL注入的危害，但是本次写，我把executeUpdate分开写了，
            // 就暂且不用以下方法，
//            String updateSQL = "UPDATE tb_bookInfo SET bookName=?, author=?, translator=?, publisher=?, date=?, price=? WHERE ISBN=?";
//
//            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
//
//                // 设置 PreparedStatement 的参数
//                pstmt.setString(1, bookName);
//                pstmt.setString(2, author);
//                pstmt.setString(3, translator);
//                pstmt.setString(4, publisher);
//                pstmt.setString(5, date);
//                pstmt.setInt(6, price);
//                pstmt.setString(7, ISBN);

            Dao.executeUpdate(updateSQL);
        }catch(Exception eee){
            eee.printStackTrace();
        }

        return 0;
    }


    public static int InsertBookBorrow(String ISBN, String readerID, int operator,
                                       LocalDate borrowDate,LocalDate backDate){

        return 0;
    }

    public static List<BookBorrow> selectBorrowInfo(String str){

        List<BookBorrow> resultList=new ArrayList<>();
        String sql="select bookName,bookISBN,typeid,Name,borrowDate,backdate\n" +
                "        from tb_borrow\n" +
                "        join tb_bookinfo on tb_bookinfo.ISBN=tb_borrow.bookISBN\n" +
                "        join tb_reader on tb_reader.id=tb_borrow.readerID\n" +
                "        where tb_borrow.readerID=?";

        try(PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1,str);
            pstmt.executeQuery();

            ResultSet resultSet=pstmt.getResultSet();
            while(resultSet.next()){
                BookBorrow bb=new BookBorrow();
                bb.setBookName(resultSet.getString("bookName"));
                bb.setBookIsbn(resultSet.getString("bookISBN"));
                bb.setTypeID(resultSet.getInt("typeID"));
                bb.setName(resultSet.getString("Name"));
                bb.setBorrowDate(resultSet.getDate("borrowDate"));
                bb.setBackDate(resultSet.getDate("backDate"));

                resultList.add(bb);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    public static int addOrderInfo(String isbn,Date da,int num,String opera,int isCheck,float discount){

        int i=-1;
        String sql="insert into tb_order(*) value(?,?,?,?,?,?)";

        try(PreparedStatement pst=conn.prepareStatement(sql)){
            pst.setString(1,isbn);
            pst.setDate(2,da);
            pst.setInt(3,num);
            pst.setString(4,opera);
            pst.setInt(5,isCheck);
            pst.setFloat(6,discount);

            pst.executeUpdate();
            i=1;
        }catch(SQLException E){
            E.printStackTrace();
        }

        return i;
    }




}
