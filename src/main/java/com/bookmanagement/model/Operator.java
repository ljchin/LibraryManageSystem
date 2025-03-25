package main.java.com.bookmanagement.model;

public class Operator {
    private int ID=-1;
    private String name;
    private int grade;
    private String password;

    public void setID(int a){
        this.ID=a;
    }
    public int getID(){
        return this.ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
