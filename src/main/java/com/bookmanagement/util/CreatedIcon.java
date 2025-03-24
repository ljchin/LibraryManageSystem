package main.java.com.bookmanagement.util;

import main.java.com.bookmanagement.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

//创建图标
public class CreatedIcon {
    public static ImageIcon add(String ImageName){

        //CreatedIcon.class 是创建一个class 对象，
        // 后面的getResource 是从根目录查找 图片URL，
        // 但只有包含 " / "才会从根目录查找，如果没有 " / "，则会从调用 getResource() 方法的类的路径开始查找。
        URL IconUrl=CreatedIcon.class.getResource("/main/resources/"+ImageName);

        ImageIcon icon= null;
        if (IconUrl != null) {
            icon = new ImageIcon(IconUrl);
        }

        //有时加载的图标可能太大或太小，你可以调整图标的大小。
        // 通过 ImageIcon.getImage() 获取 Image 对象，然后使用 getScaledInstance() 方法来调整大小。
        Image image=icon.getImage();
        Image scaledImage=image.getScaledInstance(30,30,Image.SCALE_DEFAULT);
        return new ImageIcon(scaledImage);

    }

}


