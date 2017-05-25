package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;

public class MDocTypePOS extends MDocType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5361654522956185417L;

	public MDocTypePOS(Properties ctx, int C_DocType_ID, String trxName) {
		super(ctx, C_DocType_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDocTypePOS(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDocTypePOS(Properties ctx, String DocBaseType, String Name,
			String trxName) {
		super(ctx, DocBaseType, Name, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Return the first Doc Type for this Document subtype sales order
	 * @param orgId
	 * @param docBaseType
	 * @param docSubTypeSO
     * @return
     */
	static public int getDocTypeBaseOnSubType(int orgId , String docBaseType , String docSubTypeSO)
	{
		Integer documentId = null;
		for (MDocType docType : MDocType.getOfDocBaseType(Env.getCtx() , docBaseType))
		{
			if (docSubTypeSO != null
					&& docSubTypeSO.equals(docType.getDocSubTypeSO())
					&& docType.getAD_Org_ID() == orgId)
				documentId = docType.get_ID();
		}

		for (MDocType docType : MDocType.getOfDocBaseType(Env.getCtx() , docBaseType))
		{
			if (docSubTypeSO != null
					&& docSubTypeSO.equals(docType.getDocSubTypeSO()))
				documentId = docType.get_ID();
		}
		return documentId;
	}
	
	/**
	 * Get document type based on organization
	 *
	 * @param docBaseType Document Type Base
	 * @param AD_Org_ID   Organization ID
	 * @return C_DocType_ID
	 */
	static public int getDocType(String docBaseType, int AD_Org_ID) {
		MDocType[] docs = MDocType.getOfDocBaseType(Env.getCtx(), docBaseType);

		if (docs == null || docs.length == 0) {
			throw new AdempiereException("@C_DocType_ID@ @NotFound@ " + docBaseType);
		} else {
			for (MDocType doc : docs)
				if (doc.getAD_Org_ID() == AD_Org_ID)
					return doc.getC_DocType_ID();

			return docs[0].getC_DocType_ID();
		}
	}
}
