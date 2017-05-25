package org.compiere.minigrid;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;

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
