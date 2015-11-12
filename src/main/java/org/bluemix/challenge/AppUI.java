package org.bluemix.challenge;

import javax.servlet.annotation.WebServlet;

import org.bluemix.challenge.backend.Customer;
import org.bluemix.challenge.pages.ChartsPage;
import org.bluemix.challenge.pages.CustomersPage;
import org.bluemix.challenge.pages.LoginPage;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;


/**
 * Main UI class
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("org.bluemix.challenge.MyAppWidgetset")
public class AppUI extends UI
{
	
	private Navigator nav;
	private Customer selectedCustomer;
	
	public Navigator getNavigator()
	{
		return this.nav;
	}
	
	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		this.getPage().setTitle("IBM Challenge");
		this.selectedCustomer = null;
		
		this.nav = new Navigator(this, this);
				
		nav.addView("", new LoginPage(this));	
		nav.addView("CUSTOMERS", new CustomersPage(this));	
		nav.addView("AGE", new ChartsPage(this, "Age"));
		nav.addView("SEX", new ChartsPage(this, "Sex"));
		nav.addView("INCOME", new ChartsPage(this, "Income"));
		
		System.out.println("Welcome to BlueMix Challenge Log");
		
	}
	
	public Customer getSelectedCustomer()
	{
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer)
	{
		this.selectedCustomer = selectedCustomer;
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = AppUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet
	{
	}
}
