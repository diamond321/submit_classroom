import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//implements ActionListener
/**
 * Created by xsw on 2017/6/4.
 */
public class modify_ind_info extends JFrame implements ActionListener
{
    private JLabel unameLbl, idLbl,pwdLbl,genderLbl, telLbl,emailLbl;
    private JTextField unameField,idField,pwdField,genderField,telField,emailField;
    private JButton okBtn, cancelBtn;
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    private static final long serialVersionUID = 1L;
    public modify_ind_info(String account) {

        // 创建窗体
        setTitle("个人信息管理");
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
        try{

        String sql = "select * from user_info where id='"+account+"'";
        Connection conn =dbUtils.getConnection();//获得数据库连接对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs =pstmt.executeQuery();
        rs.next();
        unameField = new JTextField(rs.getString("uname"),20);
        idField = new JTextField(rs.getString("id"),20);
        pwdField = new JTextField(rs.getString("pwd"),20);
        genderField = new JTextField(rs.getString("gender"),20);
        telField = new JTextField(rs.getString("telNum"),20);
        emailField = new JTextField(rs.getString("email"),20);
        } catch(Exception e){

        }
        okBtn = new JButton("修改");
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

        //获取信息
        //unameField.value=
    }

    public static void main(String[] args){
        //new modify_ind_info();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okBtn) {
            //先获取信息
            String uname = unameField.getText();
            String id = idField.getText();
            String pwd = pwdField.getText();
            String gender = genderField.getText();
            String telNum = telField.getText();
            String email = emailField.getText();
            if (uname == null || "".equals(uname.trim())
                    || id == null || "".equals(id.trim())
                    || pwd == null || "".equals(pwd.trim())
                    || gender == null || "".equals(gender.trim())
                    || telNum == null || "".equals(telNum.trim())
                    || email == null || "".equals(email))
            {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            //调用DepartDao业务逻辑处理类来完成增加的操作
            adduserDepartDao adduserdepartDao = new adduserDepartDao();
            //String sql="delete from [user_info] where id="+idField.getText()+";";
            //将用户输入的数据封装成一个Depart对象
            adduserDepart d = new adduserDepart(uname,id,pwd,gender,telNum,email);
            try {
                adduserdepartDao.modify(d);
                JOptionPane.showMessageDialog(null, "用户信息修改成功！");
                log.logger.debug(""+id+"修改了信息");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "添加时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else {
            this.dispose();//关闭当前窗口
        }
    }
}
