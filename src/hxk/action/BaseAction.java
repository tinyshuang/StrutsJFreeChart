package hxk.action;


import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author HXK
 *2013-9-7  下午7:57:22
 */
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    private static final Font font = new Font("simsun",Font.ITALIC,22);  
    
    /** @description	
     * @param chart
     *2013-9-7  下午7:58:26
     *返回类型:void	
     */
    protected void setPieText(JFreeChart chart,String titleName) {
	chart.setTitle(new TextTitle(titleName,font));  
          
        //重新设置图例的字体，保证汉字的显示  
        LegendTitle legend = chart.getLegend();  
        legend.setItemFont(font);  
          
        //重新设置统计表对象的字体，保证汉字的显示  
        PiePlot plot = (PiePlot) chart.getPlot();  
        plot.setBackgroundAlpha(0.9f);  
        plot.setLabelFont(font);
    }  

    
    /** @description	
     * @param chart
     *2013-9-7  下午8:01:16
     *返回类型:void	
     */
    protected void setBarText(JFreeChart chart,String titleName) {
	chart.setTitle(new TextTitle(titleName,font));  
          
        //重新设置图例的字体，保证汉字的显示  
        LegendTitle legend = chart.getLegend();  
        legend.setItemFont(font);  
          
        //设置X轴坐标上的文字的字体  
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(font);  
        //设置X轴坐标标题的字体  
        chart.getCategoryPlot().getDomainAxis().setLabelFont(font);  
          
        //设置Y轴坐标上的文字的字体  
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(font);  
        //设置Y轴坐标标题的字体  
        chart.getCategoryPlot().getRangeAxis().setLabelFont(font);
    }         
}
