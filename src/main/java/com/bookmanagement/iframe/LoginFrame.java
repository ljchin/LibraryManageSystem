package main.java.com.bookmanagement.iframe;

import javax.swing.*;
import java.awt.*;


public class LoginFrame extends JFrame {
    /*
    子窗口文件夹
     */

    //构造函数
    public LoginFrame(){
        super();
        DesignLoginFrame();
    }

    public void DesignLoginFrame(){
        setTitle("图书馆管理系统登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        //镶板，嵌板 center
        final JPanel panel_2=new JPanel();
        final GridLayout gridLayout=new GridLayout(0,2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(20);
        panel_2.setLayout(gridLayout);

        //
        final JLabel label=new JLabel("用 户 名：");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(label);

        JTextField username=new JTextField(20);
        panel_2.add(username);

        final JLabel label1=new JLabel("密   码：");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(label1);

        JPasswordField passwordField=new JPasswordField(20);
        passwordField.setEchoChar('*');  //设置回显字符
        panel_2.add(passwordField);

        // 嵌板 south
        final JPanel panel_1=new JPanel();








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
        SwingUtilities.invokeLater(LoginFrame::new);
    }




}
