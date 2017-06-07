import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by xsw on 2017/6/5.
 */
public class con_user extends JFrame implements ActionListener {
    //该窗体具备一个表和两个按钮
    private JButton modifyBtn, delBtn;
    private JLabel unameLbl, idLbl,pwdLbl,genderLbl, telLbl,emailLbl;
    private JTextField unameField,idField,pwdField,genderField,telField,emailField;
    private JTable table;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    regDepartDao regdepartDao;
    List<regDepart> dList;
    int num;
    Object[][]data;
    ad_modify.TableListener a;

    public con_user() {

        // 创建窗体
        setTitle("审核注册信息");
        setSize(1100, 400);
        WindowUtils.displayOnDesktopCenter(this);//窗体居中的方法
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) (width - this.getWidth()) / 2;
        int y = (int) (height - this.getHeight()) / 2;
        setLocation(x, y);
        // 正常关闭窗口
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);//设置窗体大小不可改变
        //setResizable(false);

        modifyBtn = new JButton("通过");
        delBtn = new JButton("驳回");
        setLayout(null);
        modifyBtn.setBounds(100, 300, 100, 20);
        delBtn.setBounds(300, 300, 100, 20);
        add(modifyBtn);
        add(delBtn);

        //初始化一个二维数组
        String columnsName[]={"姓名","ID","密码","性别","联系方式","邮箱"};
        try{
            regdepartDao=new regDepartDao();
            //调用departBao对象的findAll方法返回教室信息列表
            dList = regdepartDao.findAll();
            //将list集合解析为JTable显示的数据模型
            num = dList.size();
            data=new Object[num][6];
            int index=0;
            for(regDepart regdepart:dList){
                data[index][0]=regdepart.getUname();
                data[index][1]=regdepart.getId();
                data[index][2]=regdepart.getPwd();
                data[index][3]=regdepart.getGender();
                data[index][4]=regdepart.getTelNum();
                data[index][5]=regdepart.getEmail();
                index++;
            }
            table.setModel(new DefaultTableModel(data,columnsName));
        } catch (Exception ec){
            //JOptionPane.showMessageDialog(null,"查询时出现异常。异常原因为："+ec.getMessage());
            ec.printStackTrace();
        }
        table = new JTable(data,columnsName);
        TableListener a =new TableListener();
        table.addMouseListener(a);
        jScrollPane2.setBounds(20, 50, 800, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);

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

        unameLbl.setBounds(850, 50, 90, 20);
        unameField.setBounds(920, 50, 150, 20);// 90=30+60

        idLbl.setBounds(850, 80, 90, 20); // 40=10+20+10
        idField.setBounds(920, 80, 150, 20);

        genderLbl.setBounds(850, 140, 90, 20);
        genderField.setBounds(920, 140, 150, 20);// 90=30+60

        pwdLbl.setBounds(850, 110, 90, 20); // 40=10+20+10
        pwdField.setBounds(920, 110, 150, 20);

        telLbl.setBounds(850, 170, 90, 20);
        telField.setBounds(920, 170, 150, 20);// 90=30+60

        emailLbl.setBounds(850, 200, 90, 20);
        emailField.setBounds(920, 200, 150, 20);// 90=30+60
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

        //注册事件监听
        modifyBtn.addActionListener(this);
        delBtn.addActionListener(this);
        setVisible(true);
    }
    public static void main(String[] args) {
        new con_user();
    }

    class TableListener extends MouseAdapter {
        String uname, id, pwd, gender, telNum, email;
        public void mouseClicked(final MouseEvent e) {
            int selRow = table.getSelectedRow();
            uname = table.getValueAt(selRow, 0).toString().trim();
            id = table.getValueAt(selRow, 1).toString().trim();
            pwd = table.getValueAt(selRow, 2).toString().trim();
            gender = table.getValueAt(selRow, 3).toString().trim();
            telNum = table.getValueAt(selRow, 4).toString().trim();
            email = table.getValueAt(selRow, 5).toString().trim();
            unameField.setText(uname);
            idField.setText(id);
            pwdField.setText(pwd);
            genderField.setText(gender);
            telField.setText(telNum);
            emailField.setText(email);
        }
    }
    public void actionPerformed(ActionEvent e) {
        //调用DepartDao业务逻辑处理类来完成增加的操作
        adduserDepartDao adduserdepartDao=new adduserDepartDao();
        if (e.getSource() == modifyBtn) {
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
                    || email == null || "".equals(email)) {
                JOptionPane.showMessageDialog(null, "信息填写不完整！");
                return;
            }
            //将用户输入的数据封装成一个Depart对象
            adduserDepart d = new adduserDepart(uname,id,pwd,gender,telNum,email);
            try {
                adduserdepartDao.save(d);//保存数据
                //adduserdepartDao.delete(d);//删除数据
                JOptionPane.showMessageDialog(null, "用户已通过注册");
                log.logger.debug("管理员通过了用户"+uname+"的信息");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else if(e.getSource() == delBtn){
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
                    || email == null || "".equals(email)) {
                JOptionPane.showMessageDialog(null, "信息填写不完整，请重新检查！");
                return;
            }
            //将用户输入的数据封装成一个Depart对象
            adduserDepartDao sdduserdepartDao = new adduserDepartDao();
            adduserDepart d = new adduserDepart(uname,id,pwd,gender,telNum,email);
            try {
                adduserdepartDao.delete(d);//修改数据
                JOptionPane.showMessageDialog(null, "驳回成功");
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
