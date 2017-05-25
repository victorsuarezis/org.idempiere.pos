package org.compiere.interfaces;

public interface ServerPOS extends Server {
	
	   /**
	    * Cash Reset
	    * @param tableName table name
	    * @param Record_ID record or 0 for all
	    * @return number of records reset    */
	   public int cacheReset( String tableName,int Record_ID );
}
