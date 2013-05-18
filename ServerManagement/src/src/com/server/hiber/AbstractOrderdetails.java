package src.com.server.hiber;

/**
 * AbstractOrderdetails entity provides the base persistence definition of the
 * Orderdetails entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrderdetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private Orders orders;
	private float discount;
	private float singlePrice;
	private float totalPrice;
	private Integer number;
	private String itemName;

	// Constructors

	/** default constructor */
	public AbstractOrderdetails() {
	}

	/** minimal constructor */
	public AbstractOrderdetails(Orders orders, float singlePrice,
			float totalPrice, Integer number, String itemName) {
		this.orders = orders;
		this.singlePrice = singlePrice;
		this.totalPrice = totalPrice;
		this.number = number;
		this.itemName = itemName;
	}

	/** full constructor */
	public AbstractOrderdetails(Orders orders, float discount,
			float singlePrice, float totalPrice, Integer number, String itemName) {
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

	public float getDiscount() {
		return this.discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getSinglePrice() {
		return this.singlePrice;
	}

	public void setSinglePrice(float singlePrice) {
		this.singlePrice = singlePrice;
	}

	public float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
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