import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xsw on 2017/6/3.
 * 面向用户的注册
 */
public class regDepartFrame extends JFrame implements ActionListener {
    //该窗体具备五个标签，五个文本框，两个按钮
    private JLabel unameLbl, idLbl,pwdLbl,genderLbl, telLbl,emailLbl;
    private JTextField unameField,idField,pwdField,genderField,telField,emailField;
    private JButton okBtn, cancelBtn;
    private static final long serialVersionUID = 1L;
    public regDepartFrame() {

        // 创建窗体
        setTitle("用户注册");
        setSize(300, 280);
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

        unameLbl = new JLabel("姓名:");
        idLbl= new JLabel("学号:");
        genderLbl = new JLabel("性别:");
        pwdLbl = new JLabel("密码:");
        telLbl = new JLabel("联系方式:");
        emailLbl = new JLabel("邮箱:");

        unameField = new JTextField(20);
        idField = new JTextField(20);
        pwdField = new JTextField(20);
        genderField = new JTextField(20);
        telField = new JTextField(20);
        emailField = new JTextField(20);

        okBtn = new JButton("提交");
        cancelBtn = new JButton("取消");

        // 窗体位置设置
        setLayout(null);
        unameLbl.setBounds(60, 10, 90, 20);
        unameField.setBounds(140, 10, 120, 20);// 90=30+60

        idLbl.setBounds(60, 40, 90, 20); // 40=10+20+10
        idField.setBounds(140, 40, 120, 20);

        pwdLbl.setBounds(60, 100, 90, 20);
        pwdField.setBounds(140, 100, 120, 20);// 90=30+60

        genderLbl.setBounds(60, 70, 90, 20); // 40=10+20+10
        genderField.setBounds(140, 70, 120, 20);

        telLbl.setBounds(60, 130, 90, 20);
        telField.setBounds(140, 130, 120, 20);// 90=30+60

        emailLbl.setBounds(60, 160, 90, 20);
        emailField.setBounds(140, 160, 120, 20);// 90=30+60

        okBtn.setBounds(60, 200, 80, 20);
        cancelBtn.setBounds(180, 200, 80, 20);
        //将上述桌面添加进来
        add(unameLbl);
        add(unameField);
        add(idLbl);
        add(idField);
        add(pwdLbl);
        add(pwdField);
        add(genderLbl);
        add(genderField);
        add(telLbl);
        add(telField);
        add(emailLbl);
        add(emailField);
        add(okBtn);
        add(cancelBtn);

        //注册事件监听
        okBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new regDepartFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okBtn) {
            String con_id = idField.getText();
            String con_uname = unameField.getText();
            String con_pwd = pwdField.getText();
            String con_gender = genderField.getText();
            String con_telNum = telField.getText();
            String con_email = emailField.getText();
            if (con_uname == null || "".equals(con_uname.trim())
                    || con_id == null || "".equals(con_id.trim())
                    || con_pwd == null || "".equals(con_pwd.trim())
                    || con_gender == null || "".equals(con_gender.trim())
                    || con_telNum == null || "".equals(con_telNum.trim())
                    || con_email == null || "".equals(con_email))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            //调用DepartDao业务逻辑处理类来完成增加的操作
            regDepartDao regdepartDao = new regDepartDao();
            //将用户输入的数据封装成一个Depart对象
            regDepart d = new regDepart(con_uname, con_id, con_pwd,con_gender,con_telNum, con_email);
            try {
                regdepartDao.save(d);//保存数据
                JOptionPane.showMessageDialog(null, "注册提交成功，等待审核");
                log.logger.debug(""+con_uname+"注册成功，等待审核");
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
