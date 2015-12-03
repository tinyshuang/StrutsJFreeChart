package hxk.action;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author hxk
 * @description 条形图
 *2015年12月3日  下午2:12:08
 */
@Controller("barAction")
@Scope("prototype")
public class BarAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    
    /**
     * @description 设置数据集	
     * @return
     *2013-9-7  下午8:03:46
     *返回类型:DefaultCategoryDataset
     */
    private static DefaultCategoryDataset getDataset(){  
        DefaultCategoryDataset  dataset = new DefaultCategoryDataset();  
        dataset.setValue(50,"本科生","本科生");  
        dataset.setValue(50,"研究生","研究生");  
        dataset.setValue(20,"博士","博士");  
        return dataset;  
    }  
    
    
    //返回一个chart
    public JFreeChart getChart(){  
        final DefaultCategoryDataset dataset = getDataset();  
      /* * @param title  the chart title (<code>null</code> permitted).
        * @param categoryAxisLabel  the label for the category axis
        *                           (<code>null</code> permitted).
        * @param valueAxisLabel  the label for the value axis (<code>null</code>
        *                        permitted).
        * @param dataset  the dataset for the chart (<code>null</code> permitted).
        * @param orientation  the plot orientation (horizontal or vertical)
        *                     (<code>null</code> not permitted).
        * @param legend  a flag specifying whether or not a legend is required.
        * @param tooltips  configure chart to generate tool tips?
        * @param urls  configure chart to generate URLs?*/
        JFreeChart chart = ChartFactory.createBarChart3D("XX公司员工学历比例图","学历","人数",  
                dataset,  
                PlotOrientation.VERTICAL,  
                true,  
                false,  
                false  
            );  
        //设置字体
        setBarText(chart,"XX公司员工学历比例图");  
          
        return chart;  
    }
    
}  