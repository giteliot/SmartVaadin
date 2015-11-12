package org.bluemix.challenge.pages;

import org.bluemix.challenge.AppUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class TemplatePage extends Browser implements View
{
	protected AppUI mainUI;
	
	public TemplatePage(AppUI ui)
	{
		this.mainUI = ui;
		this.setupNavigationButtons();
	}
	
	private final class NavigatorListener implements Button.ClickListener
	{
		
		private String pageTarget;
		
		public NavigatorListener(String pageName)
		{
			this.pageTarget = pageName;
		}
		
		@Override
		public void buttonClick(ClickEvent event)
		{
			mainUI.getNavigator().navigateTo(this.pageTarget);	
		}
	}

	@Override
	public void enter(ViewChangeEvent event)
	{		
	}
	
	private void setupNavigationButtons()
	{
		this.btnHome.addClickListener(new NavigatorListener(""));		
        this.mnuCustomers.addClickListener(new NavigatorListener("CUSTOMERS"));
        this.mnuDetails.addClickListener(new NavigatorListener("AGE"));
        this.mnuSex.addClickListener(new NavigatorListener("SEX"));
        this.mnuOther.addClickListener(new NavigatorListener("INCOME"));
        this.mnuLogout.addClickListener(new NavigatorListener(""));
	}
}
	