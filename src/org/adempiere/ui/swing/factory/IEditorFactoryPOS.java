/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.ui.swing.factory;

import org.compiere.grid.ed.VEditor;
import org.adempiere.model.GridField;
import org.compiere.model.GridTab;

/**
 *	Editor Factory.
 *
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
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
