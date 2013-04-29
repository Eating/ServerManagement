package src.com.server.hiber;

import java.util.HashSet;
import java.util.Set;

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
	private Double discount;
	private Integer giftNum;
	private Boolean state;
	private Set orderdetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractItemlist() {
	}

	/** minimal constructor */
	public AbstractItemlist(Store store, Items itemsByItemsId, Integer stock,
			Double discount, Boolean state) {
		this.store = store;
		this.itemsByItemsId = itemsByItemsId;
		this.stock = stock;
		this.discount = discount;
		this.state = state;
	}

	/** full constructor */
	public AbstractItemlist(Store store, Items itemsByItemsId,
			Items itemsByGiftId, Integer stock, Double discount,
			Integer giftNum, Boolean state, Set orderdetailses) {
		this.store = store;
		this.itemsByItemsId = itemsByItemsId;
		this.itemsByGiftId = itemsByGiftId;
		this.stock = stock;
		this.discount = discount;
		this.giftNum = giftNum;
		this.state = state;
		this.orderdetailses = orderdetailses;
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

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
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

	public Set getOrderdetailses() {
		return this.orderdetailses;
	}

	public void setOrderdetailses(Set orderdetailses) {
		this.orderdetailses = orderdetailses;
	}

}