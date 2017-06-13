/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
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
