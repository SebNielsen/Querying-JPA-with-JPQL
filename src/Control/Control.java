/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entity.ClassicOrder;
import Entity.Customer;
import Entity.Employee;
import Entity.Office;
import Facade.Facade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.Order;

/**
 *
 * @author sebastiannielsen
 */
public class Control implements Facade {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("classicmodelsPU");
    EntityManager em = emf.createEntityManager();
    
    @Override
    public Employee createEmployee(int number,String lastname, String firstname, String extension, String email, String title) {
       Employee e = new Employee(number,lastname,firstname,extension,email,title);
       Office o = em.find(Office.class, "1");
       e.setOfficeCode(o);
       em.getTransaction().begin();
       em.persist(e);
       em.getTransaction().commit();
       return e;
    }

    @Override
    public Customer updateCustomer(Customer cust) {
        Customer updatedCustomer = em.find(Customer.class, cust.getCustomerNumber());
        updatedCustomer = cust;
        em.getTransaction().begin();
        em.persist(updatedCustomer);
        em.getTransaction().commit();
        return updatedCustomer;
    }
    
    @Override
    public long getEmployeeCount(){
        Query query = em.createQuery("SELECT COUNT(e.employeeNumber) FROM Employee e", Employee.class);
        long result = (long) query.getSingleResult();
        return result;
    }

    @Override
    public List<Customer> getCustomerInCity(String city) {
        Query query = em.createQuery("SELECT c FROM Customer c Where c.city = :city", Customer.class);
        query.setParameter("city", city);
        List<Customer> customers = query.getResultList();
        return customers;
    }

    @Override
    public List<Object[]> getEmployeMaxCustomers() {
        Query query = em.createQuery("SELECT e.firstName, e.lastName, SIZE(e.customerCollection) FROM Employee e ORDER BY SIZE(e.customerCollection) DESC", Employee.class);
        query.setMaxResults(1);
        List<Object[]> result = query.getResultList();
        return result;
    }

    @Override
    public List<ClassicOrder> getOrdersOnHold() {
        Query query = em.createQuery("SELECT o FROM ClassicOrder o Where o.status = 'On Hold'", ClassicOrder.class);
        List<ClassicOrder> orders = query.getResultList();
        return orders;
    }

    @Override
    public List<ClassicOrder> getOrdersOnHold(int customerNumber) {
        Customer cus = em.find(Customer.class, customerNumber);
        Query query = em.createQuery("SELECT co FROM ClassicOrder co WHERE co.status = 'On Hold' AND co.customerNumber = :customerNumber", ClassicOrder.class);
        query.setParameter("customerNumber", cus);
        List<ClassicOrder> orders = query.getResultList();
        return orders;
    }
    
}
