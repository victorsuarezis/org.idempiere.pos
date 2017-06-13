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
package org.compiere.grid.ed;

import java.util.List;

import org.adempiere.base.Service;
import org.adempiere.model.GridField;
import org.adempiere.ui.swing.factory.IEditorFactoryPOS;
import org.compiere.model.GridTab;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
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
