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
	
	private void getData() throws ParseException {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		Iterator<Store> store_it = store_cri.list().iterator() ;
		sta_list = new LinkedList<StaBean>() ;
		while(store_it.hasNext())
		{
			java.sql.Date now = new java.sql.Date(new java.util.Date().getTime()) ;
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); //制定日期格式
			Calendar c=Calendar.getInstance();
			java.util.Date date = new java.util.Date();
			c.setTime(date);
			c.add(Calendar.MONTH, -1); //将当前日期加一个月
			String validityDate = df.format(c.getTime());  //返回String型的时间
			java.util.Date date2 = df.parse(validityDate);  
		    java.sql.Date before = new java.sql.Date(date2.getTime());
			
			float totalGPrice = 0 ;
			float totalRPrice = 0 ;
			Store currStore = store_it.next() ;
			curr = new StaBean() ;
			curr.setStoreName(currStore.getName()) ;
			Criteria order_cri = se.createCriteria(Orders.class) ;
			order_cri.add(Restrictions.and(Restrictions.eq("storeName", currStore.getName()), Restrictions.between("time", before, now))) ;
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
		
	}
	
	public String execute() throws Exception{
		getData() ;
		request.setAttribute("stalist", sta_list) ;
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
