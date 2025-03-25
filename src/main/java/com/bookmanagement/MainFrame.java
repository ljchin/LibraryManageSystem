package main.java.com.bookmanagement;

import main.java.com.bookmanagement.util.CreatedIcon;
import main.java.com.bookmanagement.iframe.LoginFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    /*
    此为主窗体文件，尚不明总体架构，推测可能为LMS启动类
    此别名可设置为BookManagementApplication  启动类
    原设计此类似为Library类
     */


    //构造函数
    public MainFrame(){
        mainFra();
    }


    //主窗体的设计
    public void mainFra(){
        setTitle("图书管理系统");
        setBounds(320,180,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //创建菜单栏
        JMenuBar menuBar=createMenu();
        setJMenuBar(menuBar);

        //创建工具栏
        JToolBar toolBar=createToolBar();
        getContentPane().add(toolBar, BorderLayout.NORTH);

        //背景标签
        final JLabel label=new JLabel();


        setVisible(true);
    }


    //创建菜单栏
    private JMenuBar createMenu(){
        //基础数据维护，新书订购管理，借阅管理，系统维护
        JMenuBar menuBar=new JMenuBar();

        //初始化基础数据维护菜单，
        JMenu baseMenu=new JMenu("基础数据维护");
        baseMenu.setIcon(CreatedIcon.add("基础数据维护.png"));
        {
            JMenu readerManagerSubmenu=new JMenu("读者信息管理");


            JMenu bookInfoSubmenu=new JMenu("图书信息管理");


            JMenu bookTypeManageSubmenu=new JMenu("图书类别管理");


            baseMenu.add(readerManagerSubmenu);
            baseMenu.add(bookInfoSubmenu);
            baseMenu.add(bookTypeManageSubmenu);
            baseMenu.addSeparator();    //添加分隔符

        }

        //新书订购管理
        JMenu bookOrderMenu=new JMenu("新书订购管理");
        bookOrderMenu.setIcon(CreatedIcon.add("新书订购管理.png"));
//        bookOrderMenu.add();
//        bookOrderMenu.add();


        //借阅管理
        JMenu borrowManageMenu=new JMenu("借阅管理");
        borrowManageMenu.setIcon(CreatedIcon.add("借阅管理.png"));


        //系统维护
        JMenu sysManageMenu=new JMenu("系统维护");
        sysManageMenu.setIcon(CreatedIcon.add("系统维护.png"));

        {
            JMenu userManageSubmenu=new JMenu("用户管理");


            sysManageMenu.add(userManageSubmenu);
        }

        menuBar.add(baseMenu);
        menuBar.add(bookOrderMenu);
        menuBar.add(borrowManageMenu);
        menuBar.add(sysManageMenu);

        return menuBar;
    }


    //工具栏
    private JToolBar createToolBar(){
        JToolBar toolBar=new JToolBar("工具栏");//在创建组件的时候可以直接命名，试试

        toolBar.setFloatable(false);  //取消工具栏浮动
        toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));

        //为按钮添加 读者添加 动作对象
        JButton readerAddButton=new JButton("读者添加");
        readerAddButton.setIcon(CreatedIcon.add(""));//从这开始，图片尚未添加
//        readerAddButton.setHideActionText(true);
        readerAddButton.setToolTipText("添加读者信息");
        toolBar.add(readerAddButton);
        //读者修改与删除
        JButton readerModiAndDelButton=new JButton("修改与删除读者");
        readerModiAndDelButton.setIcon(CreatedIcon.add(""));
//        readerModiAndDelButton.setHideActionText(true);
        readerModiAndDelButton.setToolTipText("修改与删除读者信息");
        toolBar.add(readerModiAndDelButton);

        //bookInfo add
        JButton bookAddButton=new JButton("添加图书");
        bookAddButton.setIcon(CreatedIcon.add(""));
//        bookAddButton.setHideActionText(false);
        bookAddButton.setToolTipText("添加图书信息");
        toolBar.add(bookAddButton);
        //bookInfo modify delete
        JButton bookModiAndDeleteButton=new JButton("修改与删除图书");
        bookModiAndDeleteButton.setIcon(CreatedIcon.add(""));
//        bookModiAndDeleteButton.setHideActionText(false);
        bookModiAndDeleteButton.setToolTipText("修改与删除图书信息");
        toolBar.add(bookModiAndDeleteButton);

        //图书类别添加
        JButton bookTypeAddButton=new JButton("图书类别");
        bookTypeAddButton.setIcon(CreatedIcon.add(""));
        bookTypeAddButton.setToolTipText("添加图书类别");
        toolBar.add(bookTypeAddButton);

        //图书借阅
        JButton bookBorrowButton=new JButton("图书借阅");
        bookBorrowButton.setIcon(CreatedIcon.add(""));
        bookBorrowButton.setToolTipText("借阅图书");
        toolBar.add(bookBorrowButton);

        //图书订购
        JButton bookOrderButton=new JButton("图书订购");
        bookOrderButton.setIcon(CreatedIcon.add(""));
        bookOrderButton.setToolTipText("订购图书");
        toolBar.add(bookOrderButton);

        //退出
        JButton ExitButton=new JButton("退出");
        ExitButton.setIcon(CreatedIcon.add(""));
        ExitButton.setToolTipText("退出");
        toolBar.add(ExitButton);

        return toolBar;
    }








    //主程序，用以测试窗体，
    public static void main(String[] args){

        //Swing 是单线程的，所有的 GUI 更新必须在事件调度线程上进行，
        // 使得在正确的线程进行，避免线程冲突和潜在的 UI 更新错误。

        //法1
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MainFrame();
//            }
//        });

        //法2
        SwingUtilities.invokeLater(() -> new MainFrame());

        //法3
//        SwingUtilities.invokeLater(LoginFrame::new);
    }


}
