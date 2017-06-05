package org.adempiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartnerInfo;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MBPartnerInfoPOS extends MBPartnerInfo {
	
	private static final long serialVersionUID = 4396099785792983796L;
	
	/**	Static Logger	*/
	private static CLogger	s_logPOS	= CLogger.getCLogger (MBPartnerInfoPOS.class);
	
	/**
	 * 
	 */
	public MBPartnerInfoPOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Find BPartners
	 *	@param ctx context
	 *	@param value Business Partner Value
	 *	@param taxId Business Partner TaxID
	 *	@param name Business Partner Name
	 * @param name2 Business Partner Name2
	 * @param contact Contact/User Name
	 * @param eMail Contact/User EMail
	 * @param phone phone
	 * @param city city      @return array if of info
	 */
	public static MBPartnerInfo[] find(Properties ctx,
									   String value, String taxId, String name, String name2, String contact, String eMail, String phone, String city)
	{
		StringBuffer sql = new StringBuffer ("SELECT * FROM RV_BPartner WHERE IsActive='Y'");
		StringBuffer sb = new StringBuffer();
		value = getFindParameter (value);
		if (value != null)
			sb.append(" UPPER(Value) LIKE ? ");

		taxId = getFindParameter (taxId);
		if (taxId != null)
			sb.append(" UPPER(TaxID) LIKE ? ");

		name = getFindParameter (name);
		if (name != null)
		{
			if (sb.length() > 0)
				sb.append(" OR ");
			sb.append(" UPPER(Name) LIKE ? ");
		}

		name2 = getFindParameter (name2);
		if (name2 != null)
		{
			if (sb.length() > 0)
				sb.append(" OR ");
			sb.append(" UPPER(Name2) LIKE ? ");
		}

		contact = getFindParameter (contact);
		if (contact != null)
		{
			if (sb.length() > 0)
				sb.append(" OR ");
			sb.append(" UPPER(ContactName) LIKE ? ");
		}
		eMail = getFindParameter (eMail);
		if (eMail != null)
		{
			if (sb.length() > 0)
				sb.append(" OR ");
			sb.append(" UPPER(EMail) LIKE ? ");
		}
		phone = getFindParameter (phone);
		if (phone != null)
		{
			if (sb.length() > 0)
				sb.append(" OR ");
			sb.append(" UPPER(Phone) LIKE ? ");
		}
		city = getFindParameter (city);
		if (city != null)
		{
			if (sb.length() > 0)
				sb.append(" OR ");
			sb.append(" UPPER(City) LIKE ? ");
		}
		if (sb.length() > 0)
			sql.append(" AND (").append(sb).append(")");
		sql.append(" ORDER BY Value ");
		//
		String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(), 
			"RV_BPartner", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		ArrayList<MBPartnerInfo> list = new ArrayList<MBPartnerInfo>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(finalSQL, null);
			int index = 1;
			if (value != null)
				pstmt.setString(index++, value);
			if (taxId != null)
				pstmt.setString(index++, taxId);
			if (name != null)
				pstmt.setString(index++, name);
			if (name2 != null)
				pstmt.setString(index++, name2);
			if (contact != null)
				pstmt.setString(index++, contact);
			if (eMail != null)
				pstmt.setString(index++, eMail);
			if (phone != null)
				pstmt.setString(index++, phone);
			if (city != null)
				pstmt.setString(index++, city);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MBPartnerInfo (ctx, rs, null));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_logPOS.log(Level.SEVERE, "find - " + finalSQL, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//	Return
		MBPartnerInfo[] retValue = new MBPartnerInfo[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	find

}
