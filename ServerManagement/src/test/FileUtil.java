package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * �ļ�������
 * @author YinZhiwei
*
 */
public class FileUtil {
	/**
	 * �����ļ��Ƿ����
	 * @param fileName(�ļ����������ļ�·������չ��)
	 */
	public static boolean checkFile(String fileName){
		File file = new File(fileName);
		if(file.exists() && file.isFile()){
			return true;
		}
		return false;
	}
	/**
	 * ��ȡ�ļ���չ��
	 * @param fileName
	 * @return string�ļ���չ��
	 */
	public static String getExtendName(String fileName){
		int index = fileName.lastIndexOf(".");
		if(index!=-1){
			return fileName.substring(index+1);
		}
		return null;
	}
	/**
	 * ����ļ��б�
	 * @param path
	 * @param filterName
	 * @return list
	 */
	public static List<String> getFileList(String path,List<String> filterName){
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		String[] fileList = file.list();
		//����������(����)
		for(int i=fileList.length-1;i>=0;i--){
			String extendName = getExtendName(fileList[i]);
			if(filterName.contains(extendName)){
				list.add(fileList[i]);
			}
		}
		return list;
	}
	/**
	* ɾ���ļ����������ļ����ļ���
	* @param fileName Ҫɾ�����ļ���
	* @return ɾ���ɹ�����true�����򷵻�false
	*/
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if(!file.exists()) {
			return false;
		} 
		else {
			if(file.isFile()){
			 return deleteFile(fileName);
			}
			else{
			 return deleteDirectory(fileName);
			}
		}
	}
	/**
	* ɾ�������ļ�
	* @param fileName Ҫɾ�����ļ����ļ���
	* @return �����ļ�ɾ���ɹ�����true�����򷵻�false
	*/
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if(file.exists() && file.isFile()) {
			if(file.delete()) {
				return true;
			} 
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	* ɾ��Ŀ¼��Ŀ¼�µ��ļ�
	* @param dir Ҫɾ����Ŀ¼���ļ�·��
	* @return Ŀ¼ɾ���ɹ�����true�����򷵻�false
	*/
	public static boolean deleteDirectory(String dir) {
		// ���dir�����ļ��ָ�����β���Զ�����ļ��ָ���
		if(!dir.endsWith(File.separator)){
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if((!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;
		// ɾ���ļ����е������ļ�������Ŀ¼
		File[] files = dirFile.listFiles();
		for(int i = 0; i < files.length; i++) {
			// ɾ�����ļ�
			if(files[i].isFile()) {
				flag = FileUtil.deleteFile(files[i].getAbsolutePath());
				if(!flag){
				  break;
				}
			}
			// ɾ����Ŀ¼
			else if(files[i].isDirectory()) {
				flag = FileUtil.deleteDirectory(files[i].getAbsolutePath());//�ݹ����
				if(!flag){
				  break;
				}
			}
		}
		if(!flag) {
			return false;
		}
		//ɾ����ǰĿ¼
		if(dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}}
