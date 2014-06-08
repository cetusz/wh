package com.my.mybatis.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Sort {
	Map<String, Boolean> sortsMap = new HashMap<String, Boolean>();

	public Sort()
	{
	}
	
	public Sort(String columnName , boolean isDesc)
	{
		this.setTableSort(columnName, isDesc);
	}
	public Map<String, Boolean> getSortsMap() {
		return sortsMap;
	}

	public void setSortsMap(Map<String, Boolean> sortsMap) {
		this.sortsMap = sortsMap;
	}
	
	public void setTableSort(String columnName, boolean isDesc)
	{
		sortsMap.put(columnName, isDesc);
	}
	
	public String getTabelSort()
	{
		if(sortsMap == null || sortsMap.isEmpty())
		{
			return null;
		}
		else
		{
			Iterator<Entry<String, Boolean>> it = sortsMap.entrySet().iterator();
			StringBuffer sortBuf = new StringBuffer();
			while(it.hasNext())
			{
				Entry<String, Boolean> entry = it.next();
				sortBuf.append(",");
				sortBuf.append(entry.getKey()).append(" ");
				if(entry.getValue())
				{
					sortBuf.append("DESC");
				}
				else
				{
					sortBuf.append("ASC");
				}
			}
			
			return sortBuf.substring(1);
		}
	}
}
