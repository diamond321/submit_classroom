import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalendarPanel extends JPanel {

    /**
     日期控件
     */
    private static final long serialVersionUID = 1L;

    private JLabel btn_lastYear = null;
    private JLabel btn_nextYear = null;
    private JLabel btn_lastMonth = null;
    private JLabel btn_nextMonth = null;
    private JLabel lb_datetime = null;
    private JPanel panel_maincalenderpanel = null;
    private JLabel lblNewLabel = null;
    private JLabel label = null;
    private JLabel label_1 = null;
    private JLabel label_2 = null;
    private JLabel label_3 = null;
    private JLabel label_4 = null;
    private JLabel label_5 = null;
    private JLabel btn_close = null;
    private JButton btn_today = null;
    private JButton btn_cancel = null;
    private Object component = null;       //日历控件的载体组件，如TextField
    private String returnDateStr = null;   //保存选中的日期
    private CallBack callBack = null;

    private String patern = "yyyy-MM-dd";     //日期格式
    private SimpleDateFormat sdf = null;
    private String nowDatetime = null ;    //当前系统日期字符串

    /**
     * 带参数的构造函数，该构造函数将构造一个设置了日历控件的载体组件的CalenderPanel对象实例
     * @param component  日历控件的载体组件，可以是TextField、JTextField、Label、JLabel
     * @param patern  日期格式 ，默认为yyyy-MM-dd
     */
    public CalendarPanel(Object component,String patern) {
        this(patern);
        this.component = component;
    }

    /**
     * 带参数的构造函数，该构造函数将构造一个CalenderPanel对象实例，该对象时候没有设置日历控件的载体组件
     * * @param patern  日期格式，默认为yyyy-MM-dd
     */
    public CalendarPanel(String patern) {
        super();

        //初始化日期格式化
        this.patern = patern;
        sdf = new SimpleDateFormat(patern);
        nowDatetime = sdf.format(new Date());

        setLayout(null);
        //设置日历控件的整体主面板边框为灰色
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        //初始化日历控件上的各个组件
        init();
        //创建日历控件的日期部分面板
        createCalendarPanel(nowDatetime);
    }

    /**
     * 无参数的构造函数，该构造函数将构造一个CalenderPanel对象实例，该对象时候没有设置日历控件的载体组件
     */
    public CalendarPanel() {
        super();

        //初始化日期格式化
        sdf = new SimpleDateFormat(patern);
        nowDatetime = sdf.format(new Date());

        setLayout(null);
        //设置日历控件的整体主面板边框为灰色
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        //初始化日历控件上的各个组件
        init();
        //创建日历控件的日期部分面板
        createCalendarPanel(nowDatetime);
    }
    /**
     * 获取日历控件的载体组件
     * @return  返回该日历控件中的载体组件，如果没有设置，则返回null;
     */
    public Object getComponent() {
        return component;
    }
    /**
     * 设置日历控件的载体组件
     * @param component
     */
    public void setComponent(Object component) {
        this.component = component;
    }

    /**
     * 获取选择日期时候的日期字符串
     * @return
     */
    public String getReturnDateStr() {
        return returnDateStr;
    }

    /**
     * 获取日历控件的回调接口对象
     * @return
     */
    public CallBack getCallBack() {
        return callBack;
    }

    /**
     * 设置日历控件的回调接口对象
     * @param callBack
     */
    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }


    /**
     * 创建下拉选择日历控件的主体面板(即显示日期部分的面板)
     * @param date   需要显示的日期
     */
    private void createCalendarPanel(String date){
        //每次刷新日期显示，需要重新创建日期部分的面板
        //如果日期部分的面板不为null,可以先将其remove，然后再创建
        if(panel_maincalenderpanel!=null){
            panel_maincalenderpanel.setVisible(false);
            this.remove(panel_maincalenderpanel);
        }

        //创建新的日期部分的面板
        panel_maincalenderpanel = new JPanel();
        panel_maincalenderpanel.setBackground(Color.WHITE);
        panel_maincalenderpanel.setBounds(2, 47, 247, 156);
        panel_maincalenderpanel.setLayout(new GridLayout(6,7));
        add(panel_maincalenderpanel);

        Date today = null;
        try {
            today = sdf.parse(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
            return;
        }

        Calendar c = new GregorianCalendar();
        //将给定日期设置为需要显示的日期
        c.setTime(today);
        //需要将日改成1号，因为我们需要获取给定日期的第一天是星期几
        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH), 1);

        int firstDayInMonthOfWeek = c.get(Calendar.DAY_OF_WEEK);              //给定日期的第一天是星期几
        int daysOfMonth  = c.getActualMaximum(Calendar.DAY_OF_MONTH);         //给定日期对应月份的天数

        //首先生成给定日期第一天之前的空白部分
        //例如：2011-11的第一天就是星期二，那么星期二之前的（星期日，星期一）我们不显示内容，需要生成空白
        for(int i=1;i<firstDayInMonthOfWeek;i++){
            JLabel bnt = new JLabel("");
            bnt.setSize(27,23);
            bnt.setBackground(Color.WHITE);
            panel_maincalenderpanel.add(bnt);
        }

        //然后生成给定日期的日期部分，即输出1,2,3....31这样的日期
        for(int i=1;i<=daysOfMonth;i++){
            final JLabel bnt = new JLabel(String.valueOf(i));
            bnt.setHorizontalAlignment(SwingConstants.CENTER);
            bnt.setSize(27,23);
            bnt.setBackground(Color.WHITE);
            if(Calendar.getInstance().get(Calendar.DATE)==i){
                //将系统当期日期对应的组件的边框显示为绿色
                bnt.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }else{
                //其他的日期组件边框显示为白色
                bnt.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }

            //为定义的日期组件添加鼠标事件
            bnt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    //当鼠标经过对应的日期上面时候，该日期对应的组件边框显示成绿色
                    bnt.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(Calendar.getInstance().get(Calendar.DATE)==Integer.parseInt(((JLabel)e.getSource()).getText().trim())){
                        //当鼠标离开对应日期组件时候，系统当期日期对应的组件的边框显示为绿色
                        bnt.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                    }else{
                        //其他的日期对应的组件边框为白色
                        bnt.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {

                    //鼠标单击事件，当鼠标选择某个日期时候，需要将对应的日期按照给定的日期格式填充到日历控件的载体组件上，即setText(..)
                    String dateStr = lb_datetime.getText().trim();
                    try {
                        dateStr = sdf.format(sdf.parse(dateStr));
                        dateStr = dateStr.substring(0,dateStr.length()-2);
                        if (component instanceof java.awt.TextField){
                            TextField txt = (TextField)component;
                            dateStr += ((JLabel)e.getSource()).getText().trim();
                            dateStr = sdf.format(sdf.parse(dateStr));
                            txt.setText(dateStr);
                        }
                        if (component instanceof java.awt.Label){
                            Label label = (Label)component;
                            dateStr += ((JLabel)e.getSource()).getText().trim();
                            dateStr = sdf.format(sdf.parse(dateStr));
                            label.setText(dateStr);

                        }
                        if (component instanceof javax.swing.JTextField){
                            JTextField txt = (JTextField)component;
                            dateStr += ((JLabel)e.getSource()).getText().trim();
                            dateStr = sdf.format(sdf.parse(dateStr));
                            txt.setText(dateStr);

                        }
                        if (component instanceof javax.swing.JLabel){
                            JLabel label = (JLabel)component;
                            dateStr += ((JLabel)e.getSource()).getText().trim();
                            dateStr = sdf.format(sdf.parse(dateStr));
                            label.setText(dateStr);
                        }

                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }



                    //为了能够让那个载体组件自用空，这里也需要设置returnDateStr并调用回调接口
                    returnDateStr = dateStr;
                    if(callBack!=null){
                        callBack.callback();
                    }

                    //选择日期后需要将日历控件隐藏
                    CalendarPanel.this.setVisible(false);

                }


            });
            panel_maincalenderpanel.add(bnt);
        }

        for(int i=1;i<6*7-(firstDayInMonthOfWeek-1)-daysOfMonth;i++){
            JLabel bnt = new JLabel("");
            bnt.setSize(27,23);
            bnt.setBackground(Color.WHITE);
            panel_maincalenderpanel.add(bnt);
        }

        panel_maincalenderpanel.validate();
    }



    /**
     * 初始化所有控件
     */
    private void init(){
        //上一年操作组件，这里使用一个JLabel
        btn_lastYear = new JLabel("<<");
        btn_lastYear.setBounds(10, 0, 27, 23);
        add(btn_lastYear);
        btn_lastYear.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //改变日历控件面板的显示日期
                changTheDate(Calendar.YEAR,false);
            }

        });

        //下一年操作组件，这里使用一个JLabel
        btn_nextYear = new JLabel(">>");
        btn_nextYear.setBounds(179, 0, 27, 23);
        add(btn_nextYear);
        btn_nextYear.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //改变日历控件面板的显示日期
                changTheDate(Calendar.YEAR,true);
            }

        });

        //上一月操作组件，这里使用一个JLabel
        btn_lastMonth = new JLabel("<");
        btn_lastMonth.setBounds(39, 0, 27, 23);
        add(btn_lastMonth);
        btn_lastMonth.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //改变日历控件面板的显示日期
                changTheDate(Calendar.MONTH,false);
            }

        });

        //下一月操作组件，这里使用一个JLabel
        btn_nextMonth = new JLabel(">");
        btn_nextMonth.setBounds(150, 0, 27, 23);
        add(btn_nextMonth);
        btn_nextMonth.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //改变日历控件面板的显示日期
                changTheDate(Calendar.MONTH,true);
            }

        });

        //显示日期的lable对象
        lb_datetime = new JLabel("");
        lb_datetime.setBounds(58, 2, 86, 19);
        lb_datetime.setHorizontalAlignment(SwingConstants.CENTER);
        add(lb_datetime);
        lb_datetime.setText(nowDatetime);

        lblNewLabel = new JLabel("日");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(2, 22, 27, 23);
        add(lblNewLabel);

        label = new JLabel("一");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(39, 22, 27, 23);
        add(label);

        label_1 = new JLabel("二");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(76, 22, 27, 23);
        add(label_1);

        label_2 = new JLabel("三");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setBounds(113, 22, 27, 23);
        add(label_2);

        label_3 = new JLabel("四");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setBounds(147, 22, 27, 23);
        add(label_3);

        label_4 = new JLabel("五");
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        label_4.setBounds(184, 22, 27, 23);
        add(label_4);

        label_5 = new JLabel("六");
        label_5.setForeground(Color.GREEN);
        label_5.setHorizontalAlignment(SwingConstants.CENTER);
        label_5.setBounds(221, 22, 27, 23);
        add(label_5);

        btn_close = new JLabel("[X]");
        btn_close.setBounds(222, 0, 27, 23);
        btn_close.setForeground(Color.RED);
        add(btn_close);
        btn_close.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                CalendarPanel.this.setVisible(false);
            }

        });


        //用于快速显示今天日期的按钮
        btn_today = new JButton("今天");
        btn_today.setBounds(10, 203, 60, 23);
        btn_today.setContentAreaFilled(false);
        btn_today.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(btn_today);
        btn_today.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCalendarPanel(nowDatetime);
                lb_datetime.setText(nowDatetime);
            }
        });


        //用于取消日期选择的按钮
        btn_cancel = new JButton("取消");
        btn_cancel.setBounds(179, 203, 60, 23);
        btn_cancel.setContentAreaFilled(false);
        btn_cancel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(btn_cancel);
        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarPanel.this.setVisible(false);
            }
        });
    }

    /**
     * 重新设置日历面板显示为系统当前日期
     */
    public void reset(){
        createCalendarPanel(nowDatetime);
        lb_datetime.setText(nowDatetime);
    }

    /**
     * 改变当前日历控件面板的日期显示
     * @param YEAR_OR_MONTH  是对YEAR还是MONTH操作，这里需要传入Calendar类对应的常量值：Calendar.YEAR,Calendar.MONTH
     * @param flag  对YEAR_OR_MONTH属性是进行加法操作还是减法操作，如果flag为true则是加法操作，否则为减法操作
     */
    public void changTheDate(int YEAR_OR_MONTH,boolean flag){
        String dateStr = lb_datetime.getText().trim();
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e1) {
            e1.printStackTrace();
            return;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(flag){
            //将对应的日期属性进行加法操作
            c.set(YEAR_OR_MONTH,c.get(YEAR_OR_MONTH)+1 );
        }else{
            //将对应的日期属性进行减法操作
            c.set(YEAR_OR_MONTH,c.get(YEAR_OR_MONTH)-1 );
        }

        date = c.getTime();
        lb_datetime.setText(sdf.format(date));
        createCalendarPanel(sdf.format(date));
    }

    public static void main(String[] args) {
        Calendar c = new GregorianCalendar(2011,11,1);
        System.out.println( c.get(Calendar.DAY_OF_WEEK));
    }


    public void initCalendarPanel(){
        if(component instanceof javax.swing.JComponent){
            JComponent j = (JComponent)component;
            int w = j.getWidth();
            int h = j.getHeight();
            int x = j.getX();
            int y = j.getY();
            System.out.println("with:"+w +"height:"+h+"x:"+x+"y:"+y);
            this.setComponent(component);
            this.setBounds((x), y+h, 251, 245);
            this.setVisible(false);
            j.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (CalendarPanel.this.isVisible()){
                        CalendarPanel.this.setVisible(false);
                    }else{
                        CalendarPanel.this.setVisible(true);
                        CalendarPanel.this.reset();
                    }
                }
            });
        }
    }

}

/**
 * 回调接口
 *
 */
interface CallBack{

    public void callback();
}
