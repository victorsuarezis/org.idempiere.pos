package org.compiere.model;

import java.math.BigDecimal;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MProductPricingPOS extends MProductPricing {
	
	private int 		productId;
	private int 		partnerId;
	private BigDecimal 	quantity = Env.ONE;
	private boolean 	isSOTrx = true;
	private String		trxName = null;
	private boolean 	useVendorBreak;


	public MProductPricingPOS(int M_Product_ID, int C_BPartner_ID,
			BigDecimal Qty, boolean isSOTrx) {
		super(M_Product_ID, C_BPartner_ID, Qty, isSOTrx);
		// TODO Auto-generated constructor stub
	}

	public MProductPricingPOS(int productId, int partnerId,
			   BigDecimal quantity, boolean isSOTrx, String trxName) {
		super(productId, partnerId, quantity, isSOTrx);
		this.productId = productId;
		this.partnerId = partnerId;
		this.trxName = trxName;

		if (quantity != null && Env.ZERO.compareTo(quantity) != 0)
			this.quantity = quantity;
		this.isSOTrx = isSOTrx;
		int thereAreVendorBreakRecords = DB.getSQLValue(trxName,
				"SELECT count(M_Product_ID) FROM M_ProductPriceVendorBreak WHERE M_Product_ID=? AND (BreakValue > 0 OR C_BPartner_ID=?)",
				this.productId, this.partnerId);
		useVendorBreak = thereAreVendorBreakRecords > 0;
	}	//	MProductPricing
}
