package src.com.server.hiber;

/**
 * AbstractItemlist entity provides the base persistence definition of the
 * Itemlist entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractItemlist implements java.io.Serializable {

	// Fields

	private Integer id;
	private Store store;
	private Items itemsByItemsId;
	private Items itemsByGiftId;
	private Integer stock;
	private float discount;
	private Integer giftNum;
	private boolean state;
	private Integer number;

	// Constructors

	/** default constructor */
	public AbstractItemlist() {
	}

	/** minimal constructor */
	public AbstractItemlist(Store store, Items itemsByItemsId, Integer stock,
			float discount, boolean state, Integer number) {
		this.store = store;
		this.itemsByItemsId = itemsByItemsId;
		this.stock = stock;
		this.discount = discount;
		this.state = state;
		this.number = number;
	}

	/** full constructor */
	public AbstractItemlist(Store store, Items itemsByItemsId,
			Items itemsByGiftId, Integer stock, float discount,
			Integer giftNum, boolean state, Integer number) {
		this.store = store;
		this.itemsByItemsId = itemsByItemsId;
		this.itemsByGiftId = itemsByGiftId;
		this.stock = stock;
		this.discount = discount;
		this.giftNum = giftNum;
		this.state = state;
		this.number = number;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Items getItemsByItemsId() {
		return this.itemsByItemsId;
	}

	public void setItemsByItemsId(Items itemsByItemsId) {
		this.itemsByItemsId = itemsByItemsId;
	}

	public Items getItemsByGiftId() {
		return this.itemsByGiftId;
	}

	public void setItemsByGiftId(Items itemsByGiftId) {
		this.itemsByGiftId = itemsByGiftId;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public float getDiscount() {
		return this.discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Integer getGiftNum() {
		return this.giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	public boolean getState() {
		return this.state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}