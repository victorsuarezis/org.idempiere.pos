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
package org.compiere.print;

import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MQuery;
import org.compiere.model.PrintInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class ReportEnginePOS extends ReportEngine {
	
	private static final String[]	DOC_BASETABLES = new String[] {
		"C_Order", "M_InOut", "C_Invoice", "C_Project",
		"C_RfQResponse",
		"C_PaySelectionCheck", "C_PaySelectionCheck", 
		"C_DunningRunEntry","PP_Order", "DD_Order", "M_Inventory", "M_Movement"};
	private static final String[]	DOC_IDS = new String[] {
		"C_Order_ID", "M_InOut_ID", "C_Invoice_ID", "C_Project_ID",
		"C_RfQResponse_ID",
		"C_PaySelectionCheck_ID", "C_PaySelectionCheck_ID", 
		"C_DunningRunEntry_ID" , "PP_Order_ID" , "DD_Order_ID", "M_Inventory_ID", "M_Movement_ID" };
	
	/** Payroll Check = 10  */
	public static final int		HR_CHECK = 10;	
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ReportEngine.class);

	public ReportEnginePOS(Properties ctx, MPrintFormat pf, MQuery query,
			PrintInfo info) {
		super(ctx, pf, query, info);
		// TODO Auto-generated constructor stub
	}

	public ReportEnginePOS(Properties ctx, MPrintFormat pf, MQuery query,
			PrintInfo info, boolean isSummary) {
		super(ctx, pf, query, info, isSummary);
		// TODO Auto-generated constructor stub
	}

	public ReportEnginePOS(Properties ctx, MPrintFormat pf, MQuery query,
			PrintInfo info, String trxName) {
		super(ctx, pf, query, info, trxName);
		// TODO Auto-generated constructor stub
	}

	public ReportEnginePOS(Properties ctx, MPrintFormat pf, MQuery query,
			PrintInfo info, boolean isSummary, String trxName) {
		super(ctx, pf, query, info, isSummary, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Print Confirm.
	 *  Update Date Printed
	 * 	@param type document type
	 * 	@param Record_ID record id
	 * 	@param trxName
	 */
	public static void printConfirm (int type, int Record_ID, String trxName)
	{
		StringBuffer sql = new StringBuffer();
		if (type == ORDER || type == SHIPMENT || type == INVOICE)
			sql.append("UPDATE ").append(DOC_BASETABLES[type])
			.append(" SET DatePrinted=SysDate, IsPrinted='Y' WHERE ")
			.append(DOC_IDS[type]).append("=").append(Record_ID);
		//
		if (sql.length() > 0)
		{
			int no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 1)
				log.log(Level.SEVERE, "Updated records=" + no + " - should be just one");
		}
	}	//	printConfirm
}
