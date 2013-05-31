package fish.maintainer.items;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
	
	private boolean alter() throws UnsupportedEncodingException {
		Pattern pattern = Pattern.compile("[0-9]+(.[0-9]+)?"); 
		if(alterItemsName == null || alterItemsName.isEmpty())
			return false ;
		if(alterItemsPrice == null || alterItemsPrice.isEmpty())
			return false ;
		if(!pattern.matcher(alterItemsPrice).matches())
			return false ;
		if(alterPurchasePrice == null || alterPurchasePrice.isEmpty())
			return false ;
		if(!pattern.matcher(alterPurchasePrice).matches())
			return false ;
		if(alterItemsCate == 0 )
			return false ;
		
		String tempName = new String(alterItemsName.getBytes("ISO-8859-1"),"UTF-8") ;
		Session se = HibernateSessionFactory.getSession() ;
		Items currItem = (Items)se.load(Items.class, alterItemsId) ;
		if(!currItem.getName().equals(tempName))
		{
			Criteria item_cri = se.createCriteria(Items.class) ;
			item_cri.add(Restrictions.eq("name", tempName)) ;
			List<Items> tempL = item_cri.list() ;
			if(!tempL.isEmpty())
			{
				se.close() ;
				return false ;
			}
		}
		//保留2位小数
		float tempPrice = Float.parseFloat(alterItemsPrice) ;
		tempPrice = (float)(Math.round(tempPrice * 100)) / 100 ;
		if(tempPrice <= 0)
			return false ;
		float tempPrice2 = Float.parseFloat(alterPurchasePrice) ;
		tempPrice2 = (float)(Math.round(tempPrice2 * 100)) / 100 ;
		if(tempPrice2 <= 0)
			return false ;
		if(tempPrice <= tempPrice2)
			return false ;
		
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		currItem.setName(tempName) ;
		if(alterItemsCate != currItem.getCategory().getId())
		{	Category tempCate = (Category)se.load(Category.class, alterItemsCate) ;
			currItem.setCategory(tempCate) ;
		}
		
		currItem.setPrice(tempPrice) ;
		currItem.setPurchasePrice(tempPrice2) ;
		se.update(currItem) ;
		tran.commit() ;
		se.close() ;
		
		return true ;
	}
	
	public String execute() throws Exception {
		if(!alter())
			return "inputError" ;
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
