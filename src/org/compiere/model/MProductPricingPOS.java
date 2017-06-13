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

import java.math.BigDecimal;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
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
