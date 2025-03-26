package main.java.com.bookmanagement.iframe;

import main.java.com.bookmanagement.DAO.Dao;
import main.java.com.bookmanagement.util.MyDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class BookAddFrame extends JFrame {

    private static JTextField ISBN;
    private static JComboBox<String> bookType;
    private static DefaultComboBoxModel<String> bookTypeModel;
    private static JComboBox<String> publisher;
    private static DefaultComboBoxModel<String> publisherModel;
    private static JTextField price;
    private static JTextField bookName;
    private static JTextField author;
    private static JTextField translator;
    private static JFormattedTextField pubDate;

    private static Button buttonAdd;
    private static Button buttonClose;

    private static JLabel backLabel;


    public BookAddFrame(){
        DesignBookAddFrame();
    }

    public void DesignBookAddFrame(){

        setTitle("图书信息添加");
        getContentPane().setLayout(new BorderLayout());
        setBounds(320,180,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        {
            JPanel centerPanel=new JPanel();
            centerPanel.setLayout(new GridLayout(4,4));


            //书上说的模棱两可，自己建造面板排版吧；
            ISBN=new JTextField("请输入13位书号",13);
            ISBN.setDocument(new MyDocument(13));
            centerPanel.add(new JLabel("图书编号ISBN："));
            centerPanel.add(ISBN);

//        bookType=new JComboBox<>();
//        bookTypeModel = (DefaultComboBoxModel) bookType.getModel();
            //写法更新，避免显式的获取，直接使用DefaultComboBoxModel的构造函数
            //组合框（下拉框）装图书类别，
            bookTypeModel=new DefaultComboBoxModel<>();
            bookType=new JComboBox<>(bookTypeModel);

            String sql="select * from tb_bookType";
            ResultSet rt= Dao.executeQuery(sql);
            try{
                while(rt.next()){
                    bookTypeModel.addElement(rt.getString("typeName"));
                }
            }catch(SQLException ee){
                ee.printStackTrace();
            }
            centerPanel.add(new JLabel("类别："));
            centerPanel.add(bookType);

            bookName=new JTextField();
            centerPanel.add(new JLabel("书名："));
            centerPanel.add(bookName);

            author=new JTextField();
            centerPanel.add(new JLabel("作者："));
            centerPanel.add(author);


            //下拉框装图书出版社；
            String[] array=new String[]{"臭名昭著出版社","胡作非为出版社","X信息出版社","唯我独尊大型出版社"};
            publisherModel=new DefaultComboBoxModel<>(array);
            publisher=new JComboBox<>(publisherModel);
            centerPanel.add(new JLabel("出版社："));
            centerPanel.add(publisher);

            translator=new JTextField();
            centerPanel.add(new JLabel("译者："));
            centerPanel.add(translator);

            //日期
            SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
            pubDate = new JFormattedTextField(myfmt.getDateInstance());
            pubDate.setValue(new java.util.Date());
            centerPanel.add(new JLabel("出版日期："));
            centerPanel.add(pubDate);

            price=new JTextField();
            price.setDocument(new MyDocument(5));
            centerPanel.add(new JLabel("价格："));
            centerPanel.add(price);

            add(centerPanel,BorderLayout.CENTER);
        }

        {
            JPanel southPanel=new JPanel();
            southPanel.setLayout(new FlowLayout());

            buttonAdd=new Button("添加");
            buttonAdd.addActionListener(new AddBookActionListener());
            southPanel.add(buttonAdd);

            buttonClose=new Button("退出");
            southPanel.add(buttonClose);
            buttonClose.addActionListener(new CloseBookActionListener());

            add(southPanel,BorderLayout.SOUTH);
        }

        {
         ImageIcon bookAddIcon=new ImageIcon("src/main/resources/bookAdd.jpg");
         backLabel=new JLabel();
         backLabel.setIcon(bookAddIcon);
         backLabel.setPreferredSize(new Dimension(800,400));

         add(backLabel,BorderLayout.NORTH);
        }

        setVisible(true);
    }


    class AddBookActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(ISBN.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"书号文本框不可以为空");
                return;
            }
            if(ISBN.getText().length()!=13){
                JOptionPane.showMessageDialog(null,"书号输入位不可以少于13位");
                return;
            }
            if(bookName.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"书名文本框不可以为空");
                return;
            }
            if(author.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"作者文本框不可为空");
                return;
            }
//            if(pubDate.getText().isEmpty()){
//                JOptionPane.showMessageDialog(null,"出版时间不可为空");
//            }
            if(price.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"单价文本框不可为空");
                return;
            }
            String a=ISBN.getText().trim();
            Object b = bookType.getSelectedItem();
            if(b==null)
                return;
            String b2=b.toString();
            int c=0;         //这里需要转化为ID，！！！！！！！！！还没解决
            String d=bookName.getText().trim();
            String auth=author.getText().trim();
            String f=translator.getText().trim();
            String g= Objects.requireNonNull(publisher.getSelectedItem()).toString();//系统提示的，
            Date h= Date.valueOf(pubDate.getText().trim());
            int i= Integer.parseInt(price.getText().trim());

            int num_i=Dao.InsertBookInfo(a,c,d,auth,f,g,h,i);

            if(num_i==1){
                JOptionPane.showMessageDialog(null,"添加成功");

            }
        }
    }

    class CloseBookActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    //    主程序，用以测试窗体，
    public static void main(String[] args){

        //法3 初始化类
        SwingUtilities.invokeLater(BookAddFrame::new);
    }

}

