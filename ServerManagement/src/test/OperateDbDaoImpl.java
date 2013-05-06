package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperateDbDaoImpl {
	private Connection conn;		//���ݿ����Ӷ���
	/**
	 * �������ݿ�
	 * @return result(0ʧ��,1�ɹ�)
	 * @author YinZhiwei
	 * @throws SQLException 
	 */
	public int backupDb(String dbName,String storePath) throws SQLException{
		int result = 0;
		conn = DBConnection.getConnection();	//��ȡ����
		String sql = "use master backup database ? to disk=?";
//ע��storePathΪ�����ļ���·�����ļ�������"D:\database\mydb\20110304.bak"
		String[] param ={dbName,storePath}; 
        PreparedStatement ps=null;      
        try {
            ps= conn.prepareStatement(sql);    		            if( param != null ) {
                for( int i = 0; i < param.length; i++ ) {
                  ps.setString(i+1, param[i]);         // ΪԤ����SQL���ò���
                }
            }
            ps.executeUpdate(); 						// ִ��SQL���
            result = 1;
        }  catch (SQLException e) {
			e.printStackTrace();
			result = 0;
        } finally {
        	// �ͷ���Դ
			try {
				ps.close();conn.close();
			} catch (SQLException e) {e.printStackTrace();
			}                     
        }
		return result;
	}
	
	/**
	 * ��ԭ���ݿ�
	 * @return result(0ʧ��,1�ɹ�)
	 * @author YinZhiwei
	 * @throws SQLException 
	 */
	public int restoreDb(String dbName,String bakPath) throws SQLException{
		int result = 0;
		conn = DBConnection.getConnection();	//��ȡ����
		String sql = "use master restore database ? from disk=? with replace";
	//ע��bakPathΪ�����ļ���·�����ļ�������"D:\database\mydb\20110304.bak"
		String[] param ={dbName,bakPath}; 
        PreparedStatement ps=null;      
        try {
            ps= conn.prepareStatement(sql);    		            if( param != null ) {
                for( int i = 0; i < param.length; i++ ) {
                ps.setString(i+1, param[i]);         // ΪԤ����SQL���ò���
                }
            }
            ps.executeUpdate(); 						// ִ��SQL���
            result = 1;
        }  catch (SQLException e) {
			e.printStackTrace();
			result = 0;
        } finally {
        	// �ͷ���Դ
			try {
				ps.close();conn.close();
			} catch (SQLException e) {e.printStackTrace();
			}                     
        }
		return result;
	}
}

