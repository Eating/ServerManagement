package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import javax.sql.DataSource;

import org.hibernate.jdbc.JDBCContext.Context;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*数据库连接类：DBConnection*/
public class DBConnection {   
	/**
     * getConnection()方法：得到数据库连接
     * @return 数据库连接
	 * @throws SQLException 
     */
	public static Connection getConnection() throws SQLException {
		
		Connection connect = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/android_final","root","1234");
		
		return connect;
	}
	
	/**
     * closeAll()方法：释放资源
     * @param conn 数据库连接
     * @param pstmt PreparedStatement对象
     * @param rs 结果集
     */
	 public static void closeAll( Connection conn, PreparedStatement pstmt, ResultSet rs ) {
	        /*  如果rs不空，关闭rs  */
	        if(rs != null){
	            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
	        }
	        /*  如果pstmt不空，关闭pstmt  */
	        if(pstmt != null){
	            try { pstmt.close();} catch (SQLException e) {e.printStackTrace();}
	        }
	        /*  如果conn不空，关闭conn  */
	        if(conn != null){
	            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
	        }
	}

}
