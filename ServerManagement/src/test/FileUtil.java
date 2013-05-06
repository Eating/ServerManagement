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
 * 文件操作类
 * @author YinZhiwei
*
 */
public class FileUtil {
	/**
	 * 查找文件是否存在
	 * @param fileName(文件名，包含文件路径和扩展名)
	 */
	public static boolean checkFile(String fileName){
		File file = new File(fileName);
		if(file.exists() && file.isFile()){
			return true;
		}
		return false;
	}
	/**
	 * 获取文件扩展名
	 * @param fileName
	 * @return string文件扩展名
	 */
	public static String getExtendName(String fileName){
		int index = fileName.lastIndexOf(".");
		if(index!=-1){
			return fileName.substring(index+1);
		}
		return null;
	}
	/**
	 * 获得文件列表
	 * @param path
	 * @param filterName
	 * @return list
	 */
	public static List<String> getFileList(String path,List<String> filterName){
		File file = new File(path);
		List<String> list = new ArrayList<String>();
		String[] fileList = file.list();
		//按名称排序(降序)
		for(int i=fileList.length-1;i>=0;i--){
			String extendName = getExtendName(fileList[i]);
			if(filterName.contains(extendName)){
				list.add(fileList[i]);
			}
		}
		return list;
	}
	/**
	* 删除文件，可以是文件或文件夹
	* @param fileName 要删除的文件名
	* @return 删除成功返回true，否则返回false
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
	* 删除单个文件
	* @param fileName 要删除的文件的文件名
	* @return 单个文件删除成功返回true，否则返回false
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
	* 删除目录及目录下的文件
	* @param dir 要删除的目录的文件路径
	* @return 目录删除成功返回true，否则返回false
	*/
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if(!dir.endsWith(File.separator)){
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		if((!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for(int i = 0; i < files.length; i++) {
			// 删除子文件
			if(files[i].isFile()) {
				flag = FileUtil.deleteFile(files[i].getAbsolutePath());
				if(!flag){
				  break;
				}
			}
			// 删除子目录
			else if(files[i].isDirectory()) {
				flag = FileUtil.deleteDirectory(files[i].getAbsolutePath());//递归调用
				if(!flag){
				  break;
				}
			}
		}
		if(!flag) {
			return false;
		}
		//删除当前目录
		if(dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}}
