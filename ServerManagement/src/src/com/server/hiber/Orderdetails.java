package src.com.server.hiber;

/**
 * Orderdetails entity. @author MyEclipse Persistence Tools
 */
public class Orderdetails extends AbstractOrderdetails implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Orderdetails() {
	}

	/** minimal constructor */
	public Orderdetails(Orders orders, Itemlist itemlist, float singlePrice,
			float totalPrice, Integer number) {
		super(orders, itemlist, singlePrice, totalPrice, number);
	}

	/** full constructor */
	public Orderdetails(Orders orders, Itemlist itemlist, float discount,
			float singlePrice, float totalPrice, Integer number) {
		super(orders, itemlist, discount, singlePrice, totalPrice, number);
	}

}
