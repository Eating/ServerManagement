package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperateDbDaoImpl {
	private Connection conn;		//数据库连接对象
	/**
	 * 备份数据库
	 * @return result(0失败,1成功)
	 * @author YinZhiwei
	 * @throws SQLException 
	 */
	public int backupDb(String dbName,String storePath) throws SQLException{
		int result = 0;
		conn = DBConnection.getConnection();	//获取连接
		String sql = "use master backup database ? to disk=?";
//注：storePath为备份文件的路径加文件名，如"D:\database\mydb\20110304.bak"
		String[] param ={dbName,storePath}; 
        PreparedStatement ps=null;      
        try {
            ps= conn.prepareStatement(sql);    		            if( param != null ) {
                for( int i = 0; i < param.length; i++ ) {
                  ps.setString(i+1, param[i]);         // 为预编译SQL设置参数
                }
            }
            ps.executeUpdate(); 						// 执行SQL语句
            result = 1;
        }  catch (SQLException e) {
			e.printStackTrace();
			result = 0;
        } finally {
        	// 释放资源
			try {
				ps.close();conn.close();
			} catch (SQLException e) {e.printStackTrace();
			}                     
        }
		return result;
	}
	
	/**
	 * 还原数据库
	 * @return result(0失败,1成功)
	 * @author YinZhiwei
	 * @throws SQLException 
	 */
	public int restoreDb(String dbName,String bakPath) throws SQLException{
		int result = 0;
		conn = DBConnection.getConnection();	//获取连接
		String sql = "use master restore database ? from disk=? with replace";
	//注：bakPath为备份文件的路径加文件名，如"D:\database\mydb\20110304.bak"
		String[] param ={dbName,bakPath}; 
        PreparedStatement ps=null;      
        try {
            ps= conn.prepareStatement(sql);    		            if( param != null ) {
                for( int i = 0; i < param.length; i++ ) {
                ps.setString(i+1, param[i]);         // 为预编译SQL设置参数
                }
            }
            ps.executeUpdate(); 						// 执行SQL语句
            result = 1;
        }  catch (SQLException e) {
			e.printStackTrace();
			result = 0;
        } finally {
        	// 释放资源
			try {
				ps.close();conn.close();
			} catch (SQLException e) {e.printStackTrace();
			}                     
        }
		return result;
	}
}

