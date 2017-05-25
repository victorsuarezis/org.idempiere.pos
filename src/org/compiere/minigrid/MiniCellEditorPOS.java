package org.compiere.minigrid;

import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VString;
import org.compiere.util.DisplayType;

public class MiniCellEditorPOS extends MiniCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -324845228179134569L;

	public MiniCellEditorPOS(Class<?> c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public VEditor m_editorPOS = null;
	
	/**
	 *  Default Constructor
	 *  @param c Class
	 */
	public MiniCellEditorPOS(Class c, int displayType)
	{
		super(c);	
		if(DisplayType.Date == displayType)
			m_editorPOS = new VDate();
		else if(DisplayType.DateTime == displayType)	
			m_editorPOS = new VDate(DisplayType.DateTime);
		else if(DisplayType.Amount == displayType)
			m_editorPOS = new VNumber("Amount", false, false, true, DisplayType.Amount, "Amount");
		else if(DisplayType.Number == displayType)
			m_editorPOS = new VNumber("Number", false, false, true, DisplayType.Number, "Number");
		else if (DisplayType.Integer == displayType)
			m_editorPOS = new VNumber("Integer", false, false, true, DisplayType.Integer, "Integer");
		else if(DisplayType.String == displayType)
			m_editorPOS = new VString();
		else
			m_editorPOS = new VString();
	}

}
