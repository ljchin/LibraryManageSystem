package main.java.com.bookmanagement.iframe;

import main.java.com.bookmanagement.DAO.Dao;
import main.java.com.bookmanagement.model.BookInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static main.java.com.bookmanagement.DAO.Dao.selectBookInfo;

public class BookModiAndDeleteFrame extends JFrame {

    private static JTable table;

    private static JTextField ISBN;
    private static JTextField bookName;
    private static JTextField author;
    private static JTextField publish;
    private static JTextField translator;
    private static JTextField price;

    public BookModiAndDeleteFrame(){
        DesignModiAndDeleteFrame();

    }

    public void DesignModiAndDeleteFrame(){
        setTitle("图书信息修改");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(320,180,800,600);
        setLayout(new BorderLayout());

        //修改
        JButton ModiButton=new JButton("修改");
        ModiButton.addActionListener(new BookModiActionListener());
        JButton CloseButton=new JButton("退出");
        CloseButton.addActionListener(new CloseModiActionListener());

        //顶部背景图
        JLabel backModiAndDeletePicture=new JLabel();
        ImageIcon backIcon=new ImageIcon("");//这里未设置图片，
        backModiAndDeletePicture.setPreferredSize(new Dimension());
        backModiAndDeletePicture.setIcon(backIcon);

        {//center perform
            //            Object[][] bookInfoResult=
            //书上使用的是两个方法，一个从sql中查找，另一个，在插到一个object[][]中,方法也太过时，
            // 直接融合一个，以下语句转移到Dao类中的selectBookInfo方法
//            String[] columnNames=new String[]{"图书编号","图书类别","图书名称","作者","译者","出版商","出版日期","价格"};
//            DefaultTableModel defaultTableModel=new DefaultTableModel(columnNames,0);
//            JTable table=new JTable(defaultTableModel);

            JPanel jPanelCenter=new JPanel();

            table=selectBookInfo();
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            JScrollPane jScrollPane=new JScrollPane(table);
            jPanelCenter.add(jScrollPane,BorderLayout.CENTER);

            //中下
            JPanel jPanelCenterSouth=new JPanel();
            jPanelCenterSouth.setLayout(new GridLayout(6,3));

            jPanelCenterSouth.add(new JLabel("书号："));
            ISBN=new JTextField(20);
            jPanelCenterSouth.add(ISBN);
            jPanelCenterSouth.add(new JLabel("类别："));
            String[] typeBooks=new String[]{"计算机","临床","土木","电气"};
            JComboBox<String> typeComboBox=new JComboBox<>(typeBooks);
            jPanelCenterSouth.add(typeComboBox);
            jPanelCenterSouth.add(new JLabel("书名："));
            bookName=new JTextField(20);
            jPanelCenterSouth.add(bookName);
            jPanelCenterSouth.add(new JLabel("作者："));
            author=new JTextField(20);
            jPanelCenterSouth.add(author);
            jPanelCenterSouth.add(new JLabel("出版社："));
            publish=new JTextField(20);
            jPanelCenterSouth.add(publish);
            jPanelCenterSouth.add(new JLabel("译者："));
            translator=new JTextField(20);
            jPanelCenterSouth.add(translator);

            SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
            JFormattedTextField date=new JFormattedTextField(myfmt.getDateFormatSymbols());
            date.setValue(new java.util.Date());
            jPanelCenterSouth.add(new JLabel("出版日期"));
            jPanelCenterSouth.add(date);
            jPanelCenterSouth.add(new JLabel("单价："));
            price=new JTextField(20);
            jPanelCenterSouth.add(price);

            jPanelCenter.add(jPanelCenterSouth,BorderLayout.SOUTH);
            add(jPanelCenter,BorderLayout.CENTER);
        }

    }

    //鼠标监听事件，这个类应该是点击对应行后，相应信息会到文本框中，并不是修改按钮，
    class TableActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selRow=table.getSelectedRow();

            String ISBN2=table.getValueAt(selRow,0).toString().trim();
            int typeID2;
            String bookName2=table.getValueAt(selRow,2).toString().trim();
            String author2=table.getValueAt(selRow,3).toString().trim();
            String translator2=table.getValueAt(selRow,4).toString().trim();
            String publisher2=table.getValueAt(selRow,5).toString().trim();
            Date date2;
            String price2=table.getValueAt(selRow,7).toString().trim();


            ISBN.setText(ISBN2);
            bookName.setText(bookName2);
            author.setText(author2);
            translator.setText(translator2);
            publish.setText(publisher2);
            price.setText(price2);


        }
    }

    class BookModiActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ISBN.getText().isEmpty()||bookName.getText().isEmpty()||
            author.getText().isEmpty()||publish.getText().isEmpty()||
            translator.getText().isEmpty()||price.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"文本框不可以为空");
                return;
            }

            int i= Dao.UpdateBook();
            if(i==1){
                JOptionPane.showMessageDialog(null,"修改成功");
                //这里接下去要继续写刷新的指令，将所示的表格刷新，
                // 但是自认为也可以设置一个刷新按钮刷新表格


            }
        }
    }

    class CloseModiActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    //主程序，用以测试窗体，
    public static void main(String[] args){

        //法3
        SwingUtilities.invokeLater(BookModiAndDeleteFrame::new);
    }




}
