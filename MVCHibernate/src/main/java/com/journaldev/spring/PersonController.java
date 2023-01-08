package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.Product;
import com.journaldev.spring.service.PersonService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String getPerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}
	@Transactional
	@RequestMapping(value= "/product/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p) {
		List<Person> al= this.personService.listPersons();
		Person person=null;
		for(Person pr:al) {
			if (pr.getProduct().equalsIgnoreCase(p.getProduct()) && pr.getCurrency().equalsIgnoreCase(p.getCurrency())
					&& pr.getLocation().equalsIgnoreCase(p.getLocation()))
			{
				person=pr;
				break;
			}
		}
		if(person==null)
		{
			person=new Person();
		}
		person.setProduct(p.getProduct());
		person.setProduct_description(p.getProduct_description());
		person.setCategory(p.getCategory());
		person.setPrice(p.getPrice());
		person.setCurrency(p.getCurrency());
		person.setInventory(p.getInventory());
		person.setLocation(p.getLocation());
		if(person.getId()==0)
		{
		this.personService.addPerson(p);
	} else {
			this.personService.updatePerson(person);
		}
		return "redirect:/product";
	}

	@Transactional
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/product";
    }

	@Transactional
	@RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }


}
