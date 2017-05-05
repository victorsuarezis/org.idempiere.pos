package org.idempiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MPaymentProcessorPOS extends MPaymentProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4581486082511173069L;

	public MPaymentProcessorPOS(Properties ctx, int C_PaymentProcessor_ID,
			String trxName) {
		super(ctx, C_PaymentProcessor_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPaymentProcessorPOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public static MPaymentProcessor[] find (Properties ctx,
			String tender, String CCType,
			int AD_Client_ID, int AD_Org_ID, int C_Currency_ID, BigDecimal Amt, String trxName)
		{
			return find(ctx, tender, CCType, AD_Client_ID, C_Currency_ID, Amt, trxName);
		}
	
	/**
	 * 	Get BankAccount & PaymentProcessor
	 * 	@param ctx context
	 *  @param tender optional Tender see TENDER_
	 *  @param CCType optional CC Type see CC_
	 *  @param AD_Client_ID Client
	 *  @param C_Currency_ID Currency (ignored)
	 *  @param Amt Amount (ignored)
	 *	@param trxName transaction
	 *  @return Array of BankAccount[0] & PaymentProcessor[1] or null
	 */
	protected static MPaymentProcessor[] find (Properties ctx,
		String tender, String CCType,
		int AD_Client_ID, int C_Currency_ID, BigDecimal Amt, String trxName)
	{
		ArrayList<MPaymentProcessor> list = new ArrayList<MPaymentProcessor>();
		StringBuffer sql = new StringBuffer("SELECT * "
			+ "FROM C_PaymentProcessor "
			+ "WHERE AD_Client_ID=? AND IsActive='Y'"				//	#1
			+ " AND (C_Currency_ID IS NULL OR C_Currency_ID=?)"		//	#2
			+ " AND (MinimumAmt IS NULL OR MinimumAmt = 0 OR MinimumAmt <= ?)");	//	#3
		if (MPayment.TENDERTYPE_DirectDeposit.equals(tender))
			sql.append(" AND AcceptDirectDeposit='Y'");
		else if (MPayment.TENDERTYPE_DirectDebit.equals(tender))
			sql.append(" AND AcceptDirectDebit='Y'");
		else if (MPayment.TENDERTYPE_Check.equals(tender))
			sql.append(" AND AcceptCheck='Y'");
		//  CreditCards
		else if (MPayment.CREDITCARDTYPE_ATM.equals(CCType))
			sql.append(" AND AcceptATM='Y'");
		else if (MPayment.CREDITCARDTYPE_Amex.equals(CCType))
			sql.append(" AND AcceptAMEX='Y'");
		else if (MPayment.CREDITCARDTYPE_Visa.equals(CCType))
			sql.append(" AND AcceptVISA='Y'");
		else if (MPayment.CREDITCARDTYPE_MasterCard.equals(CCType))
			sql.append(" AND AcceptMC='Y'");
		else if (MPayment.CREDITCARDTYPE_Diners.equals(CCType))
			sql.append(" AND AcceptDiners='Y'");
		else if (MPayment.CREDITCARDTYPE_Discover.equals(CCType))
			sql.append(" AND AcceptDiscover='Y'");
		else if (MPayment.CREDITCARDTYPE_PurchaseCard.equals(CCType))
			sql.append(" AND AcceptCORPORATE='Y'");
		//
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, C_Currency_ID);
			pstmt.setBigDecimal(3, Amt);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MPaymentProcessor (ctx, rs, trxName));
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, "find - " + sql, e);
			return null;
		}
		//
		if (list.size() == 0)
			s_log.warning("find - not found - AD_Client_ID=" + AD_Client_ID
				+ ", C_Currency_ID=" + C_Currency_ID + ", Amt=" + Amt);
		else
			s_log.fine("find - #" + list.size() + " - AD_Client_ID=" + AD_Client_ID
				+ ", C_Currency_ID=" + C_Currency_ID + ", Amt=" + Amt);
		MPaymentProcessor[] retValue = new MPaymentProcessor[list.size()];
		list.toArray(retValue);
		return retValue;
	}   //  find
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MPaymentProcessor.class);

}
