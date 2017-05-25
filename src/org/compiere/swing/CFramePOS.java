package org.compiere.swing;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

public class CFramePOS extends CFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3949012749194081892L;
	private int		p_AD_Form_ID = 0;

	public CFramePOS() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public CFramePOS(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public CFramePOS(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public CFramePOS(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get Form ID
	 * @return
	 */
	public int getAD_Form_ID() {
		return p_AD_Form_ID;
	}
	
	/**
	 * Set Form ID
	 * @param p_AD_Form_ID
	 */
	public void setAD_Form_ID(int p_AD_Form_ID) {
		this.p_AD_Form_ID = p_AD_Form_ID;
	}

}
