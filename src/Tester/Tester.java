/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import Control.Control;
import Entity.ClassicOrder;
import Entity.Customer;
import Facade.Facade;
import java.util.List;
import javax.persistence.criteria.Order;

/**
 *
 * @author sebastiannielsen
 */
public class Tester {
    
    public static void main(String[] args) {
        test();
    }
    
    public static void test(){
        
        Facade facade = new Control();
        //facade.createEmployee(1608,"Hansen", "Hans", "x434234", "hans@hotmail.com", "Sales Rep");
        System.out.println("");
        System.out.println("<------- Find total amount of employees ------->");
        System.out.println(facade.getEmployeeCount());
        System.out.println("");
        
        
        System.out.println("<------- Find Customers who live en Barcelona ------->");
        List<Customer> customers = facade.getCustomerInCity("Barcelona");
        for(Customer cus : customers){
            System.out.println(cus.getCustomerName());
        }
        System.out.println("");
        
        
        System.out.println("<------- Find Employee with most Customers ------->");
        List<Object[]> result = facade.getEmployeMaxCustomers();
        result.stream().forEach((record) ->{
            String firstName = (String) record[0];
            String lastName = (String) record[1];
            Integer amount  = (Integer) record[2];
            System.out.println("Name: " + firstName + " " +lastName + " Number of Customers:" + amount);
        });
        System.out.println("");
        
        System.out.println("<------- Find orders with status 'On Hold' ------->");
        List<ClassicOrder> orders = facade.getOrdersOnHold();
        for(ClassicOrder o : orders){
            System.out.println("OrderNr: " + o.getOrderNumber() + " Status: " + o.getStatus());
        }
        System.out.println("");
        
        System.out.println("<------- Find orders with status'On Hold' for customer 144 ------->");
        List<ClassicOrder> ordersforCustomer = facade.getOrdersOnHold(144);
        for(ClassicOrder o : ordersforCustomer){
            System.out.println("OrderNr: " + o.getOrderNumber() + " Status: " + o.getStatus());
        }
        
    }
    
}
