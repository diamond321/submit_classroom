import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

/**
 * Created by xsw on 2017/6/5.
 */
public class ad_modify extends JFrame implements ActionListener{
    //该窗体具备一个表和两个按钮
    private JButton modifyBtn, delBtn;
    private JTable table;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    private JLabel clr_idLbl, academyLbl, placeLbl,equipmentLbl,peoNumLbl;
    private JTextField clr_idField,academyField,placeField,equipmentField,peoNumField;
    DepartDao departDao;
    List<Depart> dList;
    int num;
    Object[][]data;
    TableListener a;
    public ad_modify() {

        // 创建窗体
        setTitle("修改教室信息");
        setSize(750, 400);
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

        modifyBtn = new JButton("修改");
        delBtn = new JButton("删除");
        setLayout(null);
        modifyBtn.setBounds(100, 300, 100, 20);
        delBtn.setBounds(300, 300, 100, 20);
        add(modifyBtn);
        add(delBtn);

        String[] columnsName={"教室编号","所属学院","位置","设备","可容纳人数"};
        try{
            departDao=new DepartDao();
            //调用departBao对象的findAll方法返回教室信息列表
            dList = departDao.findAll();
            //将list集合解析为JTable显示的数据模型
            num = dList.size();
            data=new Object[num][5];
            int index=0;
            for(Depart depart:dList){
                data[index][0]=depart.getClr_id();
                data[index][1]=depart.getAcademy();
                data[index][2]=depart.getPlace();
                data[index][3]=depart.getEquipment();
                data[index][4]=depart.getPeoNum();
                index++;
            }
            table.setModel(new DefaultTableModel(data,columnsName));
        } catch (Exception ec){
            //JOptionPane.showMessageDialog(null,"查询时出现异常。异常原因为："+ec.getMessage());
            ec.printStackTrace();
        }
        //初始化一个二维数组
        //String columnsName[]={"教室编号","所属学院","位置","设备","可容纳人数"};
        //Object[][] data={};
        table = new JTable(data,columnsName);

        TableListener a =new TableListener();
        table.addMouseListener(a);

        jScrollPane2.setBounds(20, 50, 450, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);

        clr_idLbl = new JLabel("教室:");
        academyLbl= new JLabel("所属学院:");
        placeLbl = new JLabel("所在位置:");
        equipmentLbl = new JLabel("已有设备:");
        peoNumLbl = new JLabel("可容纳人数:");


        clr_idField = new JTextField(20);
        academyField = new JTextField(20);
        placeField = new JTextField(20);
        equipmentField = new JTextField(20);
        peoNumField = new JTextField(20);

        clr_idLbl.setBounds(510, 50, 90, 20);
        clr_idField.setBounds(590, 50, 140, 20);// 90=30+60

        academyLbl.setBounds(510, 80, 90, 20); // 40=10+20+10
        academyField.setBounds(590, 80, 140, 20);

        placeLbl.setBounds(510, 140, 90, 20);
        placeField.setBounds(590, 140, 140, 20);// 90=30+60

        equipmentLbl.setBounds(510, 110, 90, 20); // 40=10+20+10
        equipmentField.setBounds(590, 110, 140, 20);

        peoNumLbl.setBounds(510, 170, 90, 20);
        peoNumField.setBounds(590, 170, 140, 20);// 90=30+60

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

        //注册事件监听
        modifyBtn.addActionListener(this);
        delBtn.addActionListener(this);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ad_modify();
    }
    class TableListener extends MouseAdapter {
        String clr_id, academy, place, equipment, peoNum;
        public void mouseClicked( MouseEvent e) {
           // String clr_id, academy, place, equipment, peoNum;
            int selRow = table.getSelectedRow();
            clr_id = table.getValueAt(selRow, 0).toString().trim();
            academy = table.getValueAt(selRow, 1).toString().trim();
            place = table.getValueAt(selRow, 2).toString().trim();
            equipment = table.getValueAt(selRow, 3).toString().trim();
            peoNum = table.getValueAt(selRow, 4).toString().trim();
            System.out.println(clr_id);
            System.out.println(academy);
            System.out.println(place);
            System.out.println(equipment);
            System.out.println(peoNum);

            clr_idField.setText(clr_id);
            academyField.setText(academy);
            placeField.setText(place);
            equipmentField.setText(equipment);
            peoNumField.setText(peoNum);
        }
    }
    public void actionPerformed(ActionEvent e) {
        //调用DepartDao业务逻辑处理类来完成增加的操作
        DepartDao departDao=new DepartDao();
        if (e.getSource() == modifyBtn) {
            String clr_id = clr_idField.getText();
            String academy = academyField.getText();
            String place = placeField.getText();
            String equipment = equipmentField.getText();
            String peoNum = peoNumField.getText();
            if (clr_id == null || "".equals(clr_id.trim()) || academy == null || "".equals(academy)) {
                JOptionPane.showMessageDialog(null, "教室和所属学院不能为空");
                return;
            }
            //将用户输入的数据封装成一个Depart对象
            Depart d = new Depart(clr_id, academy, place, equipment, peoNum);
            try {
                departDao.modify(d);//修改数据
                JOptionPane.showMessageDialog(null, "教室信息修改成功");
                log.logger.debug("管理员修改了"+clr_id+"的信息");
            } catch (Exception ec) {
                JOptionPane.showMessageDialog(null, "保存时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else if(e.getSource() == delBtn){
            String clr_id = clr_idField.getText();
            String academy = academyField.getText();
            String place = placeField.getText();
            String equipment = equipmentField.getText();
            String peoNum = peoNumField.getText();
            if (clr_id == null || "".equals(clr_id.trim()) || academy == null || "".equals(academy)) {
                JOptionPane.showMessageDialog(null, "教室和所属学院不能为空");
                return;
            }
            //将用户输入的数据封装成一个Depart对象
            Depart d = new Depart(clr_id, academy, place, equipment, peoNum);
            try {
                departDao.delete(d);//修改数据
                JOptionPane.showMessageDialog(null, "教室信息添加成功");
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
