package fish.maintainer.items;

import src.com.server.hiber.Itemlist;

public class ItemlistBean {
	private String name ;
	private String category ;
	private float price ;
	private int number ;
	private int stock ;
	private float discount ;
	private int gift_id ;
	private int giftNum ;
	private String giftName ;
	private String store ;
	private int id ;
	
	public ItemlistBean(Itemlist curr)
	{
		name = curr.getItemsByItemsId().getName() ;
		category = curr.getItemsByItemsId().getCategory().getName() ;
		price = curr.getItemsByItemsId().getPrice() ;
		number = curr.getNumber() ;
		stock = curr.getStock() ;
		discount = curr.getDiscount() ;
		store = curr.getStore().getName() ;
		id = curr.getId() ;
		if(curr.getItemsByGiftId() != null)
		{
			gift_id = curr.getItemsByGiftId().getId() ;
			if(curr.getGiftNum() == null)
				curr.setGiftNum(1) ;
			giftNum = curr.getGiftNum() ;
			giftName = curr.getItemsByGiftId().getName() ;
		}
		else
		{
			gift_id = 0 ;
			giftNum = 0 ;
			giftName = "ÎÞÔùÆ·" ;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getGift_id() {
		return gift_id;
	}

	public void setGift_id(int gift_id) {
		this.gift_id = gift_id;
	}

	public int getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(int giftNum) {
		this.giftNum = giftNum;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	
}
