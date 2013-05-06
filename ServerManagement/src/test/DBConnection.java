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

/*���ݿ������ࣺDBConnection*/
public class DBConnection {   
	/**
     * getConnection()�������õ����ݿ�����
     * @return ���ݿ�����
	 * @throws SQLException 
     */
	public static Connection getConnection() throws SQLException {
		
		Connection connect = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/android_final","root","1234");
		
		return connect;
	}
	
	/**
     * closeAll()�������ͷ���Դ
     * @param conn ���ݿ�����
     * @param pstmt PreparedStatement����
     * @param rs �����
     */
	 public static void closeAll( Connection conn, PreparedStatement pstmt, ResultSet rs ) {
	        /*  ���rs���գ��ر�rs  */
	        if(rs != null){
	            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
	        }
	        /*  ���pstmt���գ��ر�pstmt  */
	        if(pstmt != null){
	            try { pstmt.close();} catch (SQLException e) {e.printStackTrace();}
	        }
	        /*  ���conn���գ��ر�conn  */
	        if(conn != null){
	            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
	        }
	}

}
