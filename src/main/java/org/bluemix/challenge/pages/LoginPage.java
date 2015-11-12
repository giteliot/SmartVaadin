package org.bluemix.challenge.pages;

import org.bluemix.challenge.AppUI;
import org.bluemix.challenge.BusinessLogic;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class LoginPage extends Login implements View
{
	private AppUI mainUI;
	
	public LoginPage(AppUI ui)
	{
		this.mainUI = ui;
	}
	
	private void initUI()
	{
		this.btnSignIn.addClickListener(new Button.ClickListener() 
		{	
			@Override
			public void buttonClick(ClickEvent event)
			{
				String username = (String) txtUsername.getValue();
				String password = txtPassword.getValue();
				
				if (BusinessLogic.checkUsername(username, password))
				{
					mainUI.getNavigator().navigateTo("CUSTOMERS");
				}
				else
				{
					Notification err = new Notification("Error", "<p>Wrong username or password</p>", 
							Notification.Type.ERROR_MESSAGE);
					err.setHtmlContentAllowed(true);
					err.show(mainUI.getPage());
				}
				
			}
		});
		
		this.setMargin(true);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		this.initUI();
	}

}
