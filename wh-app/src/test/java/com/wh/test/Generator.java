package com.wh.test;

public class Generator {

	public static void main(String[] args) {
		String[] cols = {
				"id",
				"userName",
				"password",
				"createTime",
				"lastUpdateTime"
		};
		printSql(cols);
		System.out.println("");
		System.out.println("+++++++columns+++++++");
		printcolums(cols);
		System.out.println("");
		System.out.println("+++++++param+++++++");
		printcolumsParam(cols);
		System.out.println("");
		System.out.println("+++++++param+++++++");
		printparamSql(cols);
		System.out.println("");
		System.out.println("+++++++where+++++++");
		printtestSql(cols);
		System.out.println("+++++++printResult++++++");
		printResult(cols);
		System.out.println("+++++++printUpdateSql++++++");
		printUpdateParamSql(cols);
		
	}
	
	private static void printResult(String[] cols){
	  for(int index=0,len = cols.length;index<len;index++){
		  System.out.println("<result property=\""+cols[index]+"\" column=\""+cols[index]+"\"/>");
	  }
	}
	
	private static void printSql(String[] cols){
		 for(int index=0,len = cols.length;index<len;index++){
			  if(index>0)
				  System.out.print(",  ");
			  System.out.print("t."+cols[index]);
		  }
	}
	
	private static void printcolums(String[] cols){
		 for(int index=0,len = cols.length;index<len;index++){
			  if(index>0)
				  System.out.print(",  ");
			  System.out.print(cols[index]);
		  }
	}
	
	private static void printcolumsParam(String[] cols){
		 for(int index=0,len = cols.length;index<len;index++){
			  if(index>0)
				  System.out.print(",  ");
			  System.out.print("#{"+cols[index]+"}");
		  }
	}
	
	private static void printparamSql(String[] cols){
		 for(int index=0,len = cols.length;index<len;index++){
			  if(index>0)
				  System.out.print(",  ");
			  System.out.print("t."+cols[index]);
		  }
	}
	private static void printUpdateParamSql(String[] cols){
		for(int index=0,len = cols.length;index<len;index++){
			  System.out.println("<if test=\""+cols[index]+"!= null and "+cols[index]+"!= ''\" >t."+cols[index]+"=#{"+cols[index]+"},</if>");
		  }
	}
	
	
	private static void printtestSql(String[] cols){
		  for(int index=0,len = cols.length;index<len;index++){
			  System.out.println("<if test=\""+cols[index]+"!= null and "+cols[index]+"!= ''\" > AND t."+cols[index]+"=#{"+cols[index]+"}</if>");
		  }
	}

}
