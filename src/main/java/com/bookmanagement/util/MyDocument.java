package main.java.com.bookmanagement.util;

import javax.swing.text.PlainDocument;

public class MyDocument extends PlainDocument {
    //本类是为了限制用户输入的字符个数上线，

    int maxLength;

    public MyDocument(){

    }

    public MyDocument(int newMaxLength){
        super();
        maxLength=newMaxLength;
    }

    public void insertString(int offset,String str){//还有一个形参，但找不到定义处

    }
}
