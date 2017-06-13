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

import java.awt.AWTEvent;
import java.util.EventListener;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class MiniTablePOS extends MiniTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8994543529425879809L;

	public MiniTablePOS() {
		// TODO Auto-generated constructor stub
	}
	
    public void fireRowSelectionEvent() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();

        // Lazily create the event:
        RowSelectionEvent rowSelectionEvent = new RowSelectionEvent(this, RowSelectionEvent.ROW_TOGGLED);

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==MiniTableSelectionListener.class) {
                ((MiniTableSelectionListener)listeners[i+1]).rowSelected(rowSelectionEvent);
            }          
        }
    }
    
	@SuppressWarnings("serial")
	public class RowSelectionEvent extends AWTEvent {
	    public static final int ROW_TOGGLED = AWTEvent.RESERVED_ID_MAX + 1;
	    public RowSelectionEvent(MiniTable source, int id) {
	        super(source, id);
	    }
	}
	
	public interface MiniTableSelectionListener extends EventListener {
		public  abstract void rowSelected(RowSelectionEvent e);
	}
}
