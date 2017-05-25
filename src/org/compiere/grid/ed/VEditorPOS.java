package org.compiere.grid.ed;

import org.adempiere.model.GridField;

public interface VEditorPOS extends VEditor {
	
	/**
	 *  Set Field/WindowNo for ValuePreference
	 *  @param mField
	 */
	public void setFieldPOS (GridField mField);

	/**
	 *  Get Field/WindowNo for ValuePreference
	 *  @return mField
	 */
	public GridField getFieldPOS ();
}
