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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Env;

/**
 * @contributor Ing. Victor Suarez - victor.suarez.is@gmail.com 
 * 		- Migration POS from ADempiere 3.9.0 to iDempiere ERP Plugin.
 */
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
