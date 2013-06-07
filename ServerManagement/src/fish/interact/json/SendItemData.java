package fish.interact.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import src.com.server.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class SendItemData extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private HttpServletRequest request ;
	private HttpServletResponse response ;
	private String itemId ;
	
	public void json() throws IOException {
		this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("UTF-8");
		String curr = request.getParameter("itemId") ;
		System.out.println("test:  " + curr) ;
		
        JSONObject jsonObject = new JSONObject();  
        jsonObject.put("test", "hello??");  
      
        try {  
            this.response.setCharacterEncoding("UTF-8");  
            this.response.getWriter().write(jsonObject.toString());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

	public void setServletResponse(HttpServletResponse resp) {
		response = resp ;
	}
}
