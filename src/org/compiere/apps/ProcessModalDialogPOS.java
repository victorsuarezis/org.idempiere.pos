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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import org.adempiere.util.IProcessUI;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
public class ProcessModalDialogPOS extends ProcessModalDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7515900781738665154L;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessModalDialog.class);

	private ASyncProcess 	aSyncProcess;
	private int 			windowNo;
	private boolean 		isValid = true;
	private boolean 		isOnlyPanel;
	private boolean 		autoStart;
	private ProcessInfo processInfo;
	private BorderLayout mainLayout = new BorderLayout();
	//
	private ProcessPanel processPanel = null;
	
	private CPanel dialog = new CPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4174872923960645676L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMinimumSize();
			if ( d.height < m.height || d.width < m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.max(d.height, m.height);
				d1.width = Math.max(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	private ConfirmPanel southPanel = new ConfirmPanel(true);
	private CButton bOK = null;
	private JEditorPane message = new JEditorPane() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2799655985925184556L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMaximumSize();
			if ( d.height > m.height || d.width > m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.min(d.height, m.height);
				d1.width = Math.min(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	
	private JScrollPane messagePane = new JScrollPane(message) {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8451885469041419529L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMaximumSize();
			if ( d.height > m.height || d.width > m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.min(d.height, m.height);
				d1.width = Math.min(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	private CPanel centerPanel = null;

	public ProcessModalDialogPOS(Properties ctx, Frame parent, String title,
			IProcessUI aProcess, int WindowNo, int AD_Process_ID, int tableId,
			int recordId, boolean autoStart) {
		super(ctx, parent, title, aProcess, WindowNo, AD_Process_ID, tableId,
				recordId, autoStart);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Private Constructor
	 * @param ctx
	 * @param parent
	 * @param title
	 * @param aProcess
	 * @param WindowNo
	 * @param processId
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 * @param pi
	 * @param isOnlyPanel
	 */
	private ProcessModalDialogPOS (Properties ctx, Frame parent, String title, 
			ASyncProcess aProcess, int WindowNo, int processId,
			int tableId, int recordId, boolean autoStart, ProcessInfo pi, boolean isOnlyPanel) {
		super(ctx, parent, title, (IProcessUI)aProcess, WindowNo, processId, tableId,
				recordId, autoStart);
		log.info("Process=" + processId);
		this.isOnlyPanel = isOnlyPanel;
		this.autoStart = autoStart;
		if(pi == null) {
			aSyncProcess = aProcess;
			processInfo = new ProcessInfo(title, processId, tableId, recordId);
		} else {
			processInfo = pi;
		}
		
		//	
		windowNo = WindowNo;
		//	
		try {
			jbInit();
			init();
		} catch(Exception ex) {
			log.log(Level.SEVERE, "", ex);
		}
	}	//	ProcessDialog
	
	/**
	 * Optional constructor, for launch from ProcessCtl
	 * @param frame
	 * @param WindowNo
	 * @param pi
	 */
	public ProcessModalDialogPOS (Frame frame, int WindowNo, ProcessInfo pi) {
		this(Env.getCtx(), frame, pi.getTitle(), 
				null, WindowNo, pi.getAD_Process_ID(), 
				pi.getTable_ID(), pi.getRecord_ID(), false, pi, true);
	}
	
	/**
	 *	Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception {
		dialog.setLayout(mainLayout);
		dialog.setMinimumSize(new Dimension(500, 200));
		southPanel.addActionListener(this);
		bOK = southPanel.getOKButton();
		//
		message.setContentType("text/html");
		message.setEditable(false);
		message.setBackground(Color.white);
		message.setFocusable(false);
		getContentPane().add(dialog);
		dialog.add(southPanel, BorderLayout.SOUTH);
		dialog.add(messagePane, BorderLayout.NORTH);
		messagePane.setBorder(null);
		messagePane.setMaximumSize(new Dimension(600, 300));
		centerPanel = new CPanel();
		centerPanel.setBorder(null);
		centerPanel.setLayout(new BorderLayout());
		dialog.add(centerPanel, BorderLayout.CENTER);
		//
		this.getRootPane().setDefaultButton(bOK);
	}	//	jbInit
	
	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	public boolean init() {
		log.config("");
		processInfo.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		processInfo.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		processPanel = new ProcessPanel((IProcessDialog)this, windowNo, processInfo, ProcessPanel.COLUMNS_1);
		processPanel.setIsOnlyPanel(isOnlyPanel);
		processPanel.setAutoStart(autoStart);
		processPanel.createFieldsAndEditors();
		//	Set Default
		getContentPane().add(processPanel.getPanel());
		setTitle(processPanel.getName());
		//	Revalidate
		validateScreen();
		return true;
	}	//	init
	
	public void validateScreen() {
		validate();
		getRootPane().setDefaultButton(processPanel.getDefaultButton());
	}
	
	/**
	 *	Is everything OK?
	 *  @return true if parameters saved correctly
	 */
	public boolean isOK() {
		return processPanel.isOkPressed();
	}	//	isOK
}
