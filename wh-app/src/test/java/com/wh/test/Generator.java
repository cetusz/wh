package com.wh.test;

public class Generator {

	private static  void generateAccount(){
		String[] cols = {
				"id",
				"accountId",
				"chineseName",
				"funcintro",
				"wxcredit",
				"sinacredit",
				"isCrawler",
				"qrCodeUrl",
				"cateIds",
				"bizId",
				"isShielded",
				"createTime",
				"lastUpdateTime",
				"version"
		};
		printConfig(cols);
	}
	private static void generateUsr(){
		String[] cols = {
				"id",
				"userName",
				"password",
				"createTime",
				"lastUpdateTime"
		};
		printConfig(cols);
	}
	
	private static void generateCate(){
		String[] cols = {
				"id",
				"cateName",
				"orderNum",
				"version",
				"createTime",
				"lastUpdateTime"
				};
		printConfig(cols);
	}
	
	private static void printEasay(){
		String[] cols ={
				"id",
				"sourceId",
				"title",
				"content",
				"intro",
				"contentUrl",
				"categoryId",
				"categoryName",
				"accountId",
				"accountName",
				"openId",
				"isRecommanded",
				"createTime",
				"lastUpdateTime"
		};
		printConfig(cols);
	}
	public static void main(String[] args) {
		generateCate();
	}
	
	private static void printConfig(String[] cols){
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
