package com.code73.function.person;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.code73.function.dto.Person;

@Repository
public class PersonDAOImpl implements PersonDAO{
	
	private AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
	
	@Override
	public void createPerson(Person person){
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		dynamoDBMapper.save(person);
	}
	
	@Override
	public Person findPerson(String username){
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		Person person = dynamoDBMapper.load(Person.class, username);
		return person;
	}
	
	@Override
	public void deletePerson(String username){
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		Person person = dynamoDBMapper.load(Person.class, username);
		dynamoDBMapper.delete(person);
	}
	
	@Override
	public void deletePerson(Person person){
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		dynamoDBMapper.delete(person);
	}
	
	@Override
	public void updatePerson(String username, Person person) {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
		Person currentPerson = dynamoDBMapper.load(Person.class, username);
		currentPerson.updateFrom(person);
		dynamoDBMapper.save(currentPerson);
	}

}
