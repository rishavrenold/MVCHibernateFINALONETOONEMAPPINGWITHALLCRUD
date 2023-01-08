package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.Product;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public void removePerson(int id);

	public Person getPersonById(int id);

}
