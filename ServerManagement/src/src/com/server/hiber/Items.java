package src.com.server.hiber;

import java.util.HashSet;
import java.util.Set;

/**
 * Items entity. @author MyEclipse Persistence Tools
 */

public class Items implements java.io.Serializable {

	// Fields

	private Integer id;
	private Category category;
	private String name;
	private Float price;
	private Float purchasePrice;
	private Set itemlistsForGiftId = new HashSet(0);
	private Set itemlistsForItemsId = new HashSet(0);

	// Constructors

	/** default constructor */
	public Items() {
	}

	/** minimal constructor */
	public Items(Category category, String name, Float price,
			Float purchasePrice) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.purchasePrice = purchasePrice;
	}

	/** full constructor */
	public Items(Category category, String name, Float price,
			Float purchasePrice, Set itemlistsForGiftId, Set itemlistsForItemsId) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.purchasePrice = purchasePrice;
		this.itemlistsForGiftId = itemlistsForGiftId;
		this.itemlistsForItemsId = itemlistsForItemsId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(Float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Set getItemlistsForGiftId() {
		return this.itemlistsForGiftId;
	}

	public void setItemlistsForGiftId(Set itemlistsForGiftId) {
		this.itemlistsForGiftId = itemlistsForGiftId;
	}

	public Set getItemlistsForItemsId() {
		return this.itemlistsForItemsId;
	}

	public void setItemlistsForItemsId(Set itemlistsForItemsId) {
		this.itemlistsForItemsId = itemlistsForItemsId;
	}

}