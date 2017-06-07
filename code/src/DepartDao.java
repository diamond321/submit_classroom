/**
 * Created by xsw on 2017/5/30.
 */
//完成教室信息添加、修改、查询等操作的的业务逻辑处理类

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

public class DepartDao {
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    //学院信息查询，返回值为Depart对象
    public List<Depart>findAll() throws Exception{
        //将查询的每一条数据封装为Depart对象
        List<Depart>dList=new ArrayList<Depart>();
        String sql="select clr_id,academy,place,equipment,peoNum from clr_info";//查询命令
        Connection conn =dbUtils.getConnection();//获得数据库连接对象
        PreparedStatement pstmt = conn.prepareStatement(sql);//创建对象
        ResultSet rs = pstmt.executeQuery();
        //将查询返回多条记录，对查询结果的每一行进行解析
        while(rs.next()){
            String clr_id=rs.getString("clr_id");
            String academy=rs.getString("academy");
            String place=rs.getString("place");
            String equipment=rs.getString("equipment");
            String peoNum=rs.getString("peoNum");
            //String use_time=rs.getString("use_time");
            //将数据表的记录封装为Depart对象，在把对象存在list集合中
            Depart d=new Depart(clr_id,academy,place,equipment,peoNum);
            dList.add(d);
        }
        dbUtils.close(rs,pstmt,conn);
        return dList;
    }
    //
   public void save(Depart d) throws Exception{
        // 插入数据的SQL语句
        String sql = "insert into clr_info(clr_id,academy,place,equipment,peoNum) values(?,?,?,?,?)";
        // 获得数据库连接对象
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getClr_id());
        pstmt.setString(2,d.getAcademy());
        pstmt.setString(3,d.getPlace());
        pstmt.setString(4,d.getEquipment());
        pstmt.setString(5,d.getPeoNum());
        //pstmt.setString(6,d.getUse_time());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }

    /*public void modify(Depart d) throws Exception{
        //插入数据的SQL语句
        String sql="delete from clr_info where oid=?";
        //插入数据的SQL语句
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getClr_id());
        pstmt.setString(2,d.getAcademy());
        pstmt.setString(3,d.getPlace());
        pstmt.setString(4,d.getEquipment());
        pstmt.setString(5,d.getPeoNum());
        //pstmt.setString(6,d.getUse_time());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }*/
    public void modify(Depart d) throws  Exception{
        String sql = "update clr_info set academy='"+d.getAcademy()+"',place='"+d.getPlace()+"',equipment='"+d.getEquipment()+"',peoNum='"+d.getPeoNum()+"' where clr_id='"+d.getClr_id()+"'";
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null,pstmt,conn);
    }
    public void delete(Depart d)throws Exception{
        String sql = "delete from clr_info where clr_id='"+d.getClr_id()+"'";
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null,pstmt,conn);
    }

    public void query(Depart d) throws Exception{
        String sql="select from clr_info where academy='"+d.getAcademy()+"' and place='"+d.getPlace()+"' and equipment='"+d.getEquipment()+"'and peoNum='"+d.getPeoNum()+"' and clr_id='"+d.getClr_id()+"'";
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null,pstmt,conn);
    }
}
