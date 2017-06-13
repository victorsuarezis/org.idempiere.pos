package org.adempiere.pos;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 * 
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class AdempierePOSException extends AdempiereException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1760091236354535749L;

	public AdempierePOSException(String message) {
		super(Msg.parseTranslation(Env.getCtx() ,message));
	}
}
