package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MPaymentPOS extends MPayment {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1912321273669202941L;

	public MPaymentPOS(Properties ctx, int C_Payment_ID, String trxName) {
		super(ctx, C_Payment_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPaymentPOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get payment for order
	 * @param order
	 * @return payments list
     */
	public static List<MPayment> getOfOrder(MOrder order)
	{
		return new Query(order.getCtx() , MPayment.Table_Name , MOrder.COLUMNNAME_C_Order_ID + "=?", order.get_TrxName())
				.setClient_ID()
				.setParameters(order.getC_Order_ID())
				.list();
	}

}
