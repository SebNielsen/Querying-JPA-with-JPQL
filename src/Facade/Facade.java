/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.ClassicOrder;
import Entity.Customer;
import Entity.Employee;
import java.util.List;
import javax.persistence.criteria.Order;

/**
 *
 * @author sebastiannielsen
 */
public interface Facade {
    
    public Employee createEmployee(int number, String lastname, String firstname, String extension, String email, String title);
    public Customer updateCustomer(Customer cust);
    
    public long getEmployeeCount();
    public List<Customer> getCustomerInCity(String id);
    public List<Object[]> getEmployeMaxCustomers();
    
    public List<ClassicOrder> getOrdersOnHold();
    public List<ClassicOrder> getOrdersOnHold(int customerNumber);
    
}
