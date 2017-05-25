package org.adempiere.ui.swing.factory;

import org.compiere.grid.ed.VEditor;
import org.adempiere.model.GridField;
import org.compiere.model.GridTab;

public interface IEditorFactoryPOS extends IEditorFactory {
	
	/**
	 *  Create Editor for MField.
	 *  The Name is set to the column name for dynamic display management
	 *  @param mTab MTab
	 *  @param mField MField
	 *  @param tableEditor true if table editor
	 *  @return grid editor
	 */
	public VEditor getEditor (GridTab mTab, GridField mField, boolean tableEditor);
}
