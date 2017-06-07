/**
 * Created by xsw on 2017/5/26.
 */
//用户界面

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class UserUI extends JFrame {
    private JMenuBar menuBar;
    //菜单：教室浏览、教室查询、教室预定、个人账户
    private JMenu clrMenu,individualMenu;
    //菜单项：添加信息、删除信息、修改密码
    private JMenuItem modifyItem,modpwdItem,scanItem,queryItem,subItem;
    private JTable table;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    private JButton subBtn;
    DepartDao departDao;
    List<Depart> dList;
    int num;
    Object[][]data;
    ad_modify.TableListener a;
    public UserUI(String account){
        setTitle("教室预约系统"+"当前用户为："+account);//设置窗体标题
        setSize(500,400);
        WindowUtils.displayOnDesktopCenter(this);//窗体居中的方法
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//正常关闭窗体
        creatMenu(account);//添加菜单
        setVisible(true);//显示窗体
    }
    //创建菜单
    public void creatMenu(String account) {
        menuBar = new JMenuBar();//创建菜单栏
        //教室浏览
        clrMenu = new JMenu("教室信息");
        //scanItem=new JMenuItem("教室浏览");
        queryItem=new JMenuItem("教室查询");
        subItem=new JMenuItem("教室预定");
       // clrMenu.add(scanItem);
        //clrMenu.addSeparator();
        clrMenu.add(queryItem);
        clrMenu.addSeparator();
        clrMenu.add(subItem);
        subItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new subDepartFrame();
            }
        });
        queryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new Query();}
        });
        individualMenu=new JMenu("个人账户");
        modifyItem = new JMenuItem("修改信息");
        //modpwdItem=new JMenuItem("修改密码");
        individualMenu.add(modifyItem);
        //individualMenu.addSeparator();
        //individualMenu.add(modpwdItem);
        modifyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new modify_ind_info(account);
            }
        });
        menuBar.add(clrMenu);
        // menuBar.add(subMenu);
        menuBar.add(individualMenu);
        this.setJMenuBar(menuBar);

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
           // JOptionPane.showMessageDialog(null,"查询时出现异常。异常原因为："+ec.getMessage());
            ec.printStackTrace();
        }
        table = new JTable(data,columnsName);
        TableListener a =new TableListener();
        table.addMouseListener(a);

        jScrollPane2.setBounds(20, 50, 450, 200);
        jScrollPane2.setViewportView(table);
        this.add(jScrollPane2);

        subBtn = new JButton("预定");
        setLayout(null);
        subBtn.setBounds(360, 300, 100, 20);
        add(subBtn);
        subBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new subDepartFrame();
            }
        });
        setVisible(true);//显示窗体

    }
    public static void main (String[] args){
        new UserUI("user");
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
        }
    }
}
