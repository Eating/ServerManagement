package test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;

public class ToBackup extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private String path;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public final String BACKUP_COMMAND = "mysqldump";
	public final String ENCODING = "utf8";
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void validate(){
		String dbName = "android_final";
		System.out.println("path" + path);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String date = df.format(new Date());
		System.out.println(date+"aaaaaa");
		String bakName = path+dbName+date+".bak";
		System.out.println(bakName);
		if(backup(bakName))
			try {
				response.sendRedirect("BackupSuccess.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	public boolean backup(String file){
		boolean isSuccess = true;
		try{
			Runtime rt = Runtime.getRuntime();
			String backupStr = this.getBackupStr();
			Process process = rt.exec(new String[] { "cmd.exe", "/c", backupStr });
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), ENCODING));
			String inStr = "";
			StringBuffer sb = new StringBuffer("");
			 while ((inStr = br.readLine()) !=null) {   
				              sb.append(inStr).append("");   
				          }   
				          String outStr = sb.toString();   
				   
				          OutputStreamWriter writer =new OutputStreamWriter(new FileOutputStream(file), ENCODING);   
				          writer.write(outStr);   
				          writer.flush();   
				   
				          br.close();   
				          writer.close();   
				      }catch (Exception e) {   
				          e.printStackTrace();   
				          isSuccess =false;   
				      }   
				     return isSuccess;  
	}
	private String getBackupStr() {   
		     String backupStr = BACKUP_COMMAND +" -u" + "root" +" -p" + "1234" +" -h" + "localhost" +" --set-charset=" + "utf8" +" " + "android_final";   
			    return backupStr;   
			}   

	
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response ;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	

}
