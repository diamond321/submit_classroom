import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login{
    private JdbcConnection dbUtils=new JdbcConnection();//数据库连接类对象
    //用户登录处理方法
    public boolean loginCheck(String account ,String password,String role)throws Exception{
        String sql="";
        if("管理员".equals(role))sql="select * from [adinfo] where adid=? and adpwd=?";
        else if("普通用户".equals(role))sql="select * from [user_info] where id=? and pwd=?";
        else sql="select * from T where Tno=?and TPwd?";
        return query(sql,account,password);
    }
    public boolean query(String sql,String param1,String param2)throws Exception{
        boolean valid=false;
        Connection conn =dbUtils.getConnection();//获得数据库连接对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,param1);
        pstmt.setString(2,MD5.MD5(param2));
        ResultSet rs =pstmt.executeQuery();
        if(rs.next())valid =true;
        dbUtils.close(rs,pstmt,conn);
        return valid;
    }
}