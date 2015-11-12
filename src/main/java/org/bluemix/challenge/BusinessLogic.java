package org.bluemix.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bluemix.challenge.backend.Customer;
import org.bluemix.challenge.backend.Customer.Gender;
import org.bluemix.challenge.backend.DummyDataService;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;

public class BusinessLogic
{
	
	private static final String USERNAME = "vaadin";
	private static final String PASSWORD = "bluemix";
	
	public static List<Customer> topAnnualIncome()
	{

		DummyDataService dds = DummyDataService.createDemoService();
		List<Customer> topIncomes = dds.findAll();
		Comparator<Customer> comparator = new Comparator<Customer>() 
		{
		    public int compare(Customer c1, Customer c2) 
		    {
		        return c2.getAnnualIncome() - c1.getAnnualIncome();
		    }
		};
		
		Collections.sort(topIncomes, comparator);
		ArrayList<Customer> topTenIncomes = new ArrayList<>();
		for (int j=0; j<10; j++)
		{
			Customer top = topIncomes.get(j);
			System.out.println(top.toString()+ " $" + String.valueOf(top.getAnnualIncome()));
			topTenIncomes.add(top);
		}
		

		return topTenIncomes;
		
	}
	
	public static List<Number> customersBySex()
	{
		int[] genders = new int[3];
		Float[] gendersPct = new Float[3];
		float total = 0;
		DummyDataService dds = DummyDataService.createDemoService();
		for (Customer customer : dds.findAll())
		{
			total += 1;
			Gender gender = customer.getGender();
			switch (gender)
			{
				case Male:
					genders[0] += 1;
					break;
				case Female:
					genders[1] += 1;
					break;
				case Other:
					genders[2] += 1;
					break;
				default:
					genders[2] += 1;
					break;
					
			}			
		}
		
		for (int k=0; k<3; k++)
		{
			gendersPct[k] = new Float((genders[k] / total) * 100.0);
		}
				
		return toList(gendersPct);
				
	}
	
	public static List<Number> customersByAge()
	{
		int[] ages = new int[9];
		DummyDataService dds = DummyDataService.createDemoService();
		for (Customer customer : dds.findAll())
        {
            int age = customer.getAge();
            if (age <= 12)
            {
            	ages[0] += 1;
            }
            else if (age > 12 && age<= 18)
            {
            	ages[1] += 1;
            }
            else if (age > 18 && age<= 26)
            {
            	ages[2] += 1;
            }
            else if (age > 26 && age<= 34)
            {
            	ages[3] += 1;
            }
            else if (age > 34 && age<= 42)
            {
            	ages[4] += 1;	
            }
            else if (age > 42 && age<= 50)
            {
            	ages[5] += 1;
            }
            else if (age > 50 && age<= 58)
            {
            	ages[6] += 1;
            }
            else if (age > 59 && age<= 66)
            {
            	ages[7] += 1;
            }
            else 
            {
            	ages[8] += 1;
            }
        }
				
		ArrayList<Number> agesList = new ArrayList<>();
		for (int k : ages)
		{
			agesList.add(new Integer(k));
		}
		
		return agesList;
		
	}
	
    public static Container.Indexed getCustomerBeans()
    {
        DummyDataService dds = DummyDataService.createDemoService();
        BeanItemContainer<Customer> customers =
                new BeanItemContainer<>(Customer.class);
        for (Customer customer : dds.findAll())
        {
            customers.addBean(customer);
        }
        return customers;
    }
	
	public static boolean checkUsername(String user, String pwd)
	{
		if (user.trim().equals(USERNAME) && pwd.trim().equals(PASSWORD))
		{
			return true;
		}
		return false;
	}
	
	private static ArrayList<Number> toList(Number[] a) 
	{
		ArrayList<Number> list = new ArrayList<>();
	    for (Number n : a) 
	    {
	        list.add(n); 
	    }
	    return list;
	}
	

}
