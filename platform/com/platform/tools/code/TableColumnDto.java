package com.platform.tools.code;

/**
 * 数据库表对应的列描述
 * @author DHJ
 */
public class TableColumnDto {

	private String table_name ; // 表名称
	
	private String table_desc ; // 表描述
	
	private String column_name ; // 列名称
	private String column_name_upperCaseFirstOne ; // 列名称，首字母大写
	
	private String column_type ; // 列类型
	
	private String column_desc ; // 列描述

	private String column_length ; // 列最大长度，针对字符型
	
	private String column_className; // 对应的java数据类型
	
	//add zw
	private String clm_type;//列类型
	
	public String getColumn_name_upperCaseFirstOne() {
		return column_name_upperCaseFirstOne;
	}
	public void setColumn_name_upperCaseFirstOne(String column_name_upperCaseFirstOne) {
		this.column_name_upperCaseFirstOne = column_name_upperCaseFirstOne;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_desc() {
		return table_desc;
	}
	public void setTable_desc(String table_desc) {
		this.table_desc = table_desc;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getColumn_type() {
		return column_type;
	}
	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}
	public String getColumn_desc() {
		return column_desc;
	}
	public void setColumn_desc(String column_desc) {
		this.column_desc = column_desc;
	}
	public String getColumn_length() {
		return column_length;
	}
	public void setColumn_length(String column_length) {
		this.column_length = column_length;
	}
	public String getColumn_className() {
		return column_className;
	}
	public void setColumn_className(String column_className) {
		column_className = column_className.substring(column_className.lastIndexOf(".") + 1);
		this.column_className = column_className;
	}
	/**
	 * @return the clm_type
	 */
	public String getClm_type() {
		return clm_type;
	}
	/**
	 * @param clm_type the clm_type to set
	 */
	public void setClm_type(String clm_type) {

		//这里只截取前3个字符，因为pg、mysql等数据库中 数据类型可以用前3个字符进行类型区别
		if (clm_type!=null && clm_type.length()>=3) {
			this.clm_type = clm_type.substring(0, 3);
		}else {
			this.clm_type = clm_type;
		}
		
	}
	
	public boolean TypeIsChar(){
		
		return clm_type.equals("cha")?true:false;
	}
	
	public boolean TypeIsText(){
		
		return clm_type.equals("tex")?true:false;
	}
	
	public boolean TypeIsDate(){
		
		return clm_type.equals("tim")?true:false;
	}
	
	public boolean TypeIsBool(){
		return clm_type.equals("boo")?true:false;
	}
	
	public static void main(String args[]){
		String str = "12345";
		System.out.println(str.length()+"---"+str.substring(0,3));
	}
	
}
