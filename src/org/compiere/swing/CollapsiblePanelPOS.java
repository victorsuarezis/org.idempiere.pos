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
package org.compiere.swing;

import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.jdesktop.swingx.JXCollapsiblePane;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class CollapsiblePanelPOS extends CollapsiblePanel {
	
	private JXCollapsiblePane collapsible;
	private Border separatorBorder;


	/**
	 * 
	 */
	private static final long serialVersionUID = -2006025550883376754L;

	public CollapsiblePanelPOS(String title) {
		super(title);
		
		separatorBorder = new SeparatorBorder();
		setTitleForegroundColor(Color.BLACK);
		setTitleBackgroundColor(new Color(248, 248, 248));
		setSeparatorColor(new Color(214, 223, 247));
		
		collapsible = new JXCollapsiblePane();
		collapsible.getContentPane().setBackground(
				AdempierePLAF.getFormBackground());
		collapsible.setBorder(new CompoundBorder(separatorBorder, collapsible
				.getBorder()));
		super.add(collapsible);
	}
	
	public void setCollapsed(boolean collapsed) {
		if (collapsible != null)
			collapsible.setCollapsed(collapsed);
	}

}
