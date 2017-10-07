package gui;

import calculate.NewCalculate;
import calculate.Simplify;
import create.CreateExp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.NumberFormat;

/**
 * Created by Zhiyi Zhang on 2017/9/28.
 */
public class calculateForm extends JFrame implements ActionListener {
    private static JFrame frame=new JFrame();
    private String[] ques=new String[mainForm.num];
    private String[] ans=new String[mainForm.num];
    private JLabel[] quesLabel=new JLabel[mainForm.num];   //题目域
    private JLabel[] checkLabel=new JLabel[mainForm.num];  //正误判断域
    private JTextField[] ansField=new JTextField[mainForm.num];   //答案输入域
    private JLabel[] ansLabel=new JLabel[mainForm.num];           //答案显示域
    private JPanel[] quesPanel=new JPanel[mainForm.num];
    private int rightCount,wrongCount=0;
    private boolean isRun=false;
    private BufferedWriter writer;
    private BufferedReader reader;
    private String rRead;
    private String wRead;
    private MyRunable myTimeRunable=new MyRunable();
    //创建各组件
    JLabel label0=new JLabel("题目:");
    JLabel label1=new JLabel("你的答案:");
    JLabel label2=new JLabel("正确答案:");
    JLabel label3=new JLabel("历史记录:");
    JLabel rLabel=new JLabel("正确量:0");
    JLabel wLabel=new JLabel("错误量:0");
    JLabel timeLabel=new JLabel("00:00:00");
    JButton timeButton=new JButton("开始计时");
    JButton submitButton=new JButton("提交答案");

    public void placeComponent() throws Exception{
        frame.setSize(new Dimension(700,700));
        frame.setTitle("四则运算界面");
        JPanel panel = (JPanel)getContentPane();
        panel.setLayout(null);
        frame.add(panel);
        //设置组件位置及大小
        label0.setBounds(new Rectangle(50,40,30,25));
        label1.setBounds(new Rectangle(320,40,60,25));
        label2.setBounds(new Rectangle(500,40,60,25));
        label3.setBounds(new Rectangle(50,620,60,25));
        rLabel.setBounds(new Rectangle(140,620,60,25));
        wLabel.setBounds(new Rectangle(220, 620, 60, 25));
        timeLabel.setBounds(new Rectangle(500,550,120,25));
        timeLabel.setFont(new java.awt.Font("Consolas",Font.BOLD,18));
        timeButton.setBounds(new Rectangle(60,550,100,30));
        submitButton.setBounds(new Rectangle(280,550,100,30));
        //添加组件到面板
        frame.add(panel);
        panel.add(label0);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(rLabel);
        panel.add(wLabel);
        panel.add(timeLabel);
        panel.add(timeButton);
        panel.add(submitButton);
    }

    public calculateForm() throws URISyntaxException {
        try{
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            placeComponent();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        ReadHistory();
        Display();
    }

    public void Display(){
        //开启计时线程
        new Thread(myTimeRunable).start();
        //显示题目及作答区域
        JPanel qaPanel=new JPanel();
        qaPanel.setLayout(new GridLayout(mainForm.num,1,4,0));  //行列数及间距
        for(int i=0;i<mainForm.num;i++){
            //每一条题目作为一个panel
            quesPanel[i]=new JPanel();
            quesPanel[i].setLayout(null);
            //题目区域
            do {
                ques[i] = CreateExp.exp((int) ((Math.random() * 100) % 4 + 3));
                ans[i] = Simplify.gcd(NewCalculate.newcalculate(ques[i]));
            }while(ans[i].contains("-"));
            quesLabel[i]=new JLabel(i+":"+ques[i],JLabel.LEFT);
            quesLabel[i].setFont(new Font("Consolas",0,14));
            quesPanel[i].add(quesLabel[i]);
            quesLabel[i].setBounds(0,0,300,25);
            //作答区域
            ansField[i]=new JTextField(10);
            ansField[i].setFont(new Font("Consolas",0,14));
            quesPanel[i].add(ansField[i]);
            ansField[i].setBounds(270,0,70,25);
            //判断区域
            checkLabel[i]=new JLabel("",JLabel.CENTER);
            quesPanel[i].add(checkLabel[i]);
            checkLabel[i].setBounds(350,0,60,25);
            //正确答案显示域
            ansLabel[i]=new JLabel("",JLabel.LEFT);
            ansLabel[i].setFont(new java.awt.Font("Consolas",0,14));
            quesPanel[i].add(ansLabel[i]);
            ansLabel[i].setBounds(450,0,100,25);

            qaPanel.add(quesPanel[i]);

        }
        //添加滚动面板
        qaPanel.setPreferredSize(new Dimension(600,mainForm.num*(25+4))); //设置面板宽高
        JScrollPane sp=new JScrollPane();
        sp.setViewportView(qaPanel);
        qaPanel.revalidate();
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBounds(50, 80, 550, 420);     //设置滚动面板大小及位置
        this.add(sp);
        frame.setVisible(true);

        //添加按钮监听事件
        //开始计时
        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRun=true;
            }
        });
        //提交答案
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRun=false;
                for(int i=0;i<mainForm.num;i++){
                    ans[i] = Simplify.gcd(NewCalculate.newcalculate(ques[i]));
                    if (ansField[i].getText().equals("")) {      //题目未答完
                        JOptionPane.showMessageDialog(null, "请输入第" + i + "题答案", "出错啦", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    else{     //显示判断结果
                        if(ansField[i].getText().equals(ans[i])) {
                                checkLabel[i].setText("正确");
                                ansLabel[i].setText(":)");
                                rightCount++;
                            }
                        else{
                                checkLabel[i].setText("错误");
                                ansLabel[i].setText(ans[i]);
                                wrongCount++;
                            }
                    }
                }
                int ratio=rightCount*100/(rightCount+wrongCount);
                String[] a=timeLabel.getText().split(":");
                int cost=Integer.parseInt(a[1])*60+Integer.parseInt(a[2]);
                //消息框显示最终用时及正确率
                JOptionPane.showMessageDialog(null,"用时:"+cost+"秒\n"+"正确率:"+ratio+"%");
                //写入对错数
                rightCount=rightCount+Integer.parseInt(rRead);
                wrongCount=wrongCount+Integer.parseInt(wRead);
                Integer right=new Integer(rightCount);
                Integer wrong=new Integer(wrongCount);
                try {
                    URL url=calculateForm.class.getResource("history.txt");
                    File file2 = new File(url.toURI());
                    writer = new BufferedWriter(new FileWriter(file2));
                    writer.write(right.toString());
                    writer.newLine();
                    writer.write(wrong.toString());
                    writer.close();
                }catch (IOException ie){
                    ie.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /*------------------------计时功能-----------------------*/
    private class MyRunable implements Runnable{
        private int hour = 0;
        private int min = 0;
        private int sec = 0;
        private NumberFormat format = NumberFormat.getInstance();  //将数字格式化处理
        private String getTime(){
            ++sec;
            if(sec == 60) {
                ++min;
                sec = 0;
            }

            if(min == 60) {
                ++hour;
                min = 0;
            }
            return showTime();
        }

        private String showTime(){
            //时间显示形式
            return format.format(hour)+":"+format.format(min)+":"+format.format(sec);
        }

        @Override
        public void run() {
            format.setMinimumIntegerDigits(2);
            format.setGroupingUsed(false);
            while(true) {
                if(rootPaneCheckingEnabled) {
                    if(isRun) {   //若点击计时按钮
                        getTime();
                        timeLabel.setText(showTime());
                    }
                }
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                }
            }
        }
    }

    /*--------------------历史读取功能-----------------*/
    public void ReadHistory() throws URISyntaxException {
        try{
            //读取对错数目
            URL url=calculateForm.class.getResource("history.txt");
            File file1=new File(url.toURI());
            reader=new BufferedReader(new FileReader(file1));
            rRead=reader.readLine();
            wRead=reader.readLine();
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        if(rRead==null&&wRead==null){
            rRead="0";
            wRead="0";
        }
        rLabel.setText("正确量:"+rRead);
        wLabel.setText("错误量"+wRead);
    }

    public void actionPerformed(ActionEvent e){

    }

}
