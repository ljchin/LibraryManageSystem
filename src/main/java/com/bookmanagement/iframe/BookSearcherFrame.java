package main.java.com.bookmanagement.iframe;

import main.java.com.bookmanagement.DAO.Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSearcherFrame extends JFrame {

    private static JTabbedPane tabbedPane;
    private static JTextField search;
    private static DefaultTableModel defaultTableModel;
    private static JTable table;
    private static DefaultTableModel defaultTableModel2;
    private static JTable table2;

    private static JButton searchButton;
    private static JButton closeButton;

    public BookSearcherFrame(){

        DesignSearcherFrame();
    }

    public void DesignSearcherFrame(){

        setTitle("图书查询");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,180,800,600);

        tabbedPane=new JTabbedPane();

        {//条件查询，
            JPanel panel1=new JPanel(new BorderLayout());
            JPanel panel2=new JPanel(new GridLayout(4,2));
            panel2.add(new JLabel("请选择查询项目"));
            panel2.add(new JLabel());
            panel2.add(new JLabel("图  书  名  称："));
            search=new JTextField(20);
            panel2.add(search);
            panel2.add(new JLabel());
            panel2.add(new JLabel());
            panel2.add(new JLabel("查询结果如下"));
            panel1.add(panel2,BorderLayout.NORTH);

            JPanel panel3=new JPanel();
//            panel3.add(new JLabel("查询结果显示"));
            String[] tableSearchResult=new String[]{"编号","类别","名称","作者","译者","出版时间","出版社","价格"};
            defaultTableModel=new DefaultTableModel(tableSearchResult,0);
            table=new JTable(defaultTableModel);
//            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            JScrollPane scrollPane=new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//            scrollPane.setPreferredSize(new Dimension(700,400));
            panel3.add(scrollPane);
//            panel3.setSize(new Dimension(700,400));
            panel1.add(panel3,BorderLayout.CENTER);


            JPanel panel4=new JPanel(new GridLayout(1,2));
            searchButton=new JButton("查询");
            searchButton.addActionListener(new searchButtonListener());
            closeButton=new JButton("退出");
            closeButton.addActionListener(new closeFrameListener());
//            searchButton.setSize(new Dimension(50,30));
//            closeButton.setSize(new Dimension(50,30));
            panel4.add(searchButton);
            panel4.add(closeButton);
            panel1.add(panel4,BorderLayout.SOUTH);

            tabbedPane.addTab("条件查询",panel1);
        }
        //使用GridLayout布局管理会使得各部分尽可能的大小相等，自己调节组件大小无用，
        //使用BorderLayout布局管理，中间部分会大，但组件大小会以原先的为准


        {//选项卡2
            JPanel panel1_2=new JPanel();
            String[] bookTable=new String[]{"ISBN","图书类别","图书名称","作者","译者","出版社","日期","价格"};
            defaultTableModel2=new DefaultTableModel(bookTable,0);
            table=new JTable(defaultTableModel2);
            JScrollPane jScrollPane2=new JScrollPane(table);
            panel1_2.add(jScrollPane2);

            showAllBookInfo();



            tabbedPane.addTab("显示图书全部信息",panel1_2);
        }

        add(tabbedPane);
        setVisible(true);
    }

    public class searchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String str=search.getText();
            String sqlStr="select *\n" +
                    "from tb_bookInfo\n" +
                    "where bookname like '%'"+str+"'%'";
            try{
                ResultSet resultSet= Dao.executeQuery(sqlStr);
                while(resultSet.next()){
                    String[] ss=new String[]{resultSet.getString("ISBN"), String.valueOf(resultSet.getInt("typeid")),
                    resultSet.getString("bookName"),resultSet.getString("author"),resultSet.getString("translator"),
                    resultSet.getString("publisher"), String.valueOf(resultSet.getDate("date")), String.valueOf(resultSet.getInt("Price"))};
                    defaultTableModel.addRow(ss);
                }
                table=new JTable(defaultTableModel);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }


            System.out.println();
        }
    }

    public class closeFrameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();

            //可能需要切换其他的Frame
        }
    }

    //选项卡2
    public void showAllBookInfo(){
        String sql2="select * from tb_bookInfo;";
        try{
            ResultSet rs2=Dao.executeQuery(sql2);
            while(rs2.next()){
                String[] rs2Str=new String[]{rs2.getString("ISBN"), String.valueOf(rs2.getInt("typeID")),
                rs2.getString("bookName"),rs2.getString("author"),rs2.getString("translator"),
                rs2.getString("publisher"), String.valueOf(rs2.getDate("date")), String.valueOf(rs2.getInt("price"))};
                defaultTableModel2.addRow(rs2Str);
            }
            table2=new JTable(defaultTableModel2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //测试主程序
    public static void main(String[] args){


        SwingUtilities.invokeLater(BookSearcherFrame::new);
    }
}
