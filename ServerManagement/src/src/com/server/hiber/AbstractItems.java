package src.com.server.hiber;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractItems entity provides the base persistence definition of the Items
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractItems implements java.io.Serializable {

	// Fields

	private Integer id;
	private Category category;
	private String name;
	private Double price;
	private Set itemlistsForGiftId = new HashSet(0);
	private Set itemlistsForItemsId = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractItems() {
	}

	/** minimal constructor */
	public AbstractItems(Category category, String name, Double price) {
		this.category = category;
		this.name = name;
		this.price = price;
	}

	/** full constructor */
	public AbstractItems(Category category, String name, Double price,
			Set itemlistsForGiftId, Set itemlistsForItemsId) {
		this.category = category;
		this.name = name;
		this.price = price;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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