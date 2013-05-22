package fish.maintainer.orders;

import java.sql.Date;
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
import src.com.server.hiber.Orderdetails;
import src.com.server.hiber.Orders;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class StatisticsAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<StaBean> sta_list ;
	private StaBean curr ;
	private List<Store> store_list ;
	private java.sql.Date now ;
	private java.sql.Date beginDate ;
	private java.sql.Date endDate ;
	private String beginDateStr ;
	private String endDateStr ;
	private int store_id ;
	private String storeDefault ;
	
	private void getData() throws ParseException {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		store_cri.add(Restrictions.and(Restrictions.ne("name", "superDepartment"), Restrictions.ne("name", "server"))) ;
		store_list = store_cri.list() ;
		
		sta_list = new LinkedList<StaBean>() ;
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
	    
	    Iterator<Store> store_it ;
	    List<Store> singleStore = new LinkedList<Store>() ;
	    singleStore.add((Store) se.load(Store.class, store_id)) ;
	    if(store_id == 0)
	    {
	    	storeDefault = "所有店铺" ;
	    	store_it = store_list.iterator() ;
	    }
	    else
	    {
	    	storeDefault = ((Store)se.load(Store.class, store_id)).getName() ;
	    	store_it = singleStore.iterator() ;
	    }
	    
	    float totalGPrice ;
		float totalRPrice ;
		while(store_it.hasNext())
		{
			totalGPrice = 0 ;
			totalRPrice = 0 ;
			Store currStore = store_it.next() ;
			curr = new StaBean() ;
			curr.setStoreName(currStore.getName()) ;
			Criteria order_cri = se.createCriteria(Orders.class) ;
			order_cri.add(Restrictions.and(Restrictions.eq("storeName", currStore.getName()), Restrictions.between("time", beginDate, endDate))) ;
			if(order_cri.list().size() == 0)
				continue ;
			
			Iterator<Orders> order_it = order_cri.list().iterator() ;
			while(order_it.hasNext())
			{
				Orders currOrder = order_it.next() ;
				totalGPrice += currOrder.getTotalPrice() ;
				totalRPrice += currOrder.getProfit() ;
			}
			curr.setgProfit(totalGPrice) ;
			curr.setrProfit(totalRPrice) ;
			sta_list.add(curr) ;
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
		request.setAttribute("storeDefault", storeDefault) ;
		if(sta_list.isEmpty())
			this.addActionMessage("您查询的信息不存在") ;
	}
	
	public String execute() throws Exception{
		request.setAttribute("stalist", sta_list) ;
		return SUCCESS;
	}
	
	public String getStoreDefault() {
		return storeDefault;
	}

	public void setStoreDefault(String storeDefault) {
		this.storeDefault = storeDefault;
	}

	public List<StaBean> getSta_list() {
		return sta_list;
	}

	public void setSta_list(List<StaBean> sta_list) {
		this.sta_list = sta_list;
	}

	public List<Store> getStore_list() {
		return store_list;
	}

	public void setStore_list(List<Store> store_list) {
		this.store_list = store_list;
	}

	public java.sql.Date getNow() {
		return now;
	}

	public void setNow(java.sql.Date now) {
		this.now = now;
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

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
