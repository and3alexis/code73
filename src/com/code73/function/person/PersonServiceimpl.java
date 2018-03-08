package com.code73.function.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code73.function.dto.Person;

@Service
public class PersonServiceimpl implements PersonService{
	
	private PersonDAO personDAO;
	
	@Autowired
	public PersonServiceimpl(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public void createPerson(Person person){
		personDAO.createPerson(person);
	}

	public Person findPerson(String username){
		Person person = personDAO.findPerson(username);
		return person;
	}

	public byte deletePerson(String username){
		Person currentPerson = personDAO.findPerson(username);
		if(currentPerson == null){
			return 0;
		}
		personDAO.deletePerson(currentPerson);
		return 1;
	}

	@Override
	public byte updatePersonByUsername(String username, Person person) {
		Person currentPerson = personDAO.findPerson(username);
		if(currentPerson == null){
			personDAO.createPerson(person);
			return 0;
		}
		personDAO.updatePerson(username, person);
		return 1;
	}

}
