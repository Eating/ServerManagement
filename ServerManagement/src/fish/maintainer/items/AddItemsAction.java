package fish.maintainer.items;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class AddItemsAction extends ActionSupport {
	private String addItemsName ;
	private int addItemsCate ;
	private String addItemsPrice ;
	private String addPurchasePrice ;

	boolean add() throws UnsupportedEncodingException {
		if(!(addItemsName == null || addItemsName.isEmpty()))
		{
			Pattern pattern = Pattern.compile("[0-9]+(.[0-9]+)?") ; 
		     
			if(addItemsPrice == null || addItemsPrice.isEmpty())
				return false ;
			if(!pattern.matcher(addItemsPrice).matches())
				return false ;
			float tempPrice = Float.parseFloat(addItemsPrice) ;
			if(tempPrice <= 0)
				return false ;
			
			if(addPurchasePrice == null || addPurchasePrice.isEmpty())
				return false ;
			if(!pattern.matcher(addPurchasePrice).matches())
				return false ;
			float tempPrice2 = Float.parseFloat(addPurchasePrice) ;
			if(tempPrice2 <= 0)
				return false ;
			if(tempPrice <= tempPrice2)
				return false ;
			if(addItemsCate == 0)
				return false ;
			
			String tempName = new String(addItemsName.getBytes("ISO-8859-1"),"UTF-8") ;
			Session se = HibernateSessionFactory.getSession() ;
			Criteria item_cri = se.createCriteria(Items.class) ;
			item_cri.add(Restrictions.eq("name", tempName)) ;
			if(!item_cri.list().isEmpty())
			{
				se.close() ;
				return false ;
			}
			
			Transaction tran = se.beginTransaction() ;
			tran.begin() ;
			Items newItem = new Items() ;
			newItem.setName(tempName) ;
			Category currCate = (Category)se.load(Category.class, addItemsCate) ;
			newItem.setCategory(currCate) ;
			//保留2位小数
			tempPrice = (float)(Math.round(tempPrice * 100)) / 100 ;
			newItem.setPrice(tempPrice) ;
			tempPrice2 = (float)(Math.round(tempPrice2 * 100)) / 100 ;
			newItem.setPurchasePrice(tempPrice2) ;
			
			se.save(newItem) ;
			tran.commit() ;
			se.close() ;
			return true ;
		}
		return false ;
	}
	
	public String execute() throws Exception {
		if(!add())
			return "inputError" ;
		return SUCCESS;
	}

	public String getAddItemsName() {
		return addItemsName;
	}

	public void setAddItemsName(String addItemsName) {
		this.addItemsName = addItemsName;
	}

	public int getAddItemsCate() {
		return addItemsCate;
	}

	public void setAddItemsCate(int addItemsCate) {
		this.addItemsCate = addItemsCate;
	}

	public String getAddItemsPrice() {
		return addItemsPrice;
	}

	public void setAddItemsPrice(String addItemsPrice) {
		this.addItemsPrice = addItemsPrice;
	}

	public String getAddPurchasePrice() {
		return addPurchasePrice;
	}

	public void setAddPurchasePrice(String addPurchasePrice) {
		this.addPurchasePrice = addPurchasePrice;
	}

}
