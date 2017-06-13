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
package org.compiere.minigrid;

import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VString;
import org.compiere.util.DisplayType;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class MiniCellEditorPOS extends MiniCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -324845228179134569L;

	public MiniCellEditorPOS(Class<?> c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public VEditor m_editorPOS = null;
	
	/**
	 *  Default Constructor
	 *  @param c Class
	 */
	public MiniCellEditorPOS(Class c, int displayType)
	{
		super(c);	
		if(DisplayType.Date == displayType)
			m_editorPOS = new VDate();
		else if(DisplayType.DateTime == displayType)	
			m_editorPOS = new VDate(DisplayType.DateTime);
		else if(DisplayType.Amount == displayType)
			m_editorPOS = new VNumber("Amount", false, false, true, DisplayType.Amount, "Amount");
		else if(DisplayType.Number == displayType)
			m_editorPOS = new VNumber("Number", false, false, true, DisplayType.Number, "Number");
		else if (DisplayType.Integer == displayType)
			m_editorPOS = new VNumber("Integer", false, false, true, DisplayType.Integer, "Integer");
		else if(DisplayType.String == displayType)
			m_editorPOS = new VString();
		else
			m_editorPOS = new VString();
	}

}
