package main.java.com.bookmanagement;

import main.java.com.bookmanagement.util.CreatedIcon;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    /*
    此为主窗体文件，尚不明总体架构，推测可能为LMS启动类
    此别名可设置为BookManagementApplication  启动类

     */


    //构造函数
    public MainFrame(){
        mainFra();
    }


    //主窗体的设计
    public void mainFra(){
        setTitle("图书管理系统");
        setBounds(40,30,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //创建菜单栏
        JMenuBar menuBar=createMenu();
        setJMenuBar(menuBar);

        //创建工具栏
        JToolBar toolBar;
//        getContentPane().add(toolBar, BorderLayout.NORTH);

        //背景标签
        final JLabel label=new JLabel();


        setVisible(true);
    }


    //创建菜单栏
    private JMenuBar createMenu(){
        //基础数据维护，新书订购管理，借阅管理，系统维护
        JMenuBar menuBar=new JMenuBar();

        //初始化基础数据维护菜单，
        JMenu baseMenu=new JMenu();
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
        JMenu bookOrderMenu=new JMenu();
        bookOrderMenu.setIcon(CreatedIcon.add("新书订购管理.png"));
//        bookOrderMenu.add();
//        bookOrderMenu.add();


        //借阅管理
        JMenu borrowManageMenu=new JMenu();
        borrowManageMenu.setIcon(CreatedIcon.add("借阅管理.png"));


        //系统维护
        JMenu sysManageMenu=new JMenu();
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
        JToolBar toolBar=new JToolBar();


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
//        SwingUtilities.invokeLater(() -> new MainFrame());

        //法3
        SwingUtilities.invokeLater(MainFrame::new);
    }


}
