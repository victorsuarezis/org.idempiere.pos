package org.adempiere.pos;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
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
