package org.bluemix.challenge.pages;

import org.bluemix.challenge.AppUI;
import org.bluemix.challenge.BusinessLogic;
import org.bluemix.challenge.backend.Customer;

import com.vaadin.data.Container;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CustomersPage extends TemplatePage implements View
{
	private Container.Indexed allCustomers;
	
	public CustomersPage(AppUI ui)
	{
		super(ui);
		this.allCustomers = BusinessLogic.getCustomerBeans();
	}
	
	private void selectCustomer(Customer customer)
	{
		this.mainUI.setSelectedCustomer(customer);
	}
	
	@Override
	public void enter(ViewChangeEvent event)
	{
		this.setupPage();
	}
		
    private Grid setupGrid()
    {
        final Grid grid = new Grid("Customer Data:", this.allCustomers);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.removeColumn("gender");
        grid.removeColumn("address");
        grid.removeColumn("city");
        grid.removeColumn("id");
        grid.removeColumn("zipCode");
        grid.setColumnOrder("firstName", "lastName", "birthDate");

        grid.getColumn("firstName").setWidth(130.0);
        grid.getColumn("lastName").setWidth(150.0);
        
        
        grid.addSelectionListener(new SelectionEvent.SelectionListener()
		{
			@Override
			public void select(SelectionEvent event)
			{
				Object selected = ((SingleSelectionModel)
		                grid.getSelectionModel()).getSelectedRow();
		        selectCustomer((Customer)selected);
				
			}
		});
        
        return grid;
    }
    
    private HorizontalLayout setupCommands()
    {
    	HorizontalLayout layout = new HorizontalLayout();
    	
    	
    	Button btnChart = new Button("Age Distribution");
		btnChart.addClickListener(new Button.ClickListener() 
		{	
			@Override
			public void buttonClick(ClickEvent event)
			{
				mainUI.getNavigator().navigateTo("AGE");	
			}
		});
		
		Button btnSex = new Button("Sex Distribution");
		btnSex.addClickListener(new Button.ClickListener() 
		{	
			@Override
			public void buttonClick(ClickEvent event)
			{
				mainUI.getNavigator().navigateTo("SEX");	
			}
		});
		
		Button btnIncome = new Button("Top Annual Income");
		btnIncome.addClickListener(new Button.ClickListener() 
		{	
			@Override
			public void buttonClick(ClickEvent event)
			{
				mainUI.getNavigator().navigateTo("INCOME");	
			}
		});
		
		layout.addComponent(btnChart);
		layout.addComponent(btnSex);
		layout.addComponent(btnIncome);
		
		return layout;
    }
	
	private void setupPage()
    {
		this.mnuDetails.removeStyleName("selected");
		this.mnuCustomers.addStyleName("selected");
		
        final Grid grid = this.setupGrid();
        grid.setWidth("70%");
                
		VerticalLayout content = new VerticalLayout();

		content.addComponent(new Label(" "));
		content.addComponent(grid);
		content.addComponent(new Label(" "));
				
		content.addComponent(this.setupCommands());
		content.setMargin(true);
		
        this.scroll_panel.setContent(content);
		
    	
    }


}
