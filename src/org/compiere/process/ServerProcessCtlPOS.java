package org.compiere.process;

import org.compiere.model.MPInstance;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

public class ServerProcessCtlPOS extends ServerProcessCtl {
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ServerProcessCtlPOS.class);
	
	/** Parent */
	ASyncProcess m_parent;
	/** Process Info */
	ProcessInfo processInfo;
	private Trx trx;
	private boolean isServerProcess = false;

	public ServerProcessCtlPOS(ProcessInfo pi, Trx trx) {
		super(pi, trx);
		// TODO Auto-generated constructor stub
	}
	
	/**************************************************************************
	 *  Constructor
	 *  @param parent Container & ASyncProcess
	 *  @param pi Process info
	 *  @param trx Transaction
	 */
	public ServerProcessCtlPOS (ASyncProcess parent, ProcessInfo pi, Trx trx) {
		super(pi, trx);
		m_parent = parent;
		processInfo = pi;
		this.trx = trx;	//	handled correctly
	}   //  ProcessCtl
	
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
	 *  @param pi ProcessInfo process info
	 *  @param trx Transaction
	 *  @return worker started ProcessCtl instance or null for workflow
	 */
	public static ServerProcessCtlPOS process (ASyncProcess parent, ProcessInfo pi, Trx trx)
	{
		log.fine("ServerProcess - " + pi);

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

		//	execute
		ServerProcessCtlPOS worker = new ServerProcessCtlPOS(parent, pi, trx);
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

}
