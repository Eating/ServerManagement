package eating.backup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class ToRecover extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private String versionToRecover;
	private String path;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public final String RECOVER_COMMAND = "mysql";
	public final String ENCODING = "utf8";
	private static String dbName = "android_final";
	public String getVersionToRecover() {
		return versionToRecover;
	}
	public void setVersionToRecover(String versionToRecover) {
		this.versionToRecover = versionToRecover;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void validate(){
		if(versionToRecover == null || versionToRecover.equals("")){
			this.addActionError("û��ѡ��ָ��汾");
		}
		else{
			String bakName = path + dbName + versionToRecover +".sql";
			System.out.println(bakName);
			if(recover(bakName)){
				try{
					System.out.println(bakName);
					response.sendRedirect("RecoverSuccess.jsp");
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		}
		
	}
	
	public boolean recover(String file){
	/*	try{
			Runtime rt = Runtime.getRuntime();
			String recoverStr = this.getRecoverStr();
			Process process = rt.exec(new String[] { "cmd.exe", "/c", recoverStr });
			  String inStr;   
			  StringBuffer sb =new StringBuffer("");   
              BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream(file), ENCODING));   
			  while ((inStr = br.readLine()) !=null) {
				  sb.append(inStr).append("");
				  }
			  String outStr = sb.toString();
			  OutputStreamWriter writer =new OutputStreamWriter(process.getOutputStream(), ENCODING);
			  writer.write(outStr);
			  writer.flush();
			  br.close();
			  writer.close();
			  }catch (Exception e) {
				  e.printStackTrace();
				  return false;
				  }*/
	   try {   
	    	  
	       
	        Runtime rt = Runtime.getRuntime();   
	  
	        // ���� mysql �� cmd:   
	        Process child = rt.exec("C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql.exe -u root -h localhost -p1234 android_final");   
	        OutputStream out = child.getOutputStream();//����̨��������Ϣ��Ϊ�����   
	        String inStr;   
	        StringBuffer sb = new StringBuffer("");   
	        String outStr;   
	        BufferedReader br = new BufferedReader(new InputStreamReader(   
	                new FileInputStream(file), "utf8"));   
	        while ((inStr = br.readLine()) != null) {   
	            sb.append(inStr + "\r\n");   
	        }   
	        outStr = sb.toString();   
	  
	        OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");   
	        writer.write(outStr);   
	        // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���   
	        writer.flush();   
	        // �����ǹر����������   
	        out.close();   
	        br.close();   
	        writer.close();   
	           
	  
	        System.out.println(" Load OK! ");   
	       
	    } catch (Exception e) {   
	        e.printStackTrace();   
	        System.out.println(" Load fail! ");   
	        return false; 
	    }   
		
		
		
		return true;   

	}
	public String getRecoverStr(){
		 String backupStr = RECOVER_COMMAND +" -u" + " root" +" -p" + "1234" +" -h" + " localhost" +" " + "android_final"; 
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
