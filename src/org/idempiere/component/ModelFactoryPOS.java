package org.idempiere.component;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.MPOS;
import org.compiere.model.PO;
import org.compiere.util.Env;

public class ModelFactoryPOS implements IModelFactory {

	public ModelFactoryPOS() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getClass(String tableName) {
		if(tableName.equals(MPOS.Table_Name))
			return MPOS.class;
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		if(tableName.equals(MPOS.Table_Name))
			return new MPOS(Env.getCtx(), Record_ID, trxName);
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		if(tableName.equals(MPOS.Table_Name))
			return new MPOS(Env.getCtx(), rs, trxName);
		return null;
	}

}
