package src.com.server.hiber;

/**
 * Orderdetails entity. @author MyEclipse Persistence Tools
 */

public class Orderdetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private Orders orders;
	private Float discount;
	private Float singlePrice;
	private Float totalPrice;
	private Integer number;
	private String itemName;

	// Constructors

	/** default constructor */
	public Orderdetails() {
	}

	/** minimal constructor */
	public Orderdetails(Orders orders, Float singlePrice, Float totalPrice,
			Integer number, String itemName) {
		this.orders = orders;
		this.singlePrice = singlePrice;
		this.totalPrice = totalPrice;
		this.number = number;
		this.itemName = itemName;
	}

	/** full constructor */
	public Orderdetails(Orders orders, Float discount, Float singlePrice,
			Float totalPrice, Integer number, String itemName) {
		this.orders = orders;
		this.discount = discount;
		this.singlePrice = singlePrice;
		this.totalPrice = totalPrice;
		this.number = number;
		this.itemName = itemName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Float getDiscount() {
		return this.discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getSinglePrice() {
		return this.singlePrice;
	}

	public void setSinglePrice(Float singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}