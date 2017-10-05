package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

/**
 * Created by Zhiyi Zhang on 2017/9/28.
 */
public class mainForm extends JFrame implements ActionListener{
    public static int num;
    private static JFrame frame=new JFrame();
    //创建各组件
    JMenuBar menuBar=new JMenuBar();//菜单条
    JMenu menu0=new JMenu("系统");
    JMenuItem item0=new JMenuItem("退出");//选项
    JMenu menu1=new JMenu("显示");
    JMenuItem item1=new JMenuItem("简体中文");
    JMenuItem item2=new JMenuItem("繁體中文");
    JMenuItem item3=new JMenuItem("English");
    JMenu menu2=new JMenu("帮助");
    JMenuItem item4=new JMenuItem("使用说明");
    JLabel inputLabel=new JLabel("请输入题目数量：");
    JTextField inputField=new JTextField();
    JButton startButton=new JButton("开始做题");

    public mainForm(){
        try{
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            placeComponent();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        frame.setVisible(true);
    }

    public static void main(String args[]){
        mainForm main=new mainForm();
    }

    //窗体内容
    public void placeComponent() throws Exception {
        JPanel panel = (JPanel)getContentPane();
        panel.setLayout(null);
        frame.add(panel);
        //设置界面大小
        frame.setSize(new Dimension(500, 450));
        frame.setTitle("开始界面");
        //设置组件大小
        inputLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 18));
        inputLabel.setBounds(new Rectangle(50, 90, 180, 30));
        inputField.setBounds(new Rectangle(220, 90, 180, 30));
        startButton.setBounds(new Rectangle(160, 200, 160, 30));
        //将组件加入到panel
        frame.setJMenuBar(menuBar);
        this.add(inputLabel);
        this.add(inputField);
        this.add(startButton);
        menuBar.add(menu0);
        menuBar.add(menu1);
        menuBar.add(menu2);
        menu0.add(item0);
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu2.add(item4);
        //添加事件监听
        item0.addActionListener(this);
        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        inputField.addActionListener(this);
        startButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==item0){
            System.exit(0);
        }
        /*
        if(e.getSource()==item2){          //切换到繁体界面
            mainForm2 f2=new mainForm2();
            frame.remove(this.getRootPane());
            frame.dispose();
            f2.setVisible(true);
        }
        if(e.getSource()==item3){           //切换到英文界面
            mainForm3 f3=new mainForm3();
            frame.remove(this.getRootPane());
            frame.dispose();
            f3.setVisible(true);
        }
        */
        if(e.getSource()==item4){
            JOptionPane.showMessageDialog(null,"系统-->退出\n显示-->多语言切换\n在主界面输入你想要做的题目数量，点击开始按钮开始做题");
        }
        if(e.getSource()==startButton){         //切换到题目界面
            if(inputField.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"请输入数字！","出错啦",JOptionPane.ERROR_MESSAGE);
            }
            else{
                num=Integer.parseInt(inputField.getText());
                calculateForm cf = null;
                try {
                    cf = new calculateForm();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                //移除原有内容
                frame.remove(this.getContentPane());
                frame.setVisible(false);
                cf.setVisible(true);
            }
        }
    }

}
