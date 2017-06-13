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

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class IDColumnRendererPOS extends IDColumnRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1070891218001221104L;
	
	/* The Multi-Selection renderer     */
	public JCheckBox   m_checkPOS;
	/** Mult-Selection flag */
	public boolean     m_multiSelectionPOS;
	/** The Single-Selection renderer   */
	public JButton     m_buttonPOS;

	public IDColumnRendererPOS(boolean multiSelection) {
		super(multiSelection);
		m_multiSelectionPOS = multiSelection;
		//          Multi => Check
		if (m_multiSelectionPOS)
		{
			m_checkPOS = new JCheckBox();
			m_checkPOS.setMargin(new Insets(0,0,0,0));
			m_checkPOS.setHorizontalAlignment(JLabel.CENTER);
		}
		else    //  Single => Button
		{
			m_buttonPOS = new JButton();
			m_buttonPOS.setMargin(new Insets(0,0,0,0));
			m_buttonPOS.setSize(new Dimension(5,5));
		}
	}
	
	public void addChangeListener(ChangeListener listener) {
		m_checkPOS.addChangeListener(listener);
	} //  IDColumnRenderer

}
