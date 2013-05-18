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
	public Orderdetails(Orders orders, float singlePrice, float totalPrice,
			Integer number, String itemName) {
		super(orders, singlePrice, totalPrice, number, itemName);
	}

	/** full constructor */
	public Orderdetails(Orders orders, float discount, float singlePrice,
			float totalPrice, Integer number, String itemName) {
		super(orders, discount, singlePrice, totalPrice, number, itemName);
	}

}
