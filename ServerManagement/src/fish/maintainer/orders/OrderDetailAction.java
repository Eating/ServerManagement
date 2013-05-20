package fish.maintainer.orders;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Orderdetails;
import src.com.server.hiber.Orders;

import com.opensymphony.xwork2.ActionSupport;

public class OrderDetailAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private int id ;
	private Orders currOrder ;
	private Set<Orderdetails> detail_list ;
	
	private void getDate() {
		Session se = HibernateSessionFactory.getSession() ;
		currOrder = (Orders)se.load(Orders.class, id) ;
		detail_list = currOrder.getOrderdetailses() ;
		Iterator<Orderdetails> it = detail_list.iterator() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		getDate() ;
		request.setAttribute("detaillist", detail_list) ;
		request.setAttribute("orderId", id) ;
		return SUCCESS;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Orderdetails> getDetail_list() {
		return detail_list;
	}

	public void setDetail_list(Set<Orderdetails> detail_list) {
		this.detail_list = detail_list;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

}
