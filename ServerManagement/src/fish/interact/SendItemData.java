package fish.interact;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;
import src.com.server.hiber.Store;
import src.com.server.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class SendItemData extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private HttpServletRequest request ;
	private HttpServletResponse response ;
	private String itemId ;
	private String storeId ;
	
	public void sendData() throws IOException {
		this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("UTF-8");
		String curr = request.getParameter("itemId") ;
		System.out.println("test:  " + curr) ;
		Session se = HibernateSessionFactory.getSession() ;
		int idNum = Integer.parseInt(itemId) ;
		int storeNum = Integer.parseInt(storeId) ;
		Store currStore = (Store)se.load(Store.class, storeNum) ;
		JSONObject jsonObject = new JSONObject(); 
		Criteria item_cri = se.createCriteria(Items.class) ;
		item_cri.add(Restrictions.eq("id", idNum)) ;
		List<Items> item_list = item_cri.list() ;
		if(!item_list.isEmpty())
		{
			Items currItem = item_list.iterator().next() ;
			Criteria list_cri = se.createCriteria(Itemlist.class) ;
			list_cri.add(Restrictions.and(Restrictions.eq("itemsByItemsId", currItem), Restrictions.eq("store", currStore))) ;
			List<Itemlist> currList = list_cri.list() ;
			if(!currList.isEmpty())
			{
				Itemlist currItemlist = currList.iterator().next() ;
	        	jsonObject.put("itemName", currItem.getName()) ;
	        	jsonObject.put("itemSPrice", currItem.getPrice()) ;
	        	jsonObject.put("discount", currItemlist.getDiscount()) ;
	        	
	        	Items gift = currItemlist.getItemsByGiftId() ;
	        	if(gift != null)
	        	{
	        		jsonObject.put("giftName", gift.getName()) ;
	        		jsonObject.put("giftNum", currItemlist.getGiftNum()) ;
	        	}
		        
		        //把这个item信息写入OrderDetail,并且修改商品数量
	        	//完成时把信息写入order
			}
			else
	        	jsonObject.put("itemName", "null") ;
        }
        else
        	jsonObject.put("itemName", "null") ;
        	
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
	
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

	public void setServletResponse(HttpServletResponse resp) {
		response = resp ;
	}
}
