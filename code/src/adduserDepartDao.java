import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by xsw on 2017/6/4.
 */
public class adduserDepartDao {
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    //学院信息查询，返回值为subDepart对象
    public List<adduserDepart> findAll() throws Exception{
        //将查询的每一条数据封装为subDepart对象
        List<adduserDepart>dList=new ArrayList<>();
        String sql="select uname, id, pwd, gender, telNum,email from user_info";//查询命令
        Connection conn =dbUtils.getConnection();//获得数据库连接对象
        PreparedStatement pstmt = conn.prepareStatement(sql);//创建对象
        ResultSet rs = pstmt.executeQuery();
        //将查询返回多条记录，对查询结果的每一行进行解析
        while(rs.next()){
            String uname=rs.getString("uname");
            String id=rs.getString("id");
            String pwd=rs.getString("pwd");
            String gender=rs.getString("gender");
            String telNum=rs.getString("telNum");
            String email=rs.getString("email");
            //将数据表的记录封装为Depart对象，在把对象存在list集合中
            adduserDepart d=new adduserDepart(uname, id, pwd,gender,telNum, email);
            dList.add(d);
        }
        dbUtils.close(rs,pstmt,conn);
        return dList;
    }
    //
    public void save(adduserDepart d) throws Exception{
        // 插入数据的SQL语句
        String sql = "insert into user_info(id,uname, pwd, gender, telNum,email) values(?,?,?,?,?,?)";
        // 获得数据库连接对象
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.setString(1,d.getId());
        pstmt.setString(2,d.getUname());
        pstmt.setString(3,MD5.MD5(d.getPwd()));
        pstmt.setString(4,d.getGender());
        pstmt.setString(5,d.getTelNum());
        pstmt.setString(6,d.getEmail());
        //提交数据
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }
    public void modify(adduserDepart d) throws  Exception{
        String sql = "update user_info set email='"+d.getEmail()+"',uname='"+d.getUname()+"' ,pwd='"+d.getPwd()+"', telNum='"+d.getTelNum()+"', gender='"+d.getGender()+"' where id='"+d.getId()+"'";
        System.out.println(sql);
        Connection conn = dbUtils.getConnection();
        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.executeUpdate();
        dbUtils.close(null,pstmt,conn);

    }
    public void delete (adduserDepart d) throws Exception{
        //插入数据的SQL语句
        String sql="delete from user_info where id='"+d.getId()+"'";
        //获得数据库的连接
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }
    public void delete_reg (adduserDepart d) throws Exception{
        //插入数据的SQL语句
        String sql="delete from comfirm_reg where con_id='"+d.getId()+"'";
        //获得数据库的连接
        Connection conn = dbUtils.getConnection();
        // 创建PreparedStatement对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //为动态参数赋值
        pstmt.executeUpdate();
        //关闭数据库的连接
        dbUtils.close(null, pstmt, conn);
    }
}
