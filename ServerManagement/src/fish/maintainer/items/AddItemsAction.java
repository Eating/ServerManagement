package fish.maintainer.items;

import java.io.UnsupportedEncodingException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class AddItemsAction extends ActionSupport {
	private String addItemsName ;
	private int addItemsCate ;
	private String addItemsPrice ;
	private String addPurchasePrice ;

	//缺少重名与名为空的提示
	boolean add() throws UnsupportedEncodingException {
		if(!(addItemsName == null || addItemsName.isEmpty()))
		{
			if(addItemsPrice == null || addItemsPrice.isEmpty())
				return false ;
			float tempPrice = Float.parseFloat(addItemsPrice) ;
			if(tempPrice <= 0)
				return false ;
			
			if(addPurchasePrice == null || addPurchasePrice.isEmpty())
				return false ;
			float tempPrice2 = Float.parseFloat(addPurchasePrice) ;
			if(tempPrice2 <= 0)
				return false ;
			
			if(addItemsCate == 0)
				return false ;
			
			Session se = HibernateSessionFactory.getSession() ;
			Transaction tran = se.beginTransaction() ;
			tran.begin() ;
			Items newItem = new Items() ;
			String tempName = new String(addItemsName.getBytes("ISO-8859-1"),"UTF-8") ;
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
		add() ;
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
