package src.com.server.hiber;

import java.util.Set;

/**
 * Items entity. @author MyEclipse Persistence Tools
 */
public class Items extends AbstractItems implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Items() {
	}

	/** minimal constructor */
	public Items(Category category, String name, float price) {
		super(category, name, price);
	}

	/** full constructor */
	public Items(Category category, String name, float price,
			Set itemlistsForGiftId, Set itemlistsForItemsId) {
		super(category, name, price, itemlistsForGiftId, itemlistsForItemsId);
	}

}
