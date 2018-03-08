package com.code73.function.dto;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.code73.function.response.Link;
import com.sun.jersey.server.linking.Ref;
import com.sun.jersey.server.linking.Ref.Style;

@DynamoDBTable(tableName = "Person")
@XmlRootElement(name = "person")
public class Person {
	
	private String username;
	
	private String email;
	
	private String name;
	
	private String lastname;
	
	private Mobile mobile;
	
	private Password password;
	
	private Role role;
	
	@Ref(value="people/${instance.username}", style=Style.ABSOLUTE)
	@DynamoDBIgnore
	private transient URI href;
	
	private transient Link link = new Link() {
		
		@Override
		public URI getHref() {
			return href;
		}
		
		@Override
		public String getRel() {
			return "self";
		}
	};
	
	/**
	 * @return the username
	 */
	@DynamoDBHashKey(attributeName = "username")
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the email
	 */
	@DynamoDBAttribute(attributeName = "email")
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the name
	 */
	@DynamoDBAttribute(attributeName = "name")
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
	 * @return the lastname
	 */
	@DynamoDBAttribute(attributeName = "lastname")
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * @return the mobile
	 */
	public Mobile getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * @return the password
	 */
	public Password getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(Password password) {
		this.password = password;
	}
	
	/**
	 * @return the link
	 */
	@DynamoDBIgnore
	public Link getLink() {
		return link;
	}
	
	public void updateFrom(Person that){
		setEmail(that.getEmail());
		setLastname(that.getLastname());
		setName(that.getName());
		setPassword(that.getPassword());
		setRole(that.getRole());
		setMobile(that.getMobile());
	}
	
	

}
