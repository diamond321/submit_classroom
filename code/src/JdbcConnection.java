//数据库的连接
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcConnection{
    private static String DRIVERCLASS= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String URL="jdbc:sqlserver://127.0.0.1:1433;databaseName=submit_classroom; ";
    private static String USENAMR="sa";
    private static String PASSWORD="111111";
    //获取数据库连接对象
    public Connection getConnection() throws ClassNotFoundException,SQLException{
        Class.forName(DRIVERCLASS);//加载驱动程序
        //建立与数据库的连接
        Connection conn=DriverManager.getConnection(URL,USENAMR,PASSWORD);
        return conn;
    }
    //关闭连接对象
    public void close(ResultSet rs,Statement stmt,Connection conn){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //测试数据库的连接
    public static void main(String[] args){
        JdbcConnection dbUtils=new JdbcConnection();
        Connection conn=null;
        try {
            conn = dbUtils.getConnection();
            System.out.println("数据库连接成功-" + conn);
        }catch(ClassNotFoundException e){
            System.out.println("数据库连接失败-" + e.getMessage());
        }catch(SQLException e){
            System.out.println("数据库连接失败-" + e.getMessage());
        }finally{
            dbUtils.close(null,null,conn);//释放连接资源
        }
    }
}