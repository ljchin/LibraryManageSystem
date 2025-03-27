package main.java.com.bookmanagement.iframe;

import main.java.com.bookmanagement.MainFrame;
import main.java.com.bookmanagement.model.Operator;
import main.java.com.bookmanagement.DAO.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {
    /*
    子窗口文件夹
     */

    private static JTextField usernameField;
    private static JPasswordField passwordField;

    //构造函数
    public LoginFrame(){
        super();
        DesignLoginFrame();

    }


    public void DesignLoginFrame(){
        setTitle("图书馆管理系统登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setBounds(520,330,400,300);

        //镶板，嵌板 center
        final JPanel panel_2=new JPanel();
        final GridLayout gridLayout=new GridLayout(0,2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(20);
        panel_2.setLayout(gridLayout);

        final JLabel label=new JLabel("用  户  名：");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(label);
        usernameField =new JTextField(20);
        panel_2.add(usernameField);

        final JLabel label1=new JLabel("密     码：");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(label1);
        passwordField=new JPasswordField(20);
        passwordField.setEchoChar('*');  //设置回显字符
        panel_2.add(passwordField);

        // 嵌板 south
        final JPanel panel_1=new JPanel();
        JButton LoginButton=new JButton("登录");
//        LoginButton.addActionListener();
        panel_1.add(LoginButton);
        JButton resetButton=new JButton("重置");
//        resetButton.addActionListener();
        panel_1.add(resetButton);

        final JLabel backgroundPicLabel=new JLabel();
        Image image=new ImageIcon("src/main/resources/主窗体.jpg").getImage();
        Image scaleImage=image.getScaledInstance(400,150,Image.SCALE_DEFAULT);
        //这里寻求简化图片编辑的代码，
        // 另一个办法更为繁杂，因为Image是一个抽象类，所以需用其实现类，
        // 再是需要先获取文件地址，使用File类加载图片，再继续使用getScaledInstance类
        backgroundPicLabel.setIcon(new ImageIcon(scaleImage));

        add(panel_2,BorderLayout.CENTER);
        add(panel_1,BorderLayout.SOUTH);
        add(backgroundPicLabel,BorderLayout.NORTH);

        setVisible(true);

    }

    //这里是用户，不是用户的具体信息，
    private static Operator user;
    public static Operator getUser() {
        return user;
    }
    public static void setUser(Operator user) {
        LoginFrame.user = user;
    }

    //登录按钮
    class BookLoginAction implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            user=Dao.check(usernameField.getText(),passwordField.getPassword());
            if(user.getID()!=-1){
                try{
                    MainFrame mainFrame=new MainFrame();
                    mainFrame.setVisible(true);
                    LoginFrame.this.setVisible(false);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null,"只有管理员才可以登录！");
                usernameField.setText("");
                passwordField.setText("");
            }

        }
    }

    //重置按钮
    private class BookResertAction implements ActionListener{

        @Override
        public void actionPerformed(final ActionEvent e){

            usernameField.setText("");
            passwordField.setText("");

        }
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
