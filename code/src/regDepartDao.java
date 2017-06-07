import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xsw on 2017/6/3.
 */
public class regDepartDao {
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    //学院信息查询，返回值为subDepart对象
    public List<regDepart> findAll() throws Exception{
        //将查询的每一条数据封装为subDepart对象
        List<regDepart>dList=new ArrayList<>();
        String sql="select con_uname, con_id, con_pwd, con_gender, con_telNum,con_email from confirm_reg";//查询命令
        Connection conn =dbUtils.getConnection();//获得数据库连接对象
        PreparedStatement pstmt = conn.prepareStatement(sql);//创建对象
        ResultSet rs = pstmt.executeQuery();
        //将查询返回多条记录，对查询结果的每一行进行解析
        while(rs.next()){
            String con_uname=rs.getString("con_uname");
            String con_id=rs.getString("con_id");
            String con_pwd=rs.getString("con_pwd");
            String con_gender=rs.getString("con_gender");
            String con_telNum=rs.getString("con_telNum");
            String con_email=rs.getString("con_email");
            //将数据表的记录封装为Depart对象，在把对象存在list集合中
            regDepart d=new regDepart(con_uname, con_id, con_pwd,con_gender,con_telNum, con_email);
            dList.add(d);
        }
        dbUtils.close(rs,pstmt,conn);
        return dList;
    }
    //
    public void save(regDepart d) throws Exception{
        // 插入数据的SQL语句
        String sql = "insert into confirm_reg( con_id,con_uname, con_pwd, con_gender, con_telNum,con_email) values(?,?,?,?,?,?)";
        // 获得数据库连接对象
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getId());
        pstmt.setString(2,d.getUname());
        pstmt.setString(3,d.getPwd());
        pstmt.setString(4,d.getGender());
        pstmt.setString(5,d.getTelNum());
        pstmt.setString(6,d.getEmail());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }


    /*public void con_save(adduserDepart d) throws Exception{
        // 插入数据的SQL语句
        String sql = "insert into user_info(uname, id, pwd, gender, telNum,email) values(?,?,?,?,?,?)";
        // 获得数据库连接对象
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getId());
        pstmt.setString(2,d.getUname());
        pstmt.setString(3,d.getPwd());
        pstmt.setString(4,d.getGender());
        pstmt.setString(5,d.getTelNum());
        pstmt.setString(6,d.getEmail());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }*/
}
