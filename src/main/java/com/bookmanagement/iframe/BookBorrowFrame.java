package main.java.com.bookmanagement.iframe;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookBorrowFrame extends JFrame {

    private static JTextField readerID;
    private static JTextField readerName;
    private static JTextField MaxBorrowBookNum;
    private static JTextField GuaranteeMoney; //押金

    private static JTextField BookISBN;
    private static JTextField BookName;
    private static JTextField BookType;
    private static JTextField BookPrice;

    private static DefaultTableModel defaultTableModel;
    private static JTable jTable;

    private static JButton BorrowBookButton;
    private static JButton ClearNoteButton;

    public BookBorrowFrame(){
        setTitle("图书借阅管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,180,800,600);

        //本窗体需要考虑 3 个表，读者信息，图书信息，借阅表
        {
            JPanel panel=new JPanel();
            panel.setLayout(new GridLayout(4,4));

            panel.add(new JLabel("读者编号："));
            readerID=new JTextField(20);
            panel.add(readerID);
            panel.add(new JLabel("书籍编号："));
            BookISBN=new JTextField(20);
            panel.add(BookISBN);
            panel.add(new JLabel("读者姓名："));
            readerName=new JTextField(20);
            panel.add(readerName);
            panel.add(new JLabel("书籍名称："));
            BookName=new JTextField(20);
            panel.add(BookName);
            panel.add(new JLabel("可借数量："));
            MaxBorrowBookNum=new JTextField(20);
            panel.add(MaxBorrowBookNum);
            panel.add(new JLabel("书籍类别："));
            BookType=new JTextField(20);
            panel.add(BookType);
            panel.add(new JLabel("押    金："));
            GuaranteeMoney=new JTextField(20);
            panel.add(GuaranteeMoney);
            panel.add(new JLabel("书籍价格："));
            BookPrice=new JTextField(20);
            panel.add(BookPrice);

            add(panel,BorderLayout.NORTH);
        }

        {//中部的表格；
            String[] tableNames=new String[]{"书籍编号","借书日期","应还日期","读者编号"};
            defaultTableModel=new DefaultTableModel(tableNames,0);
            jTable=new JTable(defaultTableModel);

            JScrollPane jScrollPane=new JScrollPane(jTable);

//            jScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            jScrollPane.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));  // 上右下左的内边距都是 10
//            jScrollPane.setBorder();

            add(jScrollPane,BorderLayout.CENTER);
        }

        {//下方
            JPanel panelSouth=new JPanel();
            panelSouth.setLayout(new GridLayout(2,3));

            panelSouth.add(new JLabel("当前时间："));
            panelSouth.add(new JLabel("   "));//时间
            BorrowBookButton=new JButton("借出当前书籍");
            panelSouth.add(BorrowBookButton);

            panelSouth.add(new JLabel("操 作 员："));
            panelSouth.add(new JLabel("   "));//下拉组合框
            ClearNoteButton=new JButton("清除所有记录");
            panelSouth.add(ClearNoteButton);

            add(panelSouth,BorderLayout.SOUTH);
        }


        setVisible(true);
    }


    //主程序，用以测试窗体，
    public static void main(String[] args){

        //法3
        SwingUtilities.invokeLater(BookBorrowFrame::new);
    }
    

}
