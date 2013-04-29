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
	private Float discount;
	private Float singlePrice;
	private Float totalPrice;
	private Integer number;

	// Constructors

	/** default constructor */
	public AbstractOrderdetails() {
	}

	/** minimal constructor */
	public AbstractOrderdetails(Orders orders, Itemlist itemlist,
			Float singlePrice, Float totalPrice, Integer number) {
		this.orders = orders;
		this.itemlist = itemlist;
		this.singlePrice = singlePrice;
		this.totalPrice = totalPrice;
		this.number = number;
	}

	/** full constructor */
	public AbstractOrderdetails(Orders orders, Itemlist itemlist,
			Float discount, Float singlePrice, Float totalPrice, Integer number) {
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

}