package gui;

import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

/**
 * Created by Zhiyi Zhang on 2017/9/28.
 */
public class mainForm extends JFrame implements ActionListener{
    public static int num;
    //初始化语言为简体中文
    public static final int SIMPLIFIED_CHINESE = 1;
    public static final int TRADITIONAL_CHINESE = 2;
    public static final int ENGLISH= 3;
    public static  int languageEdition=SIMPLIFIED_CHINESE;
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WebLookAndFeel.globalControlFont  = new FontUIResource("微软雅黑",0, 14);
                WebLookAndFeel.globalMenuFont = new FontUIResource("微软雅黑",0,13);
                WebLookAndFeel.install();
                mainForm main=new mainForm();
            }
        });
    }

    //窗体内容
    public void placeComponent() throws Exception {
        JPanel panel = (JPanel)getContentPane();
        panel.setLayout(null);
        frame.add(panel);
        //设置界面大小
        frame.setSize(new Dimension(700, 450));
        frame.setTitle("开始界面");
        //设置组件大小
        inputLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 18));
        inputLabel.setBounds(new Rectangle(200, 60, 400, 30));
        inputField.setBounds(new Rectangle(200, 120, 180, 30));
        startButton.setBounds(new Rectangle(210, 200, 160, 30));
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
        if (e.getSource()==item1){
            languageEdition=SIMPLIFIED_CHINESE;
            frame.setTitle("开始界面");
            menu0.setText("系统");
            item0.setText("退出");
            menu1.setText("显示");
            menu2.setText("帮助");
            item4.setText("使用说明");
            inputLabel.setText("请输入题目数量：");
            startButton.setText("开始做题");
        }

        if(e.getSource()==item2){          //切换到繁体界面
            languageEdition=TRADITIONAL_CHINESE;
            frame.setTitle("開始界面");
            menu0.setText("系統");
            item0.setText("退出");
            menu1.setText("顯示");
            menu2.setText("幫助");
            item4.setText("使用說明");
            inputLabel.setText("請輸入題目數量：");
            startButton.setText("開始做題");

        }
        if(e.getSource()==item3){           //切换到英文界面
            languageEdition=ENGLISH;
            frame.setTitle("Start Interface");
            menu0.setText("system");
            item0.setText("quit");
            menu1.setText("show");
            menu2.setText("help");
            item4.setText("using instruction");
            inputLabel.setText("Please input the number of questions:");
            startButton.setText("start to do it");

        }

        if(e.getSource()==item4){
            if (languageEdition==TRADITIONAL_CHINESE){
                JOptionPane.showMessageDialog(null,"在主界面輸入妳想要做的題目數量，點擊開始按鈕開始做題\n\n點擊計時則進入自測模式\n每道題限時50s");
            }
            else if (languageEdition==ENGLISH){
                JOptionPane.showMessageDialog(null,"please input the number of Questions and click the start button\n\nenter self-test mode when start Timer\ntime limited: 50s per question");
            }
            else {
                JOptionPane.showMessageDialog(null,"在主界面输入你想要做的题目数量，点击开始按钮开始做题\n\n点击计时则进入自测模式\n每道题限时50s");
            }

        }
        if(e.getSource()==startButton){         //切换到题目界面
            if(inputField.getText().equals("")) {
                if (languageEdition==TRADITIONAL_CHINESE){
                    JOptionPane.showMessageDialog(null,"請輸入數字！","出錯啦",JOptionPane.ERROR_MESSAGE);
                }
                else if (languageEdition==ENGLISH){
                    JOptionPane.showMessageDialog(null,"Please input the number！","It's Wrong",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"请输入数字！","出错啦",JOptionPane.ERROR_MESSAGE);
                }
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
