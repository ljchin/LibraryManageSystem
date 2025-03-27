package main.java.com.bookmanagement.iframe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookBackFrame extends JFrame {

    private static DefaultTableModel defaultTableModelBack;
    private static JTable jTableBack;

    private static JTextField readerID;

    private static JTextField BorrowDate;
    private static JTextField expectDays;
    private static JTextField actualDays;
    private static JTextField outDays;
    private static JTextField amountMoney;

    private static JTextField currentDate;
    private static JTextField Operator;
    private static JButton backButton;
    private static JButton closeButton;

    public BookBackFrame(){
        DesignBookBackFrame();
    }

    public void DesignBookBackFrame(){
        setTitle("图书归还管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,180,800,600);

        {//上部，
            JPanel panel1=new JPanel();
            JPanel panel2=new JPanel();

            panel2.setLayout(new GridLayout(1,2));
            panel2.add(new JLabel("读者编号："));
            readerID=new JTextField(20);
            panel2.add(readerID);

            panel1.add(panel2,BorderLayout.NORTH);

            String[] tableStr=new String[]{"图书名称","图书ISBN","图书类别","读者姓名","读者ID","借书时间","归还时间"};
            defaultTableModelBack=new DefaultTableModel(tableStr,0);
            jTableBack=new JTable(defaultTableModelBack);
            JScrollPane jScrollPane=new JScrollPane(jTableBack);
//            jScrollPane.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
            panel1.add(jScrollPane,BorderLayout.CENTER);
            add(panel1,BorderLayout.NORTH);
        }

        JPanel jPanelCenter=new JPanel(new GridLayout(1,2));

        {//中下左
            JPanel jPanel3=new JPanel();
            jPanel3.setLayout(new GridLayout(6,2));
            jPanel3.add(new JLabel("罚款信息"));
            jPanel3.add(new JLabel());
            jPanel3.add(new JLabel("借书日期："));
            BorrowDate=new JTextField(20);
            jPanel3.add(BorrowDate);
            jPanel3.add(new JLabel("规定天数："));
            expectDays=new JTextField(20);
            jPanel3.add(expectDays);
            jPanel3.add(new JLabel("实际天数："));
            actualDays=new JTextField(20);
            jPanel3.add(actualDays);
            jPanel3.add(new JLabel("超出天数："));
            outDays=new JTextField(20);
            jPanel3.add(outDays);
            jPanel3.add(new JLabel("罚款金额："));
            amountMoney=new JTextField(20);
            jPanel3.add(amountMoney);

            jPanelCenter.add(jPanel3);
        }

        {
            JPanel jPanel4=new JPanel(new GridLayout(4,2));
            jPanel4.add(new JLabel("系统信息"));
            jPanel4.add(new JLabel());
            jPanel4.add(new JLabel("当前时间："));
            currentDate=new JTextField(20);
            jPanel4.add(currentDate);
            jPanel4.add(new JLabel("操作员："));
            Operator=new JTextField(20);
            jPanel4.add(Operator);
            backButton=new JButton("图书归还");
            closeButton=new JButton("退出");
            jPanel4.add(backButton);
            jPanel4.add(closeButton);


            jPanelCenter.add(jPanel4);
        }

        add(jPanelCenter,BorderLayout.CENTER);

        setVisible(true);
    }


    //主程序，用以测试窗体，
    public static void main(String[] args){

        //法3
        SwingUtilities.invokeLater(BookBackFrame::new);
    }
}
