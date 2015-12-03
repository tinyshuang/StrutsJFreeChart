# StrutsJFreeChart

###这是一个Struts与JFreeChart结合的例子..

####Struts.xml的一个简单实例 

  ` <package name="helloworld" extends="jfreechart-default"> 
  
     <action name="pie" class="pieAction">   
     
         <result type="chart">   
         
             <param name="width">400</param>   
             
            <param name="height">300</param>   
            
         </result> 
         
    </action>  
    
 ` </package>
  
#####提供以下图形 : 
 
饼状图 条状图 二维图 自定义二维图 有突出强调的饼状图 3D饼状图 3D透明效果饼状图 折线图 点线图 曲线图  

#####Action里面主要方法 : 

* private static DefaultPieDataset getDataset() {}   --拿来返回JFreeChart的数据的私有方法 
* public JFreeChart getChart() {}                    --拿来返回JFreeChart实例的公共方法..是调用action的入口..

  
  
