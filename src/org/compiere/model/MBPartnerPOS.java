package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.Env;

public class MBPartnerPOS extends MBPartner {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2662675553578309595L;

	public MBPartnerPOS(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	public MBPartnerPOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBPartnerPOS(Properties ctx, int C_BPartner_ID, String trxName) {
		super(ctx, C_BPartner_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MBPartnerPOS(X_I_BPartner impBP) {
		super(impBP);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get Template from POS, optional if it not exists on POS Terminal the get from Client
	 * @param ctx
	 * @param clientId
	 * @param posId
	 * @return
	 */
	public static MBPartner getTemplate(Properties ctx, int clientId, int posId) {
		MPOS pos = MPOS.get(ctx, posId);
		//	Validate
		if(pos.getC_POS_ID() != 0) {
			if(pos.getC_BPartnerCashTrx_ID() != 0) {
				MBPartner template = get(ctx, pos.getC_BPartnerCashTrx_ID());
				return cleanBPartner(ctx, template);
			}
		}
		//	Return from Client
		return getTemplate(ctx, clientId);
	}
	
	/**
	 * Get a Clean BPartner
	 * @param ctx
	 * @param source
	 * @return
	 */
	private static MBPartner cleanBPartner(Properties ctx, MBPartner source) {
		if (source == null)
			source = new MBPartner (ctx, 0, null);
		//	Reset
		source.set_ValueNoCheck ("C_BPartner_ID", new Integer(0));
		source.setTaxID("");
		source.setValue("");
		source.setName("");
		source.setName2(null);
		source.setDUNS("");
		source.setFirstSale(null);
		//
		source.setSO_CreditLimit (Env.ZERO);
		source.setSO_CreditUsed (Env.ZERO);
		source.setTotalOpenBalance (Env.ZERO);
		//	s_template.setRating(null);
		//
		source.setActualLifeTimeValue(Env.ZERO);
		source.setPotentialLifeTimeValue(Env.ZERO);
		source.setAcqusitionCost(Env.ZERO);
		source.setShareOfCustomer(0);
		source.setSalesVolume(0);
		// Reset Created, Updated to current system time ( teo_sarca )
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		source.set_ValueNoCheck("Created", ts);
		source.set_ValueNoCheck("Updated", ts);
		//	Return
		return source;
	}

}
