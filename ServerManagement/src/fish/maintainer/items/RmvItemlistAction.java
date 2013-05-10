package fish.maintainer.items;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;

import com.opensymphony.xwork2.ActionSupport;

public class RmvItemlistAction extends ActionSupport{
	private int rmvItemlistId ;
	
	//关于商品删除后，历史订单如何引用此商品，待讨论
	private void remove() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Itemlist rmvItemlist = (Itemlist)se.load(Itemlist.class, rmvItemlistId) ;
		se.delete(rmvItemlist) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception {
		remove() ;
		return SUCCESS;
	}
	
	public int getRmvItemlistId() {
		return rmvItemlistId;
	}

	public void setRmvItemlistId(int rmvItemlistId) {
		this.rmvItemlistId = rmvItemlistId;
	}	
}
