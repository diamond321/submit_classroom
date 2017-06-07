import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
//按条件检索查询
/**
 * Created by xsw on 2017/6/3.
 */
public class Query extends JFrame implements ActionListener {
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    //该窗体具备五个标签，五个文本框，两个按钮
    private JLabel  idLbl,placeLbl,humLbl, equipLbl,timeLbl,dateLbl;
    private JTextField idField,placeField,humField,equipField,dateField;
    private JButton okBtn, cancelBtn;
    private JComboBox timeCombox;
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    DepartDao departDao;
    List<Depart> dList;
    int num;
    Object[][]data;
    ad_modify.TableListener a;
    public Query() {

        // 创建窗体
        setTitle("教室查询");
        setSize(800, 350);
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

        idLbl = new JLabel("教室编号:");
        dateLbl= new JLabel("日期:");
        timeLbl= new JLabel("时间:");
        placeLbl= new JLabel("地点:");
        humLbl = new JLabel("容纳人数:");
        equipLbl = new JLabel("设备:");
        timeCombox=new JComboBox();
        timeCombox.addItem("8:00-9:30");
        timeCombox.addItem("10:00-11:30");
        timeCombox.addItem("12:00-13:00");
        timeCombox.addItem("13:30-15:00");
        timeCombox.addItem("15:30-17:00");
        timeCombox.addItem("17:30-21:00");
        timeCombox.addItem("21:30-22:30");
        idField = new JTextField(20);
        dateField = new JTextField(20);
        placeField = new JTextField(20);
        humField = new JTextField(20);
        equipField = new JTextField(20);

        okBtn = new JButton("查询");
        //cancelBtn = new JButton("取消");
        // 窗体位置设置
        setLayout(null);

        idLbl.setBounds(70, 40, 90, 20); // 40=10+20+10
        idField.setBounds(150, 40, 120, 20);

        placeLbl.setBounds(70, 130, 90, 20);
        placeField.setBounds(150, 130, 120, 20);// 90=30+60

        dateLbl.setBounds(70, 70, 90, 20);
        dateField.setBounds(150, 70, 120, 20);// 90=30+60

        timeLbl.setBounds(70, 100, 90, 20); // 40=10+20+10
        timeCombox.setBounds(150, 100, 120, 20);

        equipLbl.setBounds(70, 160, 90, 20);
        equipField.setBounds(150, 160, 120, 20);// 90=30+60

        humLbl.setBounds(70, 190, 90, 20);
        humField.setBounds(150, 190, 120, 20);// 90=30+60

        okBtn.setBounds(190, 260, 80, 20);
        //cancelBtn.setBounds(180, 200, 80, 20);
        //将上述桌面添加进来
        add(idLbl);
        add(idField);
        add(placeLbl);
        add(placeField);
        add(dateLbl);
        add(dateField);
        CalendarPanel p = new CalendarPanel(dateField, "yyyy/MM/dd");
        p.initCalendarPanel();

        JLabel l = new JLabel("日历面板");
        p.add(l);
        getContentPane().add(p);
        getContentPane().add(dateField);
        //setSize(500, 400);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        add(humLbl);
        add(humField);
        add(equipLbl);
        add(equipField);
        add(timeLbl);
        add(timeCombox);
        add(okBtn);
        //add(cancelBtn);
        //注册事件监听
        okBtn.addActionListener(this);
//        cancelBtn.addActionListener(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Query();
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == okBtn) {
            String[] columnsName={"教室编号","所属学院","位置","设备","可容纳人数"};
            try{
                departDao=new DepartDao();
                //调用departBao对象的findAll方法返回教室信息列表
                dList = departDao.findAll();
                //将list集合解析为JTable显示的数据模型
                num = dList.size();
                data=new Object[num][5];
                /*int index=0;
                for(Depart depart:dList){
                    data[index][0]=depart.getClr_id();
                    data[index][1]=depart.getAcademy();
                    data[index][2]=depart.getPlace();
                    data[index][3]=depart.getEquipment();
                    data[index][4]=depart.getPeoNum();
                    index++;
                }*/
                table.setModel(new DefaultTableModel(data,columnsName));
            } catch (Exception ec){
                //JOptionPane.showMessageDialog(null,"查询时出现异常。异常原因为："+ec.getMessage());
                ec.printStackTrace();
            }
            table = new JTable(data,columnsName);

            //TableListener a =new TableListener();
            //table.addMouseListener(a);

            jScrollPane2.setBounds(300, 40, 450, 200);
            jScrollPane2.setViewportView(table);
            this.add(jScrollPane2);

            String clr_id = idField.getText();
            String date = dateField.getText();
            String time = (String )timeCombox.getSelectedItem();
            String  place= placeField.getText();
            String peoNum = humField.getText();
            String equipment = equipField.getText();

            String sql="select * from sub_info where date_begin='"+date+"' and date_end='"+time+"'";
            System.out.println(sql);
            try{
            Connection conn = dbUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            String[] used = new String[100];
            int i=0;
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                used[i]=rs.getString("clr_id");
                i++;
            }
            sql="select * from clr_info  where equipment='"+equipment+"' and peoNum='"+peoNum+"' and place='"+place+"'";
                conn = dbUtils.getConnection();
                pstmt = conn.prepareStatement(sql);
                rs=pstmt.executeQuery();
                int flag=0;
                while(rs.next()){
                    for(int j=0;j<i;j++){
                        if(used[j].equals(rs.getString("clr_id"))){
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        int index=0;
                        data[index][0]=rs.getString("clr_id");
                        data[index][1]=rs.getString("academy");
                        data[index][2]=rs.getString("place");
                        data[index][3]=rs.getString("equipment");
                        data[index][4]=rs.getString("peoNum");
                        table.setModel(new DefaultTableModel(data,columnsName));
                       //break;
                    }
                }
            }
            catch(Exception ec){
                JOptionPane.showMessageDialog(null, "查询时出现异常，异常原因为:" + ec.getMessage());
                ec.printStackTrace();
            }
        }
        else {
            this.dispose();//关闭当前窗口
        }
    }
}