package hxk.action;

import java.awt.Font;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("pieExplodeAction")
@Scope("prototype")
public class PieExplodeAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 设置数据集
    private static DefaultPieDataset getDataset() {
	DefaultPieDataset dataset = new DefaultPieDataset();
	dataset.setValue("本科生", 50);
	dataset.setValue("研究生", 30);
	dataset.setValue("博士", 20);
	return dataset;
    }

    // 返回chart
    public JFreeChart getChart() {
	final DefaultPieDataset dataset = getDataset();
	/*
	 * @param title the chart title (<code>null</code> permitted).
	 * 
	 * @param dataset the dataset for the chart (<code>null</code>
	 * permitted).
	 * 
	 * @param legend a flag specifying whether or not a legend is required.
	 * 
	 * @param tooltips configure chart to generate tool tips?
	 * 
	 * @param urls configure chart to generate URLs?
	 */
	JFreeChart chart = ChartFactory.createPieChart("XX公司员工学历比例图", dataset,
		true,// 是否有标题
		false,// 是否有悬浮提示
		false// 是否有连接
		);
	// 设置字体
	setPieText(chart, "XX公司员工学历比例图");

	// 副标题
	chart.addSubtitle(new TextTitle("2013年度"));

	PiePlot pieplot = (PiePlot) chart.getPlot();
	pieplot.setLabelFont(new Font("宋体", 0, 11));
	// 设置饼图是圆的（true），还是椭圆的（false）；默认为true
	pieplot.setCircular(true);
	// 没有数据的时候显示的内容
	pieplot.setNoDataMessage("无数据显示");
	StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator(
		"{0}:({1}.{2})", NumberFormat.getNumberInstance(),
		NumberFormat.getPercentInstance());
	pieplot.setLabelGenerator(standarPieIG);
	
	//强调突出的部分
	pieplot.setExplodePercent("研究生", 0.3);

	return chart;
    }

}
