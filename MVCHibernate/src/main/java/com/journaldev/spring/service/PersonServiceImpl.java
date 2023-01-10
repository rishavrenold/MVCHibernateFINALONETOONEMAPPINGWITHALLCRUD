package com.journaldev.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.Product;

@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {
		if((p.getProduct()=="" || p.getProduct()==null )&& (p.getCurrency()==""||p.getCurrency()==null)
				&& p.getPrice()==0 && p.getInventory()==0 && (p.getLocation()==""||p.getLocation()==null) && (p.getCategory()==""||p.getCategory()==null) )
		{
			listPersons();
		}
		else {
			this.personDAO.addPerson(p);
		}
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}


	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}

}
