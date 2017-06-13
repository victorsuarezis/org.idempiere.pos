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
import java.util.Properties;

import org.compiere.util.TimeUtil;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class MBankStatementPOS extends MBankStatement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1255464161392254369L;

	public MBankStatementPOS(Properties ctx, int C_BankStatement_ID,
			String trxName) {
		super(ctx, C_BankStatement_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBankStatementPOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBankStatementPOS(MBankAccount account, boolean isManual) {
		super(account, isManual);
		// TODO Auto-generated constructor stub
	}

	public MBankStatementPOS(MBankAccount account) {
		super(account);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Add payment to bank statement
	 * @param payment
	 */
	static public MBankStatementLine addPayment(MPayment payment)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MBankStatement.COLUMNNAME_C_BankAccount_ID).append("=? AND ")
				.append("TRUNC(").append(MBankStatement.COLUMNNAME_StatementDate).append(",'DD')=? AND ")
				.append(MBankStatement.COLUMNNAME_Processed).append("=?");
		MBankStatement bankStatement = new Query(payment.getCtx() , MBankStatement.Table_Name , whereClause.toString(), payment.get_TrxName())
				.setClient_ID()
				.setParameters(payment.getC_BankAccount_ID(), TimeUtil.getDay(payment.getDateTrx()) , false)
				.first();
		if (bankStatement == null || bankStatement.get_ID() <= 0)
		{
			bankStatement =  new MBankStatement(payment.getCtx() , 0 , payment.get_TrxName());
			bankStatement.setC_BankAccount_ID(payment.getC_BankAccount_ID());
			bankStatement.setStatementDate(payment.getDateAcct());
			bankStatement.setDateAcct(payment.getDateAcct());
			bankStatement.setName(payment.getDescription());
			bankStatement.saveEx();
		}

		MBankStatementLine bankStatementLine = new MBankStatementLine(bankStatement);
		bankStatementLine.setPayment(payment);
		bankStatementLine.setStatementLineDate(payment.getDateAcct());
		bankStatementLine.setDateAcct(payment.getDateAcct());
		bankStatementLine.saveEx();
		return bankStatementLine;
	}
}
