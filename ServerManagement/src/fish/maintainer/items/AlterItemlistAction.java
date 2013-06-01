package fish.maintainer.items;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class AlterItemlistAction extends ActionSupport {
	private String alterListDis ;
	private int alterListGift ;
	private String alterListGiftNum ;
	private int alterListId ;
	
	//折扣默认应为1，无赠品时赠品数量为0
	private boolean alter() {
		if(alterListDis == null || alterListDis.isEmpty())
			return false ;
		if(alterListGiftNum == null || alterListGiftNum.isEmpty())
			return false ;
		
		Pattern pattern = Pattern.compile("^[0,1].?[0-9]*$");
	    if(!pattern.matcher(alterListDis).matches())
	    	return false ;
	    
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Itemlist currList = (Itemlist)se.load(Itemlist.class, alterListId) ;
		float tempNum = Float.parseFloat(alterListDis) ;
		tempNum = (float)(Math.round(tempNum * 100)) / 100 ;
		if(tempNum > 1 || tempNum <= 0)
			return false ;
		
		currList.setDiscount(tempNum) ;	
		
		if(alterListGift != 0)
		{
			Items gift = (Items)se.load(Items.class, alterListGift) ;
			currList.setItemsByGiftId(gift) ;
			Pattern pattern2 = Pattern.compile("^[1-9][0-9]*$");
			if(alterListGiftNum.equals("0"))
				currList.setGiftNum(1) ;
			else if(!pattern2.matcher(alterListGiftNum).matches())
			{
				se.close() ;
		    	return false ;
			}
			else
			{
				int newGirfNum = Integer.parseInt(alterListGiftNum) ;
				currList.setGiftNum(newGirfNum) ;
			}
		}
		else
		{
			currList.setGiftNum(0) ;
			currList.setItemsByGiftId(null) ;
		}

		se.update(currList) ;
		tran.commit() ;
		se.close() ;
		
		return true ;
	}
	
	public String execute() throws Exception {
		if(!alter())
			return "inputError" ;
		
		return SUCCESS;
	}
	
	public String getAlterListDis() {
		return alterListDis;
	}

	public void setAlterListDis(String alterListDis) {
		this.alterListDis = alterListDis;
	}

	public int getAlterListGift() {
		return alterListGift;
	}

	public void setAlterListGift(int alterListGift) {
		this.alterListGift = alterListGift;
	}

	public String getAlterListGiftNum() {
		return alterListGiftNum;
	}

	public void setAlterListGiftNum(String alterListGiftNum) {
		this.alterListGiftNum = alterListGiftNum;
	}

	public int getAlterListId() {
		return alterListId;
	}

	public void setAlterListId(int alterListId) {
		this.alterListId = alterListId;
	}

}
