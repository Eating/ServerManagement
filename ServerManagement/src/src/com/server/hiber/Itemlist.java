package src.com.server.hiber;

/**
 * Itemlist entity. @author MyEclipse Persistence Tools
 */

public class Itemlist implements java.io.Serializable {

	// Fields

	private Integer id;
	private Store store;
	private Items itemsByItemsId;
	private Items itemsByGiftId;
	private Integer stock;
	private Float discount;
	private Integer giftNum;
	private Boolean state;
	private Integer number;

	// Constructors

	/** default constructor */
	public Itemlist() {
	}

	/** minimal constructor */
	public Itemlist(Store store, Items itemsByItemsId, Integer stock,
			Float discount, Boolean state, Integer number) {
		this.store = store;
		this.itemsByItemsId = itemsByItemsId;
		this.stock = stock;
		this.discount = discount;
		this.state = state;
		this.number = number;
	}

	/** full constructor */
	public Itemlist(Store store, Items itemsByItemsId, Items itemsByGiftId,
			Integer stock, Float discount, Integer giftNum, Boolean state,
			Integer number) {
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

	public Float getDiscount() {
		return this.discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getGiftNum() {
		return this.giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}