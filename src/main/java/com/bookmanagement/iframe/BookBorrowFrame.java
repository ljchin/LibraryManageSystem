package main.java.com.bookmanagement.iframe;

import main.java.com.bookmanagement.DAO.Dao;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private static JTextField nowDate;
    private static JComboBox<String> comboBox;
    private static DefaultComboBoxModel<String> ComboModel;
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
//            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
            nowDate=new JTextField(20);
            nowDate.setText(LocalDate.now().toString());
            panelSouth.add(nowDate);//时间
            BorrowBookButton=new JButton("借出当前书籍");
            panelSouth.add(BorrowBookButton);

            panelSouth.add(new JLabel("操 作 员："));
            String[] operator=new String[]{"操作员1","王牌操作员2","神级牛马"};
            ComboModel=new DefaultComboBoxModel<>(operator);
            comboBox=new JComboBox<>(ComboModel);
            panelSouth.add(comboBox);
            ClearNoteButton=new JButton("清除所有记录");
            panelSouth.add(ClearNoteButton);

            add(panelSouth,BorderLayout.SOUTH);
        }


        setVisible(true);
    }


    class ISBNListenerlostFous extends KeyAdapter{
        public void KeyTyped(KeyEvent e){
            if(e.getKeyChar()=='\n'){
                String ISBNs=readerID.getText().trim();
                //这里接下来就是查找数据库中是否有这个人，
                //如果没有，目前此地 缺少查找的方法
//                if(){
//                    JOptionPane.showMessageDialog(null,
//                            "此读者没有编号注册，请检查是否输入错误。");
//                }
            }
        }
    }


    class BookISBNListenerlostFocus extends KeyAdapter{
        public void KeyTyped(KeyEvent e){
            if(e.getKeyChar()=='\n'){
                String bookISBNStr=BookISBN.getText().trim();
                //接下来就是相同的，查询是否有没有这本书。
                //如果没有，目前此地 缺少查找的方法
//                if(){
//                    JOptionPane.showMessageDialog(null,
//                            "本图书馆没有此图书，请检查是否输入错误。");
            }
        }
    }

    //将生成借书信息添加到JTable中
    public final void add(){
        String[] str=new String[4];
        str[0]=BookISBN.getText().trim();
        SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
        str[1]=myfmt.format(new Date());
        LocalDate today=LocalDate.now();
        LocalDate future=today.plusDays(15);
        str[2]= String.valueOf(future);
        str[3]=readerID.getText().trim();
        defaultTableModel.addRow(str);

    }


    class BookBorrowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(readerID.getText().isEmpty()||readerName.getText().isEmpty()||
            MaxBorrowBookNum.getText().isEmpty()||GuaranteeMoney.getText().isEmpty()||
            BookISBN.getText().isEmpty()||BookName.getText().isEmpty()||
            BookType.getText().isEmpty()||BookType.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"文本框不可为空");
                return;
            }
            int i=Dao.InsertBookBorrow(BookISBN.getText(),readerID.getText(),Integer.parseInt((comboBox.getSelectedItem()).toString()) ,
                    LocalDate.now(),LocalDate.now().plusDays(15));

            if(i==1){
                JOptionPane.showMessageDialog(null,"图书借阅完成");
                dispose();
            }
        }
    }




    //主程序，用以测试窗体，
    public static void main(String[] args){

        //法3
        SwingUtilities.invokeLater(BookBorrowFrame::new);
    }
    

}
