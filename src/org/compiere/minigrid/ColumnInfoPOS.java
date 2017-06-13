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

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class ColumnInfoPOS extends ColumnInfo {
	
	private int      	m_DisplayType;
	private boolean		m_isVisible;

	public ColumnInfoPOS(String colHeader, String colSQL, Class<?> colClass) {
		super(colHeader, colSQL, colClass);
		// TODO Auto-generated constructor stub
	}

	public ColumnInfoPOS(String colHeader, String colSQL, Class<?> colClass,
			String keyPairColSQL) {
		super(colHeader, colSQL, colClass, keyPairColSQL);
		// TODO Auto-generated constructor stub
	}

	public ColumnInfoPOS(String colHeader, String colSQL, Class<?> colClass,
			boolean readOnly, boolean colorColumn, String keyPairColSQL) {
		super(colHeader, colSQL, colClass, readOnly, colorColumn, keyPairColSQL);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *  Create Info Column
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param displayType
	 *  @param readOnly  column is read only
	 *  @param colorColumn   if true, value of column determines foreground color
	 *  @param keyPairColSQL  SQL select for the ID of the for the displayed column
	 *  @param visible  if true, the column will be visible.  False, it will be hidden.
	 */
	public ColumnInfoPOS (String colHeader, String colSQL, Class<?> colClass, int displayType,
		boolean readOnly, boolean colorColumn, String keyPairColSQL, boolean visible)
	{
		super(colHeader, colSQL, colClass, readOnly, colorColumn, keyPairColSQL);
		setColHeader(colHeader);
		setColSQL(colSQL);
		setColClass(colClass);
		setReadOnly(readOnly);
		setColorColumn(colorColumn);
		setKeyPairColSQL(keyPairColSQL);
		setDisplayType(displayType);
		setVisibility(visible);
	}   //  ColumnInfo
	
	/**
	 * 	Set Display Type
	 *	@param display Type
	 */
	public void setDisplayType(int displayType)
	{
		m_DisplayType = displayType;
	}
	/**
	 * 	Display Type
	 *	@return display Type
	 */
	public int getDisplayType()
	{
		return m_DisplayType;
	}
	
	/**
	 * @param m_isVisible the m_isVisible to set
	 */
	public void setVisibility(boolean m_isVisible) {
		this.m_isVisible = m_isVisible;
	}

	/**
	 * @return the m_isVisible
	 */
	public boolean getVisibility() {
		return m_isVisible;
	}
}
