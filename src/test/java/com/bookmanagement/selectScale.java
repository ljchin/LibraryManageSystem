package test.java.com.bookmanagement;

import java.awt.Dimension;
import java.awt.Toolkit;


public class selectScale {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        System.out.println("Screen Resolution: " + width + "x" + height);

        //Screen Resolution: 1440x960
    }
}
