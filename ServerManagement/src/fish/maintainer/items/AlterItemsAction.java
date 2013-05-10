package fish.maintainer.items;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class AlterItemsAction extends ActionSupport {
	private String alterItemsName ;
	private int alterItemsCate ;
	private String alterItemsPrice ;
	private String alterPurchasePrice ;
	private int alterItemsId ;
	
	//缺少商品重名的提示
	private void alter() throws UnsupportedEncodingException {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Items currItem = (Items)se.load(Items.class, alterItemsId) ;
		if(!(alterItemsName == null || alterItemsName.isEmpty()))
		{
			String tempName = new String(alterItemsName.getBytes("ISO-8859-1"),"UTF-8") ;
			currItem.setName(tempName) ;
		}
		if(alterItemsCate != currItem.getCategory().getId())
		{	Category tempCate = (Category)se.load(Category.class, alterItemsCate) ;
			currItem.setCategory(tempCate) ;
		}
		
		if(!(alterItemsPrice == null || alterItemsPrice.isEmpty()))
		{
			//保留2位小数
			float tempPrice = Float.parseFloat(alterItemsPrice) ;
			tempPrice = (float)(Math.round(tempPrice * 100)) / 100 ;
			currItem.setPrice(tempPrice) ;
		}
		
		if(!(alterPurchasePrice == null || alterPurchasePrice.isEmpty()))
		{
			//保留2位小数
			float tempPrice2 = Float.parseFloat(alterPurchasePrice) ;
			tempPrice2 = (float)(Math.round(tempPrice2 * 100)) / 100 ;
			currItem.setPurchasePrice(tempPrice2) ;
		}
		se.update(currItem) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception {
		alter() ;
		return SUCCESS;
	}
	
	public String getAlterItemsName() {
		return alterItemsName;
	}

	public void setAlterItemsName(String alterItemsName) {
		this.alterItemsName = alterItemsName;
	}

	public int getAlterItemsCate() {
		return alterItemsCate;
	}

	public void setAlterItemsCate(int alterItemsCate) {
		this.alterItemsCate = alterItemsCate;
	}

	public String getAlterItemsPrice() {
		return alterItemsPrice;
	}

	public void setAlterItemsPrice(String alterItemsPrice) {
		this.alterItemsPrice = alterItemsPrice;
	}

	public int getAlterItemsId() {
		return alterItemsId;
	}

	public void setAlterItemsId(int alterItemsId) {
		this.alterItemsId = alterItemsId;
	}

	public String getAlterPurchasePrice() {
		return alterPurchasePrice;
	}

	public void setAlterPurchasePrice(String alterPurchasePrice) {
		this.alterPurchasePrice = alterPurchasePrice;
	}

}
