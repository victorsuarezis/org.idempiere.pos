package org.compiere.minigrid;

import java.awt.AWTEvent;
import java.util.EventListener;

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
