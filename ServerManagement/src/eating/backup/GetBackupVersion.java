package eating.backup;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetBackupVersion {
	private static String dbName = "android_final";
	private static String path = "C://Canjie//";
	private List<String> list;
	public String getExtendName(String fileName){
		int index = fileName.lastIndexOf(".");
		if(index!=-1){
			return fileName.substring(index+1);
		}
		return null;
	}
	public List<String> getFileList(String path,List<String> filterName){
		File file = new File(path);
		List<String> listOfFile = new ArrayList<String>();
		String[] fileList = file.list();
		//按名称排序(降序)
		for(int i=fileList.length-1;i>=0;i--){
			String extendName = getExtendName(fileList[i]);
			if(filterName.contains(extendName)){
				listOfFile.add(fileList[i]);
			}
		}
		return listOfFile;
	}
	public List<String> getVersionList(){
		
		//设置过滤器
		List<String> listOfVersion = new ArrayList<String>();
		List<String> filterName = new ArrayList<String>();
		filterName.add("sql");
		list = getFileList(path, filterName);
		if(list==null || list.size()==0){
			return listOfVersion;
		}else{
			//去掉list里面的扩展名和数据库名
			int dbLength = dbName.length();
			for(int j=0;j<list.size();j++){
				String temp = list.get(j);
				listOfVersion.add(temp.substring(dbLength,temp.length()-4));
			}
			return listOfVersion;
		}
	}

}
