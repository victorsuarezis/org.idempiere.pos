package org.compiere.swing;

import java.beans.VetoableChangeListener;

import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.model.GridField;

public interface CEditorPOS extends CEditor {
	
	public void addVetoableChangeListener(VetoableChangeListener listener);
	
	public void addValueChangeListener(ValueChangeListener listener);
	
	public GridField getField();
}
