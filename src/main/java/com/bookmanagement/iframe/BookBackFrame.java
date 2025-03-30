package main.java.com.bookmanagement.iframe;

import main.java.com.bookmanagement.DAO.Dao;
import main.java.com.bookmanagement.model.BookBorrow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.time.LocalDate;

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
            expectDays.setText("15");
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
            currentDate.setText(LocalDate.now().toString());
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

    //本方法为输入读者编号后，点击回车 enter 就会搜索JTable 的信息以及底部文本框的信息填充，
    public static void selectInfoBorrow(String str){

        List<BookBorrow> list= Dao.selectBorrowInfo(str);
        for(int i=0;i<list.size();i++){
            BookBorrow bStr=list.get(i);
            String[] add=new String[]{bStr.getBookName(),bStr.getBookIsbn(), String.valueOf(bStr.getTypeID()),
            bStr.getName(), String.valueOf(bStr.getBorrowDate()),String.valueOf(bStr.getBackDate())};
            defaultTableModelBack.addRow(add);
        }
        //刷新表格
        jTableBack=new JTable(defaultTableModelBack);

    }

    public class backListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(BorrowDate.getText().isEmpty()||expectDays.getText().isEmpty()||
            actualDays.getText().isEmpty()||outDays.getText().isEmpty()||
            amountMoney.getText().isEmpty()||currentDate.getText().isEmpty()||
            currentDate.getText().isEmpty()||Operator.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"底部文本框不可为空");
                return;
            }

            //接下来就是添加信息的部分，

        }
    }

    public class closeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();

        }
    }



    //主程序，用以测试窗体，
    public static void main(String[] args){

        //法3
        SwingUtilities.invokeLater(BookBackFrame::new);
    }
}
