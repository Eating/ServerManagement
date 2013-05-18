package fish.maintainer.orders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Items;
import src.com.server.hiber.Orderdetails;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;


public class StaItemsAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<StaItemBean> staItem_list ;
	private StaItemBean curr ;
	private List<Store> store_list ;
	private java.sql.Date now ;
	private java.sql.Date beginDate ;
	private java.sql.Date endDate ;
	private String beginDateStr ;
	private String endDateStr ;
	private int store_id ;
	private String storeName ;
	
	private void getData() throws ParseException {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		store_cri.add(Restrictions.and(Restrictions.ne("name", "superDepartment"), Restrictions.ne("name", "server"))) ;
		store_list = store_cri.list() ;
		staItem_list = new LinkedList<StaItemBean>() ;
		
		now = new java.sql.Date(new java.util.Date().getTime()) ;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); //制定日期格式
		Calendar c=Calendar.getInstance();
		java.util.Date date = new java.util.Date();
		c.setTime(date);
		c.add(Calendar.MONTH, -1); //将当前日期加一个月
		String beforeStr = df.format(c.getTime());  //返回String型的时间
		if(beginDateStr == null || beginDateStr.isEmpty())
	    	beginDateStr = beforeStr ;
	    if(endDateStr == null || endDateStr.isEmpty())
	    	endDateStr = df.format(now) ;
	    java.util.Date tempdate = df.parse(beginDateStr);  
	    beginDate = new java.sql.Date(tempdate.getTime());
	    java.util.Date tempdate2 = df.parse(endDateStr);  
	    endDate = new java.sql.Date(tempdate2.getTime());
	    
	    Criteria item_cri = se.createCriteria(Items.class) ;
	    List<Items> itemlist = item_cri.list() ;
	    Store currStore ;
	    if(store_id == 0)
	    	storeName = "所有店铺" ;
	    else
	    {
	    	currStore = (Store)se.load(Store.class, store_id) ;
	    	storeName = currStore.getName() ;
	    }
	    Iterator<Items> item_it = itemlist.iterator() ;
	    while(item_it.hasNext())
	    {
	    	Items currItem = item_it.next() ;
	    	int tempNum = 0 ;
	    	float tempPrice = 0 ;
	    	curr = new StaItemBean(currItem.getName()) ;
	    	Criteria detail_cri = se.createCriteria(Orderdetails.class) ;
	    	List<Orderdetails> detail_list ;
	    	detail_cri.add(Restrictions.eq("itemName", currItem.getName())) ;
		    if(store_id == 0)
		    	detail_list = detail_cri.list() ;
		    else
		    {
		    	List<Orderdetails> tempDetail = detail_cri.list() ;
		    	detail_list = new LinkedList<Orderdetails>() ;
		    	currStore = (Store)se.load(Store.class, store_id) ;
		    	Iterator<Orderdetails> d_it = tempDetail.iterator() ;
		    	while(d_it.hasNext())
		    	{
		    		Orderdetails currD = d_it.next() ;
		    		if(currStore.getName().equals(currD.getOrders().getStoreName()))
		    			detail_list.add(currD) ;
		    	}
		    }
		    
	    	if(detail_list.isEmpty())
	    		continue ;
	    	Iterator<Orderdetails> detail_it = detail_list.iterator() ;
	    	while(detail_it.hasNext())
	    	{
	    		Orderdetails currdetail = detail_it.next() ;
	    		tempNum += currdetail.getNumber() ;
	    		tempPrice += currdetail.getTotalPrice() ;
	    	}
	    	curr.setItemNum(tempNum) ;
	    	curr.setTotalPrice(tempPrice) ;
	    	
	    	staItem_list.add(curr) ;
		   
	    }
		
		se.close() ;
	}
	
	public void validate() {
		try {
			getData() ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("storelist", store_list) ;
		request.setAttribute("beginDefault", beginDateStr) ;
		request.setAttribute("endDefault", endDateStr) ;
		if(staItem_list.isEmpty())
			this.addActionMessage("您查询的信息不存在") ;
	}
	
	public String execute() throws Exception{
		request.setAttribute("staItemList", staItem_list) ;
		request.setAttribute("storeName", storeName) ;
		return SUCCESS;
	}
	
	public List<StaItemBean> getStaItem_list() {
		return staItem_list;
	}

	public void setStaItem_list(List<StaItemBean> staItem_list) {
		this.staItem_list = staItem_list;
	}

	public List<Store> getStore_list() {
		return store_list;
	}

	public void setStore_list(List<Store> store_list) {
		this.store_list = store_list;
	}

	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public String getBeginDateStr() {
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

}
