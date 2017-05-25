package org.compiere.apps;

import java.awt.Container;

import org.adempiere.util.IProcessUI;
import org.compiere.model.MPInstance;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.idempiere.util.EnvPOS;

public class ProcessCtlPOS extends ProcessCtl {
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ProcessCtlPOS.class);

	public ProcessCtlPOS(IProcessUI parent, int WindowNo, ProcessInfo pi,
			Trx trx) {
		super(parent, WindowNo, pi, trx);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 *	Process Control
	 *  <code>
	 *	- Get Instance ID
	 *	- Get Parameters
	 *	- execute (lock - start process - unlock)
	 *  </code>
	 *  Creates a ProcessCtl instance, which calls
	 *  lockUI and unlockUI if parent is a ASyncProcess
	 *  <br>
	 *	Called from APanel.cmd_print, APanel.actionButton and
	 *  VPaySelect.cmd_generate
	 *
	 *  @param parent ASyncProcess & Container
	 *  @param WindowNo window no
	 *  @param pi ProcessInfo process info
	 *  @param trx Transaction
	 *  @return worker started ProcessCtl instance or null for workflow
	 */
	public static ProcessCtlPOS process (ASyncProcess parent, int WindowNo, ProcessInfo pi, Trx trx)
	{
		log.fine("WindowNo=" + WindowNo + " - " + pi);

		MPInstance instance = null; 
		try 
		{ 
			instance = new MPInstance(Env.getCtx(), pi.getAD_Process_ID(), pi.getRecord_ID()); 
		} 
		catch (Exception e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		} 
		catch (Error e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		}
		if (!instance.save())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			pi.setError (true);
			return null;
		}
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Get Parameters (Dialog)
		//	FR [ 265 ]
		//	Change to Standard Process Modal Dialog
		ProcessModalDialogPOS para = new ProcessModalDialogPOS(EnvPOS.getFrame((Container)parent), WindowNo, pi);
		if (para.isValidDialog()) {
			para.validate();
			para.pack();
			AEnvPOS.showCenterWindow(EnvPOS.getWindow(WindowNo), para);
			if (!para.isOK()) {
				return null;
			}
		}

		//	execute
		ProcessCtlPOS worker = new ProcessCtlPOS((IProcessUI)parent, WindowNo, pi, trx);
		if (parent != null)
		{
			//asynchrous
			worker.start();
		}
		else
		{
			//synchrous
			worker.run();
		}
		return worker;
	}	//	execute
	
	/**
	 *	Async Process - Do it all.
	 *  <code>
	 *	- Get Instance ID
	 *	- Get Parameters
	 *	- execute (lock - start process - unlock)
	 *  </code>
	 *  Creates a ProcessCtl instance, which calls
	 *  lockUI and unlockUI if parent is a ASyncProcess
	 *  <br>
	 *	Called from ProcessDialog.actionPerformed
	 *
	 *  @param parent ASyncProcess & Container
	 *  @param WindowNo window no
	 *  @param paraPanel Process Parameter Panel
	 *  @param pi ProcessInfo process info
	 *  @param trx Transaction
	 *  @return worker started ProcessCtl instance or null for workflow
	 */
	public static ProcessCtlPOS process(ASyncProcess parent, int WindowNo, ProcessController parameter, ProcessInfo pi, Trx trx)
	{
		log.fine("WindowNo=" + WindowNo + " - " + pi);

		MPInstance instance = null; 
		try 
		{ 
			instance = new MPInstance(Env.getCtx(), pi.getAD_Process_ID(), pi.getRecord_ID()); 
		} 
		catch (Exception e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		} 
		catch (Error e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		}
		if (!instance.save())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			pi.setError (true);
			return null;
		}
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Get Parameters
		//	BR [ 265 ]
		if (parameter != null) {
			if (parameter.saveParameters() != null) {
				return null;
			}
		}

		//	execute
		ProcessCtlPOS worker = new ProcessCtlPOS((IProcessUI)parent, WindowNo, pi, trx);
		if (parent != null)
		{
			worker.start();
		}
		else
		{
			//synchrous
			worker.run();
		}
		return worker;
	}	//	execute

}
