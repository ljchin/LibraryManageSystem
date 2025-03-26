package main.java.com.bookmanagement.model;

import java.sql.Date;

public class BookInfo {
    private String ISBN;
    private int typeID;
    private String bookName;
    private String author;
    private String translator;
    private String publisher;
    private Date date;
    private int price;

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getTypeID() {
        return typeID;
    }
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }


    public String getTranslator() {
        return translator;
    }
    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    //这里的日期时间类，util类中的更为老，但是可以表示 年月日 时分秒 毫秒，
    // sql 继承util 中的Date ，只能年月日，
    // 但两者都不如使用 新出的java.time.* ，有localDate 等，

}
