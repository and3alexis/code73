package com.code73.function.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Password {
	
	private String value;
	
	private String encrypt;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the encrypt
	 */
	public String getEncrypt() {
		return encrypt;
	}

	/**
	 * @param encrypt the encrypt to set
	 */
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	
	

}
