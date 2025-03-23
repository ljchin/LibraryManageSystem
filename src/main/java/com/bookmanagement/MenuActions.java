package main.java.com.bookmanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;

//菜单事件
public class MenuActions {


    /*
    1. 内部类适用于与外部类有紧密关系，仅在外部类中使用的情况。

    2. 这里 PasswordModiAction 是一个内部类，确保，它与MenuActions 紧密相关，
    不会被外部直接访问，也不会被其他类重用，更减少了命名冲突

    3. 就像是一个外文件夹包着一个内文件夹，内文件夹的东西，外文件夹可以用，但是再向外的文件夹就不能用了，

    4. 如果放到类外部原因：
        如果希望被多个类共享，或者复用，就移到类外部（作为独立的类，会更合适），使得可以再其他地方被调用和使用

        如果类的逻辑比较复杂，或者在多个地方都可以用到，就放外面，

     */
    private static class PasswordModiAction extends AbstractAction {

        PasswordModiAction(){
            putValue(Action.NAME,"更改密码");
            putValue(Action.LONG_DESCRIPTION,"修改用户当前密码");
            putValue(Action.SHORT_DESCRIPTION,"更换密码");
        }

        public void actionPerformed(ActionEvent e){

        }

        public static PasswordModiAction MODIFY_PASSWORD;
        static {
            MODIFY_PASSWORD=new PasswordModiAction();
        }

    }

}

