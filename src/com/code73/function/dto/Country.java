package com.code73.function.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Country {
	
	private String numericCode;
	
	private String name;
	
	private String callingCode;
	
	/**
	 * @return the numericCode
	 */
	public String getNumericCode() {
		return numericCode;
	}
	/**
	 * @param numericCode the numericCode to set
	 */
	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the callingCode
	 */
	public String getCallingCode() {
		return callingCode;
	}
	/**
	 * @param callingCode the callingCode to set
	 */
	public void setCallingCode(String callingCode) {
		this.callingCode = callingCode;
	}

}
