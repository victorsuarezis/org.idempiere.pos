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

import javax.swing.ComboBoxModel;
import org.adempiere.pos.POSLookupCellRenderer;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class VComboBoxPOS extends VComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2978871655755824102L;

	public VComboBoxPOS() {
		// TODO Auto-generated constructor stub
	}

	public VComboBoxPOS(Object[] items) {
		super(items);
		// TODO Auto-generated constructor stub
	}

	public VComboBoxPOS(ComboBoxModel<Object> model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	
	public void setRenderer(POSLookupCellRenderer posLookupCellRenderer) {
		// TODO Auto-generated method stub
		System.out.println("Metodo SetRenderer de la clase VComboBoxPOS no implementado aun");
	}	

}
