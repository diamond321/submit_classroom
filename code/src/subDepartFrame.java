/**
 * Created by xsw on 2017/6/2.
 */
//预定信息界面

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class subDepartFrame extends JFrame implements ActionListener {
    //该窗体具备五个标签，五个文本框，两个按钮
    private JLabel unLbl, idLbl, telLbl,emailLbl,clrLbl,beginLbl,endLbl,durLbl,purLbl;
    private JTextField unField,idField,telField,emailField,clrField,beginField,endField,durField;
    private JButton okBtn, cancelBtn;
    private JTextArea purField;
    private JComboBox endCombox,durCombox;
    private static final long serialVersionUID = 1L;
    public subDepartFrame() {

        // 创建窗体
        setTitle("预定教室");
        setSize(400, 450);
        WindowUtils.displayOnDesktopCenter(this);//窗体居中的方法
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        // 正常关闭窗口
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);//设置窗体大小不可改变
        //setResizable(false);

        unLbl = new JLabel("姓名:");
        idLbl= new JLabel("学号:");
        telLbl = new JLabel("联系方式:");
        emailLbl = new JLabel("邮箱:");
        clrLbl = new JLabel("教室位置:");
        beginLbl = new JLabel("借用日期:");
        endLbl = new JLabel("借用时间:");
        durLbl = new JLabel("借用时长:");
        purLbl = new JLabel("用途:");
        endCombox=new JComboBox();
        endCombox.addItem("8:00-9:30");
        endCombox.addItem("10:00-11:30");
        endCombox.addItem("12:00-13:00");
        endCombox.addItem("13:30-15:00");
        endCombox.addItem("15:30-17:00");
        endCombox.addItem("17:30-21:00");
        endCombox.addItem("21:30-22:30");
        durCombox=new JComboBox();
        durCombox.addItem("1小时");
        durCombox.addItem("1.5小时");

        unField = new JTextField(20);
        idField = new JTextField(20);
        telField = new JTextField(20);
        emailField = new JTextField(20);
        clrField = new JTextField(20);
        beginField = new JTextField(20);
        //endField = new JTextField(20);
        //durField = new JTextField(20);
        purField = new JTextArea(100,50);
        purField.setBorder(BorderFactory.createLineBorder(Color.black));

        okBtn = new JButton("提交");
        cancelBtn = new JButton("取消");

        // 窗体位置设置
        setLayout(null);
        unLbl.setBounds(60, 10, 90, 20);
        unField.setBounds(140, 10, 140, 20);// 90=30+60

        idLbl.setBounds(60, 40, 90, 20); // 40=10+20+10
        idField.setBounds(140, 40, 140, 20);

        telLbl.setBounds(60, 100, 90, 20);
        telField.setBounds(140, 100, 140, 20);// 90=30+60

        emailLbl.setBounds(60, 70, 90, 20); // 40=10+20+10
        emailField.setBounds(140, 70, 140, 20);

        clrLbl.setBounds(60, 130, 90, 20);
        clrField.setBounds(140, 130, 140, 20);// 90=30+60

        beginLbl.setBounds(60, 160, 90, 20);
        beginField.setBounds(140, 160, 140, 20);// 90=30+60

        endLbl.setBounds(60, 190, 90, 20);
        endCombox.setBounds(140, 190, 140, 20);// 90=30+60

        durLbl.setBounds(60, 220, 90, 20);
        durCombox.setBounds(140, 220, 140, 20);// 90=30+60

        purLbl.setBounds(60, 250, 90, 20);
        purField.setBounds(140, 250, 140, 80);// 90=30+60

        okBtn.setBounds(60, 350, 100, 20);
        cancelBtn.setBounds(180, 350, 100, 20);
        //将上述桌面添加进来
        add(unLbl);
        add(unField);
        add(idLbl);
        add(idField);
        add(telLbl);
        add(telField);
        add(emailLbl);
        add(emailField);
        add(clrLbl);
        add(clrField);
        add(beginLbl);
        add(beginField);
        //定义日历控制面板类
        //beginField.setBounds(100, 160, 140, 20);// 90=30+60
        //add(beginField);

        CalendarPanel p = new CalendarPanel(beginField, "yyyy/MM/dd");
        p.initCalendarPanel();

        JLabel l = new JLabel("日历面板");
        p.add(l);
        getContentPane().add(p);
        getContentPane().add(beginField);
        //setSize(500, 400);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        add(endLbl);
        add(endCombox);
        add(durLbl);
        add( durCombox);
        add(purLbl);
        add(purField);
        add(okBtn);
        add(cancelBtn);

        //注册事件监听
        okBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new subDepartFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okBtn) {
            String user_name = unField.getText();
            String user_id = idField.getText();
            String telNum = telField.getText();
            String email = emailField.getText();
            String clr_num = clrField.getText();
            String date_begin = beginField.getText();
            String date_end = (String) endCombox.getSelectedItem();
            String duration =(String)durCombox.getSelectedItem();
            String purpose = purField.getText();
            if (user_id == null || "".equals(user_id.trim())
                    || user_id == null || "".equals(user_id.trim())
                    || user_name == null || "".equals(user_name.trim())
                    || telNum == null || "".equals(telNum.trim())
                    || email == null || "".equals(email.trim())
                    || clr_num == null || "".equals(clr_num.trim())
                    || date_begin == null || "".equals(date_begin.trim())
                    || date_end == null || "".equals(date_end)
                    || duration == null || "".equals(duration.trim())
                    || purpose == null || "".equals(purpose))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            //调用DepartDao业务逻辑处理类来完成增加的操作
            subDepartDao subdepartDao = new subDepartDao();
            //将用户输入的数据封装成一个Depart对象
            subDepart d = new subDepart(user_name, user_id, telNum, email, clr_num,date_begin,date_end,duration,purpose);
            try {
                subdepartDao.save(d);//保存数据
                JOptionPane.showMessageDialog(null, "预定提交成功，等待审核");
                log.logger.debug(""+user_id+"预定了教室"+clr_num+"并等待审核");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else {
            this.dispose();//关闭当前窗口
        }
    }
}
