package src.com.server.hiber;

import java.util.Set;

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
			float discount, boolean state, Integer number) {
		super(store, itemsByItemsId, stock, discount, state, number);
	}

	/** full constructor */
	public Itemlist(Store store, Items itemsByItemsId, Items itemsByGiftId,
			Integer stock, float discount, Integer giftNum, boolean state,
			Integer number, Set orderdetailses) {
		super(store, itemsByItemsId, itemsByGiftId, stock, discount, giftNum,
				state, number, orderdetailses);
	}

}
