/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.graphs;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author 2246745
 */
public class Graph1 {
    private AtomicInteger time = new AtomicInteger(0);
    public Graph1 (){
     Stage graphStage = new Stage();
        graphStage.setTitle("Line Chart Example");

        /*final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X Axis");
        yAxis.setLabel("Y Axis");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Line Chart Example");

        XYChart.Series series = new XYChart.Series();
        series.setName("Example Data");

        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);

        Scene scene = new Scene(lineChart, 800, 600);
        graphStage.setScene(scene);
        graphStage.show();}*/
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    xAxis.setAnimated(false);
    xAxis.setLabel("Time (s)");

    yAxis.setAnimated(false);
    yAxis.setLabel("Position");

    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    series.setName("Values");

    LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
    chart.setAnimated(false);
    chart.getData().add(series);

    Scene scene = new Scene(chart, 620, 350);
    graphStage.setScene(scene);
    graphStage.show();
    UpdatewithTime(series);
    
    }
    
    
    public void UpdatewithTime(XYChart.Series<Number, Number> series){
        Thread updateThreadwithTime = new Thread(() -> {
      
    while (true) {
        
        try {
          Thread.sleep(1000);
          Platform.runLater(() -> series.getData().add(new XYChart.Data<>(time.incrementAndGet()- 1, (int) (Math.random() * 100 ))));
        }
        
        catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });   
    updateThreadwithTime.start();
  } 

}
