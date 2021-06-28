package com.tuneit.example.managed;

import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class Area implements Serializable {
	private LineChartModel drawArea;
	@PostConstruct
	public void init() {
		createDrawArea();
	}
	public LineChartModel getDrawArea() {
		return drawArea;
	}
	private void createDrawArea() {
		drawArea = new LineChartModel();
		LineChartSeries devices = new LineChartSeries();
		devices.setFill(true);
		devices.setLabel("Run devices");
		devices.set("1995", 3000000);
		devices.set("2000", 3000000);
		devices.set("2005", 3000000);
		devices.set("2010", 3000000);
		devices.set("2015", 3000000);
		devices.set("2020", 3000000);

		drawArea.addSeries(devices);
		drawArea.setLegendPosition("ne");
		drawArea.setStacked(true);
		drawArea.setShowPointLabels(true);
		Axis xAxis = new CategoryAxis("Years");
		drawArea.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = drawArea.getAxis(AxisType.Y);
		yAxis.setLabel("Devices");
		yAxis.setMin(0);
		yAxis.setMax(3000000);
	}
}
