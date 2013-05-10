package fish.maintainer.items;

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
	
	private void alter() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Itemlist currList = (Itemlist)se.load(Itemlist.class, alterListId) ;
		if(!(alterListDis == null || alterListDis.isEmpty()))
		{
			float tempNum = Float.parseFloat(alterListDis) ;
			tempNum = (float)(Math.round(tempNum * 100)) / 100 ;
			currList.setDiscount(tempNum) ;
		}
		
		if(alterListGift != 0)
		{
			Items gift = (Items)se.load(Items.class, alterListGift) ;
			currList.setItemsByGiftId(gift) ;
			if(alterListGiftNum == null || alterListGiftNum.isEmpty() || alterListGiftNum.equals("0"))
				currList.setGiftNum(1) ;
			else
				currList.setGiftNum(Integer.getInteger(alterListGiftNum)) ;
		}
		else
		{
			currList.setGiftNum(0) ;
			currList.setItemsByGiftId(null) ;
		}

		se.update(currList) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception {
		alter() ;
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
