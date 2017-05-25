package org.compiere.swing;

import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.jdesktop.swingx.JXCollapsiblePane;

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
