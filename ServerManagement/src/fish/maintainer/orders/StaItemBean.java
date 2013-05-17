package fish.maintainer.orders;

public class StaItemBean {
	private String itemName ;
	private int itemNum ;
	private float totalPrice ;
	private String storeName ;
	
	public StaItemBean(String currName) {
		itemName = currName ;
	}
	
	public StaItemBean() {
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
