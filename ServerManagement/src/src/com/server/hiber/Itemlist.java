package src.com.server.hiber;

/**
 * Itemlist entity. @author MyEclipse Persistence Tools
 */
public class Itemlist extends AbstractItemlist implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Itemlist() {
	}

	/** minimal constructor */
	public Itemlist(Store store, Items itemsByItemsId, Integer stock,
			float discount, Integer number) {
		super(store, itemsByItemsId, stock, discount, number);
	}

	/** full constructor */
	public Itemlist(Store store, Items itemsByItemsId, Items itemsByGiftId,
			Integer stock, float discount, Integer giftNum, Integer number) {
		super(store, itemsByItemsId, itemsByGiftId, stock, discount, giftNum,
				number);
	}

}
