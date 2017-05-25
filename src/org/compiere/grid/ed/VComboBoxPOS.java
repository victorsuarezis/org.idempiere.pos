package org.compiere.grid.ed;

import javax.swing.ComboBoxModel;

import org.adempiere.pos.POSLookupCellRenderer;

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
