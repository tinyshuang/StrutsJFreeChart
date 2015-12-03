package hxk.action;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author HXK 2013-9-7 下午7:35:24
 * 自定义条形图
 */
@Controller("customBarAction")
@Scope("prototype")
public class CustomBarAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private static CategoryDataset getDataset() {
	double[][] data = new double[][] { { 1320, 1110, 1123, 321 },
		{ 720, 210, 1423, 1321 }, { 830, 1310, 123, 521 },
		{ 400, 1110, 623, 321 } };	//二维的数据
	String[] rowKeys = { "苹果", "香蕉", "橘子", "梨子" };//二维条状图的各个条状图所代表的东东
	String[] columnKeys = { "深圳", "北京", "上海", "南京" };//总条状图的各个数据的下标名
	/* @param rowKeys  the row keys (<code>null</code> not permitted).
	 * @param columnKeys  the column keys (<code>null</code> not permitted).
	 * @param data  the data.*/
	CategoryDataset dataSet = DatasetUtilities.createCategoryDataset(
		rowKeys, columnKeys, data);
	return dataSet;
    }

    public JFreeChart getChart() {
	final CategoryDataset dataset = getDataset();
	/*
	 *  * @param title the chart title (<code>null</code> permitted).
	 * 
	 * @param categoryAxisLabel the label for the category axis
	 * (<code>null</code> permitted).
	 * 
	 * @param valueAxisLabel the label for the value axis (<code>null</code>
	 * permitted).
	 * 
	 * @param dataset the dataset for the chart (<code>null</code>
	 * permitted).
	 * 
	 * @param orientation the plot orientation (horizontal or vertical)
	 * (<code>null</code> not permitted).
	 * 
	 * @param legend a flag specifying whether or not a legend is required.
	 * 
	 * @param tooltips configure chart to generate tool tips?
	 * 
	 * @param urls configure chart to generate URLs?
	 */
	JFreeChart chart = ChartFactory.createBarChart3D("水果销售统计图", "水果", "销售",
		dataset, PlotOrientation.VERTICAL, true, true, false);
	
	setBarText(chart, "水果销售统计图");
	
	CategoryPlot plot=chart.getCategoryPlot();
	// 设置网格背景颜色
	plot.setBackgroundPaint(Color.white);
	// 设置网格竖线颜色
	plot.setDomainGridlinePaint(Color.pink);
	// 设置网格横线颜色
	plot.setRangeGridlinePaint(Color.pink);
	
	// 显示每个柱的数值，并修改该数值的字体属性
	BarRenderer3D renderer=new BarRenderer3D();
	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	renderer.setBaseItemLabelsVisible(true);
	
	renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
	renderer.setItemLabelAnchorOffset(10D);  
	
	// 设置平行柱的之间距离
	renderer.setItemMargin(0.4);
	
	plot.setRenderer(renderer);
	return chart;
    }
}
