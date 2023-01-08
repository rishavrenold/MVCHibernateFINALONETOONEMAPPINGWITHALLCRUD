package com.journaldev.spring.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.journaldev.spring.model.*;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;


@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public int createCategoryCode()
	{
		Session session = this.sessionFactory.getCurrentSession();
		int max=Integer.MIN_VALUE;
		List<Category> categoryList = session.createQuery("from Category").list();
		for(Category c:categoryList)
		{
			if(max<c.getCategory_code())
			{
				max=c.getCategory_code();
			}
		}
		return max==Integer.MIN_VALUE?0:max;
	}

	public Category addCategory(Person p)
	{
		Category category= new Category();
		Session session = this.sessionFactory.getCurrentSession();
		category.setCategory_name(p.getCategory());
		category.setCategory_code(createCategoryCode() + 1000);
		session.persist(category);
		return category;
	}
	@Transactional
	public Price addPrice(Person pp)
	{
		Price price=new Price();
		Session session = this.sessionFactory.getCurrentSession();
		price.setPrice(pp.getPrice());
		price.setCurrency(pp.getCurrency());
		session.persist(price);
		return price;
	}
	@Transactional
	public Stock addStock(Person pp)
	{
		Stock stock=new Stock();
		Session session = this.sessionFactory.getCurrentSession();
		stock.setInventory(pp.getInventory());
		stock.setLocation(pp.getLocation());
		session.persist(stock);
		return stock;
	}
	@Transactional
	public Product addProduct(Person p)
	{
			Session session = this.sessionFactory.getCurrentSession();
			Product product = new Product();
			product.setProduct_code(p.getProduct());
			product.setProduct_description(p.getProduct_description());
			List<Category> categoryList = session.createQuery("from Category").list();
			Category category = new Category();
			for (Category c : categoryList) {
				if (!c.getCategory_name().equalsIgnoreCase(p.getCategory())) {
					category = null;
				} else if (c.getCategory_name().equalsIgnoreCase(p.getCategory())) {
					category = c;
					break;
				}
			}
			if (category == null) {
				category = addCategory(p);
			}

			product.setCategory(category);
			product.setPrice(addPrice(p));
			product.setStock(addStock(p));
			session.persist(product);
			return product;
	}
	@Override
	@Transactional
	public void addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		Product pro=addProduct(p);
		System.out.println("INSIDE ADD PERSON PRODUCT LEKE AAE HAI USKA ID="+pro.getProduct_id());
		p.setPro(pro);
		session.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
	}

//---------------------------------------------------------------------Update --------------------------------------------------------------------------

	@Transactional
	public Product updateProduct(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product=null;
		List<Product> pl=session.createQuery("from Product").list();
		for(Product pro:pl)
		{
			if(pro.getProduct_code().equalsIgnoreCase(p.getProduct()) && pro.getPrice().getCurrency().equalsIgnoreCase(p.getCurrency())
					&& pro.getStock().getLocation().equalsIgnoreCase(p.getLocation()))
			{
				product=pro;
			}
		}
		if(product==null)
		{
			product=addProduct(p);
			return product;
		}
		System.out.println("LINE 155 PRODUCT JO UPDATE HONE AYA HAI:-"+product.getProduct_code());
		//Case1:jaab product mei jaab price and stock mei change hoga taab
		//Case2: jaab product fields mei change
		product.setProduct_code(p.getProduct());
		if(!product.getProduct_description().equalsIgnoreCase(p.getProduct_description()))
		{
			product.setProduct_description(p.getProduct_description());
		}
		List<Category> cl=session.createQuery("from Category").list();
		Category category=null;
		for(Category cr:cl)
		{
			if(cr.getCategory_name().equalsIgnoreCase(p.getCategory()))
			{
				category=cr;
				break;
			}
		}
		Category product_category=product.getCategory();
		if(!category.equals(product_category)) {
			product.setCategory(category);
		}

		Price price=product.getPrice();
		if(price.getCurrency().equalsIgnoreCase(p.getCurrency())) {
			if (price != null) {
				price.setPrice(p.getPrice());
				product.setPrice(price);
			}
		}
		else {
			price=null;
		}

		Stock stock=product.getStock();
		if(stock.getLocation().equalsIgnoreCase(p.getLocation())) {
			if (stock != null) {
				stock.setInventory(p.getInventory());
				product.setStock(stock);
			}
		}else {
			stock=null;
		}
		session.merge(product);
		return product;
	}
	@Override
	@Transactional
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product=updateProduct(p);
		List<Person> pl=session.createQuery("From Person").list();
		Person person=null;
		for(Person pr:pl) {
			if (pr.getProduct().equalsIgnoreCase(product.getProduct_code()) && pr.getCurrency().equalsIgnoreCase(product.getPrice().getCurrency())
					&& pr.getLocation().equalsIgnoreCase(product.getStock().getLocation()))
			{
				person=pr;
			}
		}
		System.out.println("PRODUCT KON SA AA RAHA HAI "+product.getProduct_code()+" "+product.getStock().getLocation()+" "+product.getPrice().getCurrency());
		System.out.println("PERSON KON SA AA RAHA HAI "+person);
		if(person==null)
		{
			person=new Person();
			if(!product.getProduct_code().equalsIgnoreCase(p.getProduct()))
			{
				person.setProduct(product.getProduct_code());
			}if(!product.getProduct_description().equalsIgnoreCase(p.getProduct_description()))
		{
			person.setProduct_description(product.getProduct_description());
		}
			if(!product.getCategory().getCategory_name().equalsIgnoreCase(p.getCategory()))
			{
				person.setCategory(product.getCategory().getCategory_name());
			}
			if(!product.getStock().getLocation().equalsIgnoreCase(p.getLocation()))
			{
				person.setLocation(product.getStock().getLocation());
			}
			if(product.getStock().getInventory()!=p.getInventory())
			{
				person.setInventory(product.getStock().getInventory());
			}
			if(product.getPrice().getCurrency().equalsIgnoreCase(p.getCurrency()))
			{
				person.setCurrency(product.getPrice().getCurrency());
			}
			if(product.getPrice().getPrice()!=p.getPrice())
			{
				person.setPrice(product.getPrice().getPrice());
			}
			person.setPro(product);
			session.persist(person);
		}
		else {
			if(!product.getStock().getLocation().equalsIgnoreCase(p.getLocation()))
			{
				person.setLocation(product.getStock().getLocation());
			}
			if(product.getStock().getInventory()!=p.getInventory())
			{
				person.setInventory(product.getStock().getInventory());
			}
			if(product.getPrice().getCurrency().equalsIgnoreCase(p.getCurrency()))
			{
				person.setCurrency(product.getPrice().getCurrency());
			}
			if(product.getPrice().getPrice()!=p.getPrice())
			{
				person.setPrice(product.getPrice().getPrice());
			}
			person.setPro(product);
			session.merge(person);
		}
		logger.info("Person updated successfully, Person Details="+p);
	}
//---------------------------------------------------------------List fetching--------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() throws NumberFormatException {
		Session session = this.sessionFactory.getCurrentSession();
	List<Person> personsList = session.createQuery("from Person order by Product asc").list();
		for(Object p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}
//----------------------------------------------------------------Getting by ID---------------------------------------------------------------------------
	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

//---------------------------------------------------------------------Removal-------------------------------------------------------------------------------
	@Override
	@Transactional
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
			session.delete(p);

		logger.info("Product deleted successfully, person details="+p);
	}
}
