package hxk.action;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author HXK 2013-9-7 下午10:56:15
 * 点线图
 */
@Controller("pointLineAction")
@Scope("prototype")
public class PointLineAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 设置数据集
    private static CategoryDataset getDataset() {
	/*********** 基础数据 *****************/
	double[][] data = new double[][] { { 100, 99.3, 89.6, 98, 96 },
		{ 87, 98, 89.1, 91, 93 }, { 92, 96, 93, 90.4, 95 } };
	String[] rowKeys = { "指标A", "指标B", "指标C" };
	String[] columnKeys = { "员工A", "员工B", "员工C", "员工D", "员工F" };
	CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
		rowKeys, columnKeys, data);
	return dataset;
    }

    public JFreeChart getChart() {
	final CategoryDataset dataset = getDataset();
	JFreeChart chart = ChartFactory.createLineChart("点线图", // 图表标题
		"人名", // 目录轴的显示标签
		"分数", // 数值轴的显示标签
		dataset, // 数据集
		PlotOrientation.VERTICAL, // 图表方向：水平、垂直
		true, // 是否显示图例(对于简单的柱状图必须是false)
		false, // 是否生成工具
		false // 是否生成URL链接
		);

	chart.setTextAntiAlias(false);

	TextTitle title = new TextTitle("点线图");
	title.setFont(new Font("隶书", Font.BOLD, 25));
	chart.setTitle(title);// 设置图标题的字体重新设置title

	chart.getLegend().setItemFont(new Font("SansSerif", Font.BOLD, 12));// 图例显示格式

	chart.setBackgroundPaint(Color.WHITE);// 设置背景颜色
	chart.setBorderVisible(true);// 设置背景边框

	CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();// 获取图表

	categoryplot.setDomainGridlinesVisible(true);// x轴网格是否可见

	categoryplot.setRangeGridlinesVisible(true); // y轴网格是否可见

	categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩

	categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩

	categoryplot.setBackgroundPaint(Color.lightGray);// 设定图表数据显示部分背景色

	categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D)); // 设置轴和面板之间的距离
									 // 参数1：上距
									 // 参数2：左距
									 // 参数3：下距
									 // 参数4：右距

	CategoryAxis domainAxis = categoryplot.getDomainAxis();// 横轴
	domainAxis.setLabelFont(new Font("SansSerif", Font.TRUETYPE_FONT, 12));// 轴标题
	domainAxis.setTickLabelFont(new Font("SansSerif", Font.TRUETYPE_FONT,
		12));// 轴数值
	// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	// // 横轴上的Lable 45度倾斜
	domainAxis.setLowerMargin(0.0);// 设置距离图片左端距离
	domainAxis.setUpperMargin(0.0);// 设置距离图片右端距离

	ValueAxis rangeAxis = categoryplot.getRangeAxis(); // 纵轴
	rangeAxis.setLabelFont(new Font("SansSerif", Font.TRUETYPE_FONT, 12));// 轴标题
	rangeAxis
		.setTickLabelFont(new Font("SansSerif", Font.TRUETYPE_FONT, 12));// 轴数值
	rangeAxis.setRange(85, 100.5);// 纵轴显示范围
	rangeAxis.setAutoTickUnitSelection(true);

	rangeAxis.setLowerMargin(5);// 设置距离图片下端距离
	rangeAxis.setUpperMargin(0.5);// 设置距离图片上端距离

	NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
	numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	numberaxis.setAutoRangeIncludesZero(true);
	numberaxis.setUpperMargin(1.15); // 设置最高的一个 Item 与图片顶端的距离
	numberaxis.setLowerMargin(0.15);// 设置最低的一个 Item 与图片底端的距离
	DecimalFormat df = new DecimalFormat("#0.00");
	numberaxis.setNumberFormatOverride(df); // 设置纵轴精度

	categoryplot.setRangeAxis(numberaxis);

	LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
		.getRenderer();// 获得renderer

	lineandshaperenderer.setBaseShapesVisible(true); // 点（即数据点）可见

	lineandshaperenderer
		.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	lineandshaperenderer.setBaseItemLabelsVisible(true);// 显示折点数据
	lineandshaperenderer.setBaseItemLabelFont(new Font("SansSerif",
		Font.TRUETYPE_FONT, 12));// 折点数据字体
	lineandshaperenderer
		.setBasePositiveItemLabelPosition(new ItemLabelPosition(
			ItemLabelAnchor.OUTSIDE10, TextAnchor.BASELINE_CENTER));// 这点数据显示位置

	lineandshaperenderer.setBaseLinesVisible(true); // 点（即数据点）间有连线可见
	
	return chart;

    }

}
