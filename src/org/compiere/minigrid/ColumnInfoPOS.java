package org.compiere.minigrid;

public class ColumnInfoPOS extends ColumnInfo {
	
	private int      	m_DisplayType;
	private boolean		m_isVisible;

	public ColumnInfoPOS(String colHeader, String colSQL, Class<?> colClass) {
		super(colHeader, colSQL, colClass);
		// TODO Auto-generated constructor stub
	}

	public ColumnInfoPOS(String colHeader, String colSQL, Class<?> colClass,
			String keyPairColSQL) {
		super(colHeader, colSQL, colClass, keyPairColSQL);
		// TODO Auto-generated constructor stub
	}

	public ColumnInfoPOS(String colHeader, String colSQL, Class<?> colClass,
			boolean readOnly, boolean colorColumn, String keyPairColSQL) {
		super(colHeader, colSQL, colClass, readOnly, colorColumn, keyPairColSQL);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *  Create Info Column
	 *
	 *  @param colHeader Column Header
	 *  @param colSQL    SQL select code for column
	 *  @param colClass  class of column - determines display
	 *  @param displayType
	 *  @param readOnly  column is read only
	 *  @param colorColumn   if true, value of column determines foreground color
	 *  @param keyPairColSQL  SQL select for the ID of the for the displayed column
	 *  @param visible  if true, the column will be visible.  False, it will be hidden.
	 */
	public ColumnInfoPOS (String colHeader, String colSQL, Class<?> colClass, int displayType,
		boolean readOnly, boolean colorColumn, String keyPairColSQL, boolean visible)
	{
		super(colHeader, colSQL, colClass, readOnly, colorColumn, keyPairColSQL);
		setColHeader(colHeader);
		setColSQL(colSQL);
		setColClass(colClass);
		setReadOnly(readOnly);
		setColorColumn(colorColumn);
		setKeyPairColSQL(keyPairColSQL);
		setDisplayType(displayType);
		setVisibility(visible);
	}   //  ColumnInfo
	
	/**
	 * 	Set Display Type
	 *	@param display Type
	 */
	public void setDisplayType(int displayType)
	{
		m_DisplayType = displayType;
	}
	/**
	 * 	Display Type
	 *	@return display Type
	 */
	public int getDisplayType()
	{
		return m_DisplayType;
	}
	
	/**
	 * @param m_isVisible the m_isVisible to set
	 */
	public void setVisibility(boolean m_isVisible) {
		this.m_isVisible = m_isVisible;
	}

	/**
	 * @return the m_isVisible
	 */
	public boolean getVisibility() {
		return m_isVisible;
	}
}
