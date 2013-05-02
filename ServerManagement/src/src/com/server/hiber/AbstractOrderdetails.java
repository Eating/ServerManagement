package src.com.server.hiber;

/**
 * AbstractOrderdetails entity provides the base persistence definition of the
 * Orderdetails entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrderdetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private Orders orders;
	private Itemlist itemlist;
	private float discount;
	private float singlePrice;
	private float totalPrice;
	private Integer number;

	// Constructors

	/** default constructor */
	public AbstractOrderdetails() {
	}

	/** minimal constructor */
	public AbstractOrderdetails(Orders orders, Itemlist itemlist,
			float singlePrice, float totalPrice, Integer number) {
		this.orders = orders;
		this.itemlist = itemlist;
		this.singlePrice = singlePrice;
		this.totalPrice = totalPrice;
		this.number = number;
	}

	/** full constructor */
	public AbstractOrderdetails(Orders orders, Itemlist itemlist,
			float discount, float singlePrice, float totalPrice, Integer number) {
		this.orders = orders;
		this.itemlist = itemlist;
		this.discount = discount;
		this.singlePrice = singlePrice;
		this.totalPrice = totalPrice;
		this.number = number;
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

	public Itemlist getItemlist() {
		return this.itemlist;
	}

	public void setItemlist(Itemlist itemlist) {
		this.itemlist = itemlist;
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

}