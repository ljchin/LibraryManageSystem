package main.java.com.bookmanagement.iframe;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import main.java.com.bookmanagement.DAO.Dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class NewBookOrderFrame extends JFrame {

    private JTextField bookISBN;
    private JTextField bookName;
    private ComboBoxModel<String> TypeModel;
    private JComboBox<String> bookType;
    private ComboBoxModel<String> PubModel;
    private JComboBox<String> bookPublisher;
    private JTextField price;

    private JTextField Date;
    private JTextField OrderNum;
    private DefaultComboBoxModel<String> OpeModel;
    private JComboBox<String> Operator;
    private ButtonGroup buttonGroup;
    private JRadioButton yesRadioButton;
    private JRadioButton NoRadioButton;
    private JTextField discount;

    private JButton add;
    private JButton close;



    public NewBookOrderFrame(){
        DesignOrderFrame();
    }

    public void DesignOrderFrame(){
        setTitle("新书订购管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(420,280,600,400);

        JPanel panel=new JPanel(new GridLayout(8,4));
        {
            panel.add(new JLabel("图书信息"));
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(new JLabel("书籍编号："));
            bookISBN=new JTextField(20);
            panel.add(bookISBN);
            panel.add(new JLabel("书籍名称："));
            bookName=new JTextField(20);
            panel.add(bookName);
            panel.add(new JLabel("图书类别："));
            String[] typeStr=new String[]{"计算机","土木","临床医学","护理"};
            TypeModel=new DefaultComboBoxModel<>(typeStr);
            bookType=new JComboBox<>(TypeModel);
            panel.add(bookType);
            panel.add(new JLabel("出版社："));
            String[] array=new String[]{"臭名昭著出版社","胡作非为出版社","X信息出版社","唯我独尊大型出版社"};
            PubModel=new DefaultComboBoxModel<>(array);
            bookPublisher=new JComboBox<>(PubModel);
            panel.add(bookPublisher);
            panel.add(new JLabel("图书价格："));
            price=new JTextField(20);
            panel.add(price);
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(new JLabel("订购信息"));
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(new JLabel("订购日期："));
            Date=new JTextField(20);
            panel.add(Date);
            panel.add(new JLabel("订购数量："));
            OrderNum=new JTextField(20);
            panel.add(OrderNum);
            panel.add(new JLabel("操作员："));
            String[] operatorStr=new String[]{"技工1号","技工2号","技工3号"};
            OpeModel=new DefaultComboBoxModel<>(operatorStr);
            Operator=new JComboBox<>(OpeModel);
            panel.add(Operator);
            panel.add(new JLabel("折扣："));
            price=new JTextField(20);
            panel.add(price);
            panel.add(new JLabel("是否验收："));
            buttonGroup=new ButtonGroup();
            yesRadioButton=new JRadioButton("是",false);
            buttonGroup.add(yesRadioButton);
            NoRadioButton=new JRadioButton("否",true);
            buttonGroup.add(NoRadioButton);
            panel.add(yesRadioButton);
            panel.add(NoRadioButton);

            add(panel,BorderLayout.CENTER);
        }

        {
            JPanel panel1=new JPanel(new GridLayout(1,2));
            add=new JButton("添加");
            close=new JButton("退出");
            add.addActionListener(new AddButtonListener());
            close.addActionListener(new CloseButtonListener());
            panel1.add(add);
            panel1.add(close);

            add(panel1,BorderLayout.SOUTH);
        }

        setVisible(true);
    }

    public class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(bookISBN.getText().isEmpty()||bookName.getText().isEmpty()||
            price.getText().isEmpty()||Date.getText().isEmpty()||OrderNum.getText().isEmpty()||
            discount.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"文本框不可为空");
                return;
            }

            int selectBu;
            if(yesRadioButton.isSelected()){
                selectBu=1;
            }else{
                selectBu=0;
            }
            int i=Dao.addOrderInfo(bookISBN.getText(), new java.sql.Date(System.currentTimeMillis()),Integer.parseInt(OrderNum.getText()),
                    Operator.getSelectedItem().toString(),selectBu,Float.valueOf(discount.getText()));

            if(i==1){
                JOptionPane.showMessageDialog(null,"图书订阅成功");
            }
        }
    }

    public class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }



    public static void main(String[] args){

        SwingUtilities.invokeLater(NewBookOrderFrame::new);
    }

}
