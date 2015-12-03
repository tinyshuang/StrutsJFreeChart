package hxk.action;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 折线图
 * @author HXK 2013-9-7 下午11:20:32
 */
@Controller("curveAction")
@Scope("prototype")
public class CurveAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private static XYSeriesCollection getDataset() {
	XYSeries dataSeries = new XYSeries("增长率");
	dataSeries.add(49, 0.17);
	dataSeries.add(59, 0.15);
	dataSeries.add(69, 0.35);
	dataSeries.add(79, 0.65);
	dataSeries.add(89, 0.85);
	dataSeries.add(100, 1.00);
	XYSeriesCollection xyDataset = new XYSeriesCollection();
	xyDataset.addSeries(dataSeries);
	return xyDataset;
    }

    public JFreeChart getChart() {
	XYDataset dataset = getDataset();
	Font xfont = new Font("宋体", Font.PLAIN, 12);
	JFreeChart chart = ChartFactory.createXYLineChart("分布率", "x轴", "y轴",
		dataset, PlotOrientation.VERTICAL, true, true, false);
	// 设置图标题的字体
	Font font = new Font("黑体", Font.CENTER_BASELINE, 20);
	chart.getTitle().setFont(font);
	// 设置图例字体
	chart.getLegend().setItemFont(xfont);

	XYPlot plot = (XYPlot) chart.getPlot();
	ValueAxis domainAxis = plot.getDomainAxis();
	domainAxis.setLowerMargin(0.1);// 设置距离图片左端距离此时为10%
	domainAxis.setUpperMargin(0.1);// 设置距离图片右端距离此时为百分之10
	domainAxis.setLabelFont(xfont);// X轴标题
	domainAxis.setTickLabelFont(xfont);// X轴数值
	domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色

	NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	rangeAxis.setUpperMargin(0.1);// 设置最高的一个柱与图片顶端的距离(最高柱的10%)
	rangeAxis.setLabelFont(xfont);// Y轴标题
	rangeAxis.setTickLabelFont(xfont);// Y轴数值
	rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色
	rangeAxis.setTickLabelFont(xfont);
	NumberFormat numberformat = new DecimalFormat("00%");
	rangeAxis.setNumberFormatOverride(numberformat);// 设置y轴以百分比方式显示
	
	// 平滑曲线,下面两行是使各个节点加粗加红显示
	XYItemRenderer renderer = new XYSplineRenderer(3);
	plot.setRenderer(renderer);
	
	//设置曲线显示各数据点的值
        XYItemRenderer xyitem = plot.getRenderer();
        xyitem.setBaseItemLabelsVisible(true);
        xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
 
        //下面三句是对设置折线图数据标示的关键代码
        xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 14));
        plot.setRenderer(xyitem);
 
 
	return chart;
    }
}
