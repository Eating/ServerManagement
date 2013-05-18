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

public class SingleItemAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private String id ;
	private List<StaItemBean> staItem_list ;
	private StaItemBean curr ;
	
	private void getData() throws ParseException {
		Session se = HibernateSessionFactory.getSession() ;
		staItem_list = new LinkedList<StaItemBean>() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		store_cri.add(Restrictions.and(Restrictions.ne("name", "superDepartment"), Restrictions.ne("name", "server"))) ;
		Criteria detail_cri = se.createCriteria(Orderdetails.class) ;
		detail_cri.add(Restrictions.eq("itemName", id)) ;
		List<Orderdetails> detail_list = detail_cri.list() ;
		List<Store> store_list = store_cri.list() ;
		Iterator<Store> store_it = store_list.iterator() ;
		int tempNum ;
    	float tempPrice ;
		while(store_it.hasNext())
		{
			tempNum = 0 ;
			tempPrice = 0 ;
			Store currStore = store_it.next() ;
			Iterator<Orderdetails> detail_it = detail_list.iterator() ;
			while(detail_it.hasNext())
			{
				Orderdetails currD = detail_it.next() ;
				if(currD.getOrders().getStoreName().equals(currStore.getName()))
				{
					tempNum += currD.getNumber() ;
					tempPrice += currD.getTotalPrice() ;
				}
			}
			if(tempNum == 0)
				continue ;
			curr = new StaItemBean() ;
			curr.setStoreName(currStore.getName()) ;
			curr.setItemNum(tempNum) ;
			curr.setTotalPrice(tempPrice) ;
			staItem_list.add(curr) ;
		}
		
		se.close() ;
	}

	public String execute() throws Exception{
		getData() ;
		request.setAttribute("staItemList", staItem_list) ;
		request.setAttribute("itemName", id) ;
		return SUCCESS;
	}
	
	
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<StaItemBean> getStaItem_list() {
		return staItem_list;
	}

	public void setStaItem_list(List<StaItemBean> staItem_list) {
		this.staItem_list = staItem_list;
	}
	
}
