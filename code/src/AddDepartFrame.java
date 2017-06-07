/**
 * Created by xsw on 2017/6/2.
 */
//面向管理员添加学院信息的窗体类

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddDepartFrame extends JFrame implements ActionListener {
    //该窗体具备五个标签，五个文本框，两个按钮
    private JLabel clr_idLbl, academyLbl, placeLbl,equipmentLbl,peoNumLbl;
    private JTextField clr_idField,academyField,placeField,equipmentField,peoNumField;
    private JButton okBtn, cancelBtn;

    public AddDepartFrame() {

        // 创建窗体
        setTitle("添加教室信息");
        setSize(280, 260);
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

        clr_idLbl = new JLabel("教室编号:");
        academyLbl= new JLabel("所属学院:");
        placeLbl = new JLabel("所在位置:");
        equipmentLbl = new JLabel("已有设备:");
        peoNumLbl = new JLabel("可容纳人数:");


        clr_idField = new JTextField(20);
        academyField = new JTextField(20);
        placeField = new JTextField(20);
        equipmentField = new JTextField(20);
        peoNumField = new JTextField(20);
        okBtn = new JButton("确定");
        cancelBtn = new JButton("取消");

        // 窗体位置设置
        setLayout(null);
        clr_idLbl.setBounds(30, 10, 90, 20);
        clr_idField.setBounds(100, 10, 140, 20);// 90=30+60

        academyLbl.setBounds(30, 40, 90, 20); // 40=10+20+10
        academyField.setBounds(100, 40, 140, 20);

        placeLbl.setBounds(30, 100, 90, 20);
        placeField.setBounds(100, 100, 140, 20);// 90=30+60

        equipmentLbl.setBounds(30, 70, 90, 20); // 40=10+20+10
        equipmentField.setBounds(100, 70, 140, 20);

        peoNumLbl.setBounds(30, 130, 90, 20);
        peoNumField.setBounds(100, 130, 140, 20);// 90=30+60

        okBtn.setBounds(30, 180, 100, 20);
        cancelBtn.setBounds(140, 180, 100, 20);
        //将上述桌面添加进来
        add(clr_idLbl);
        add(clr_idField);
        add(academyLbl);
        add(academyField);
        add(equipmentLbl);
        add(equipmentField);
        add(placeLbl);
        add(placeField);
        add(peoNumLbl);
        add(peoNumField);
        add(okBtn);
        add(cancelBtn);
        //注册事件监听
        okBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddDepartFrame();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okBtn) {
            String clr_id = clr_idField.getText();
            String academy = academyField.getText();
            String place = placeField.getText();
            String equipment = equipmentField.getText();
            String peoNum = peoNumField.getText();
            if (clr_id == null || "".equals(clr_id.trim()) || academy == null || "".equals(academy)) {
                JOptionPane.showMessageDialog(null, "教室编号和所属学院不能为空");
                return;
            }
            //调用DepartDao业务逻辑处理类来完成增加的操作
            DepartDao departDao = new DepartDao();
            //将用户输入的数据封装成一个Depart对象
            Depart d = new Depart(clr_id, academy, place, equipment, peoNum);
            try {
                departDao.save(d);//保存数据
                JOptionPane.showMessageDialog(null, "教室信息添加成功");
                log.logger.debug("管理员添加了教室"+clr_id+"");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        } else {
            this.dispose();//关闭当前窗口
        }
    }
}
