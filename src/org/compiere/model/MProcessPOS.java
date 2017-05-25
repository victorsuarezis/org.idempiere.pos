package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.Env;

import java.util.List;

public class MProcessPOS extends MProcess {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7030722866248729014L;
	
	/**	Cache						*/
	private static CCache<Integer,MProcess>	s_cache	= new CCache<Integer,MProcess>(Table_Name, 20);
	/**	Cache for parameters		*/
	private static CCache<String, MProcessPara[]>	s_cacheASPParameters = new CCache<String, MProcessPara[]>(I_AD_Process_Para.Table_Name, 20);

	public MProcessPOS(Properties ctx, int AD_Process_ID, String trxName) {
		super(ctx, AD_Process_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MProcessPOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get based on java class
	 * @param processClass
	 * @return
     */
	public static MProcess getUsingJavaClass(final Class<?> processClass)
	{
		String className = processClass.getCanonicalName();
		if (className == null)
			return null;

		return new Query(Env.getCtx() , MProcess.Table_Name , MProcess.COLUMNNAME_Classname + "=?" , null)
						.setParameters(className)
						.first();
	}
	
	/**
	 * Get ASP Parameter (make a query if it is not exists in Cache)
	 * @return
	 */
	public MProcessPara[] getASPParameters() {
		MClient client = MClient.get(Env.getCtx());
		String key = getAD_Process_ID() + "|" + client.getAD_Client_ID();
		MProcessPara[] retValue = s_cacheASPParameters.get (key);
		if (retValue != null)
			return retValue;
		//	Get where clause
		String ASPFilter = null;
		if (client.isUseASP()) {
			ASPFilter = 
				//	Just ASP subscribed process parameters for client "
				"("
				+ "	EXISTS(SELECT 1 FROM ASP_Process p "
				+ "					INNER JOIN ASP_Process_Para pp ON(pp.ASP_Process_ID = p.ASP_Process_ID) "
				+ "					INNER JOIN ASP_Level l ON(l.ASP_Level_ID = p.ASP_Level_ID) "
				+ "					INNER JOIN ASP_ClientLevel cl ON(cl.ASP_Level_ID = l.ASP_Level_ID) "
				+ "				WHERE pp.AD_Process_Para_ID = AD_Process_Para.AD_Process_Para_ID "
				+ "				AND cl.AD_Client_ID = " + client.getAD_Client_ID()
				+ "				AND pp.IsActive = 'Y' "
				+ "				AND p.IsActive = 'Y' "
				+ "				AND l.IsActive = 'Y' "
				+ "				AND cl.IsActive = 'Y' "
				+ "				AND pp.ASP_Status = 'S') "	//	Show
				+ "OR "
				//	+ show ASP exceptions for client
				+ "	EXISTS(SELECT 1 FROM ASP_ClientException ce "
				+ "				WHERE ce.AD_Process_Para_ID = AD_Process_Para.AD_Process_Para_ID "
				+ "				AND ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "				AND ce.IsActive = 'Y' "
				+ "				AND ce.AD_Process_Para_ID IS NOT NULL "
				+ "				AND ce.AD_Tab_ID IS NULL "
				+ "				AND ce.AD_Field_ID IS NULL "
				+ "				AND ce.ASP_Status = 'S')"	//	Show
				+ ") "
				//	minus hide ASP exceptions for client
				+ "AND EXISTS(SELECT 1 FROM ASP_ClientException ce "
				+ "				WHERE ce.AD_Process_Para_ID = AD_Process_Para.AD_Process_Para_ID "
				+ "				AND ce.AD_Client_ID = " + client.getAD_Client_ID()
				+ "				AND ce.IsActive = 'Y' "
				+ "				AND ce.AD_Process_Para_ID IS NOT NULL "
				+ "				AND ce.AD_Tab_ID IS NULL "
				+ "				AND ce.AD_Field_ID IS NULL "
				+ "				AND ce.ASP_Status = 'H')";	//	Hide
		}
		retValue = getParameters(ASPFilter);
		if (retValue.length != 0)
			s_cacheASPParameters.put(key, retValue);
		//	Default Return
		return retValue;
	}
	
	/**
	 * Get Parameter with optional where clause
	 * @param optionalWhereClause
	 * @return
	 */
	public MProcessPara[] getParameters(String optionalWhereClause) {
		MProcessPara[] retValue = null;
		StringBuffer whereClause = new StringBuffer(MProcessPara.COLUMNNAME_AD_Process_ID + "=?");
		
		//	Validate where
		if(optionalWhereClause != null
				&& optionalWhereClause.trim().length() > 0)
			whereClause.append(" AND ").append(optionalWhereClause);
		//	
		List<MProcessPara> list = new Query(getCtx(), I_AD_Process_Para.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(get_ID())
			.setOnlyActiveRecords(true)
			.setOrderBy(MProcessPara.COLUMNNAME_SeqNo)
			.list();
		//
		retValue = new MProcessPara[list.size()];
		list.toArray(retValue);
		//	Default Return
		return retValue;
	}
	
	/**
	 * 	Get MProcess from Cache
	 *	@param ctx context
	 *	@param AD_Process_ID id
	 *	@return MProcess
	 */
	public static MProcessPOS get (Properties ctx, int AD_Process_ID)
	{
		Integer key = new Integer (AD_Process_ID);
		MProcessPOS retValue = (MProcessPOS) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MProcessPOS (ctx, AD_Process_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

}
