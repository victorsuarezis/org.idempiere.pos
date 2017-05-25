package org.compiere.grid.ed;

import java.util.List;

import org.adempiere.base.Service;
import org.adempiere.model.GridField;
import org.adempiere.ui.swing.factory.IEditorFactoryPOS;
import org.compiere.model.GridTab;

public class VEditorFactoryPOS extends VEditorFactory {

	public VEditorFactoryPOS() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *  Create Editor for MField.
	 *  The Name is set to the column name for dynamic display management
	 *  @param mField MField
	 *  @param tableEditor true if table editor
	 *  @return grid editor
	 */
	public static VEditor getEditor (GridField mField, boolean tableEditor)
	{
		return getEditor (null, mField, tableEditor);
	}   //  getEditor
	
	/**
	 *  Create Editor for MField.
	 *  The Name is set to the column name for dynamic display management
	 *  @param mTab MTab
	 *  @param mField MField
	 *  @param tableEditor true if table editor
	 *  @return grid editor
	 */
	public static VEditor getEditor (GridTab mTab, GridField mField, boolean tableEditor)
	{
		VEditor editor = null;
		List<IEditorFactoryPOS> factoryList = Service.locator().list(IEditorFactoryPOS.class).getServices();
		for(IEditorFactoryPOS factory : factoryList)
		{
			editor = factory.getEditor(mTab, mField, tableEditor);
			if (editor != null)
				break;
		}
		return editor;
	}	//	getEditor
}
