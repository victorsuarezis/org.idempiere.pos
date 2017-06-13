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
package org.compiere.process;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class ProcessInfoPOS extends ProcessInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6959951844554617269L;
	
	private boolean 			managedTransaction = true;
	//	FR [ 244 ]
	private boolean 			isSelection = false;
	/**	Multi-Selection Keys		*/
	private List<Integer>		keySelection = null;
	//	FR [ 352 ]
	/**	Multi-Selection Parameters	*/
	private LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection = null;
	/* Alias table selection */
	private String aliasTableSelection;
	/* Table ID */
	private int tableSelectionId;
	
	private int          		windowNo = 0;

	public ProcessInfoPOS(String Title, int AD_Process_ID, int Table_ID,
			int Record_ID) {
		super(Title, AD_Process_ID, Table_ID, Record_ID);
		// TODO Auto-generated constructor stub
	}

	public ProcessInfoPOS(String Title, int AD_Process_ID) {
		super(Title, AD_Process_ID);
		// TODO Auto-generated constructor stub
	}
	
	public ProcessInfoPOS (String title, int processId, int tableId, int recordId, boolean managedTransaction) {
		this(title, processId , tableId , recordId);
		this.managedTransaction = managedTransaction;
	}
	
	public void setManagedTransaction(boolean managedTransaction) {
		this.managedTransaction = managedTransaction;
	}
	
	public boolean isManagedTransaction() {
		return managedTransaction;
	}
	
	/**
	 * FR [ 244 ]
	 * Set the flag for know if is from SB or not
	 * @param isSelection
	 */
	public void setIsSelection(boolean isSelection) {
		this.isSelection = isSelection;
	}
	
	/**
	 * FR [ 244 ]
	 * Return flag is selection
	 * @return
	 */
	public boolean isSelection() {
		return isSelection;
	}
	
	/**
	 * Set Selection keys
	 * @param selection
	 */
	public void setSelectionKeys(List<Integer> selection) {
		keySelection = selection;
		setIsSelection(selection != null && selection.size() > 0);
	}
	
	/**
	 * Get Selection keys (used just for key without values)
	 * @return
	 */
	public List<Integer> getSelectionKeys() {
		return keySelection;
	}
	
	/**
	 * Set Selection Parameters
	 * @param selection
	 */
	public void setSelectionValues(LinkedHashMap<Integer, LinkedHashMap<String, Object>> selection) {
		this.selection = selection;
		setIsSelection(selection != null && selection.size() > 0);
		//	fill key
		if(selection != null) {
			keySelection = new ArrayList<Integer>();
			for(Entry<Integer,LinkedHashMap<String, Object>> records : selection.entrySet()) {
				keySelection.add(records.getKey());
			}
		}
	}

	/**
	 * Get Selection
	 * @return
	 */
	public LinkedHashMap<Integer, LinkedHashMap<String, Object>> getSelectionValues() {
		return selection;
	}
	
	/**
	 * set table alias for  selection
	 * @param aliasTableSelection
	 */
	public void setAliasForTableSelection(String aliasTableSelection) {
		this.aliasTableSelection = aliasTableSelection;
	}
	
	/**
	 * Get Selection Table Alias
	 * @return
	 */
	public String getAliasForTableSelection() {
		return aliasTableSelection;
	}
	
	/**
	 * Method setTable_ID
	 * @param tableSelectionId
	 */
	public void setTableSelectionId(int tableSelectionId)
	{
		this.tableSelectionId = tableSelectionId;
	}

	/**
	 * Method tableSelectionId
	 * @return int
	 */
	public int getTableSelectionId()
	{
		return tableSelectionId;
	}
	
	/**
	 * @return the window No
	 */
	public int getWindowNo()
	{
		return windowNo;
	}

	/**
	 * @param window No the window No to set
	 */
	public void setWindowNo(int windowNo)
	{
		this.windowNo = windowNo;
	}
}
