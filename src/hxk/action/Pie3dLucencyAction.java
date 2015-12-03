package hxk.action;

import java.awt.Font;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author hxk
 * @description 3D饼图
 *2015年12月3日  下午2:15:46
 */
@Controller("pie3dLucencyAction")
@Scope("prototype")
public class Pie3dLucencyAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 设置数据集
    private static DefaultPieDataset getDataset() {
	DefaultPieDataset dataset = new DefaultPieDataset();  
        dataset.setValue("苹果",100);  
        dataset.setValue("梨子",200);  
        dataset.setValue("葡萄",300);  
        dataset.setValue("香蕉",400);  
        dataset.setValue("荔枝",500);  
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
	JFreeChart chart = ChartFactory.createPieChart3D("水果产量", dataset,
		true,// 是否有标题
		false,// 是否有悬浮提示
		false// 是否有连接
		);
	// 设置字体
	setPieText(chart, "水果产量");

	// 副标题
	chart.addSubtitle(new TextTitle("水果产量"));

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

	PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
	
	//设置开始角度  
	pieplot3d.setStartAngle(120D);  
	//设置方向为”顺时针方向“  
	pieplot3d.setDirection(Rotation.CLOCKWISE);  
	//设置透明度，0.5F为半透明，1为不透明，0为全透明  
	pieplot3d.setForegroundAlpha(0.7F); 
	
	return chart;
    }

}
