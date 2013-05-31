package fish.maintainer.stock;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;

import com.opensymphony.xwork2.ActionSupport;

public class AlterStockAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private String stockInNum ;
	private String stockOutNum ;
	private int alterStockId ;
	
	private boolean alter() {
		if(stockInNum == null || stockInNum.isEmpty())
			stockInNum = "0" ;
		if(stockOutNum == null || stockOutNum.isEmpty())
			stockOutNum = "0" ;
		Session se = HibernateSessionFactory.getSession() ;
		Itemlist currlist = (Itemlist)se.load(Itemlist.class, alterStockId) ;
		if(Integer.parseInt(stockOutNum) > Integer.MAX_VALUE)
			return false ;
		if(Integer.parseInt(stockInNum) < 0 || Integer.parseInt(stockInNum) > Integer.MAX_VALUE)
			return false ;
		if(Integer.parseInt(stockOutNum) < 0 || Integer.parseInt(stockOutNum) > currlist.getStock())
			return false ;
		else
		{
			Transaction tran = se.beginTransaction() ;
			tran.begin() ;
			if(!stockInNum.equals("0"))
				currlist.setStock(currlist.getStock() + Integer.parseInt(stockInNum)) ;
			if(!stockOutNum.equals("0"))
			{
				currlist.setStock(currlist.getStock() - Integer.parseInt(stockOutNum)) ;
				currlist.setNumber(currlist.getNumber() + Integer.parseInt(stockOutNum)) ;
			}
			se.update(currlist) ;
			tran.commit() ;
			se.close() ;
			return true ;
		}
	}
	
	public String execute() throws Exception {
		if(!alter())
			return "inputError" ;
		return SUCCESS;
	}

	public String getStockInNum() {
		return stockInNum;
	}

	public void setStockInNum(String stockInNum) {
		this.stockInNum = stockInNum;
	}

	public String getStockOutNum() {
		return stockOutNum;
	}

	public void setStockOutNum(String stockOutNum) {
		this.stockOutNum = stockOutNum;
	}

	public int getAlterStockId() {
		return alterStockId;
	}

	public void setAlterStockId(int alterStockId) {
		this.alterStockId = alterStockId;
	}
	
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
