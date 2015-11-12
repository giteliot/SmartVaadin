package org.bluemix.challenge.pages;

import java.util.ArrayList;
import java.util.List;

import org.bluemix.challenge.AppUI;
import org.bluemix.challenge.BusinessLogic;
import org.bluemix.challenge.backend.Customer;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.SubTitle;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ChartsPage extends TemplatePage implements View
{
	private String chartType;
	
	public ChartsPage(AppUI ui, String chartType)
	{
		super(ui);
		this.chartType = chartType;
	}
	
	@Override
	public void enter(ViewChangeEvent event)
	{
		this.setupPage();
	}
	
	private void setupIncomeChart(VerticalLayout layout)
	{
		Chart chart = new Chart(ChartType.COLUMN);
        chart.setWidth("70%");
		chart.setHeight("600px");

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Customers Top Annual Income");
        conf.setSubTitle(new SubTitle("Source: DummyDataService"));
        
        List<Customer> topCustomers = BusinessLogic.topAnnualIncome();
        ArrayList<String> categories = new ArrayList<>();
        for (Customer c : topCustomers)
        {
        	categories.add(c.toSmallName());
        }
        
        XAxis xAxis = new XAxis();
        String cats[] = new String[topCustomers.size()];
        xAxis.setCategories(categories.toArray(cats));
        Labels labels = new Labels();
        labels.setRotation(-45);
        labels.setAlign(HorizontalAlign.RIGHT);
        xAxis.setLabels(labels);
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new Title("Top Ten Annual Income (US dollars)"));
        conf.addyAxis(yAxis);
        Legend legend = new Legend();
        legend.setEnabled(false);
        conf.setLegend(legend);
        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("''+ this.x +''+' income in 2015: '"
                + "+ this.y +'USD'");
        conf.setTooltip(tooltip);

        ListSeries serie = new ListSeries();
        serie.setName("Income");
        for (Customer cust : topCustomers)
        {
        	serie.addData(cust.getAnnualIncome());
        }
        
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setRotation(-90);
        dataLabels.setColor(new SolidColor(250, 250, 210));
        dataLabels.setAlign(HorizontalAlign.RIGHT);
        dataLabels.setX(4);
        dataLabels.setY(10);
        dataLabels.setFormatter("this.y");
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setDataLabels(dataLabels);
        serie.setPlotOptions(plotOptionsColumn);
        conf.addSeries(serie);

        chart.drawChart(conf);
        
        layout.addComponent(chart);
		
	}
	
	private void setupSexChart(VerticalLayout layout)
	{
        Chart chart = new Chart(ChartType.PIE);
        chart.setWidth("70%");
		chart.setHeight("600px");

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Customers Sex Distribution");
        conf.setSubTitle(new SubTitle("Source: DummyDataService"));


        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        conf.setPlotOptions(plotOptions);
        
        List<Number> sexData = BusinessLogic.customersBySex();
        
        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Males", sexData.get(0)));
        series.add(new DataSeriesItem("Females", sexData.get(1)));
        series.add(new DataSeriesItem("Other", sexData.get(2)));
        conf.setSeries(series);

        chart.drawChart(conf);

        layout.addComponent(chart);
		
	}
	
	private void setupAgeChart(VerticalLayout layout)
	{
		Chart chart = new Chart(ChartType.BAR);
		chart.setWidth("70%");
		chart.setHeight("600px");
		        
		// Modify the default configuration a bit
		Configuration conf = chart.getConfiguration();
		conf.setTitle("Customers Age Distribution");
		conf.setSubTitle("based on our dummy data service...");
		conf.getLegend().setEnabled(false); // Disable legend

		// The data
		ListSeries series = new ListSeries("Nr. of customers in age range");
		List<Number> ages = BusinessLogic.customersByAge();
		
		System.out.println(ages.get(0));
		System.out.println(ages.get(1));
		System.out.println(ages.get(2));
		System.out.println(ages.get(3));
		
		series.setData(ages);
		conf.addSeries(series);

		// Set the category labels on the axis correspondingly
		XAxis xaxis = new XAxis();
		xaxis.setCategories("0-12", "13-18",   "19-26",
		                    "27-34", "35-42", "43-50",
		                    "51-58",  "59-66", "67-");
		xaxis.setTitle("Age");
		conf.addxAxis(xaxis);

		// Set the Y axis title
		YAxis yaxis = new YAxis();
		yaxis.setTitle("Number");
		yaxis.getLabels().setStep(2);
		conf.addyAxis(yaxis);
		        
		layout.addComponent(chart);
	}
	
	private void setupPage()
    {
		this.mnuCustomers.removeStyleName("selected");
        
		VerticalLayout content = new VerticalLayout();
		if (this.chartType.equals("Age"))
		{
			this.mnuDetails.addStyleName("selected");
			this.setupAgeChart(content);
		}
		else if (this.chartType.equals("Sex"))
		{
			this.mnuSex.addStyleName("selected");
			this.setupSexChart(content);
		}
		else
		{
			this.mnuOther.addStyleName("selected");
			this.setupIncomeChart(content);
		}
		
		content.setMargin(true);
        this.scroll_panel.setContent(content);
    	
    }


}
