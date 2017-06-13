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

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class CFramePOS extends CFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3949012749194081892L;
	private int		p_AD_Form_ID = 0;

	public CFramePOS() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public CFramePOS(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public CFramePOS(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public CFramePOS(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get Form ID
	 * @return
	 */
	public int getAD_Form_ID() {
		return p_AD_Form_ID;
	}
	
	/**
	 * Set Form ID
	 * @param p_AD_Form_ID
	 */
	public void setAD_Form_ID(int p_AD_Form_ID) {
		this.p_AD_Form_ID = p_AD_Form_ID;
	}

}
