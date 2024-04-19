/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.graphs;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author 2246745
 */
public class Graph1 {
    private AtomicInteger time = new AtomicInteger(0);
    
    public Graph1 (){
    ComboBox SwitchGraphCB = new ComboBox();    
    SwitchGraphCB.getItems().addAll("Position Graph", "Velocity Graph", "Acceleration Graph");
    SwitchGraphCB.setValue("Position Graph");

        
    SwitchGraphCB.setOnAction(event ->{
    String string = SwitchGraphCB.getValue().toString();
    
    switch(string){
        case "Position Graph":
            System.out.println("P");
            
        case "Velocity Graph":
            System.out.println("v");
            
        case "Acceleration Graph":
            
            System.out.println("a");
    }});
    
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    xAxis.setLabel("Time (s)");
    
    yAxis.setLabel("Velocity (m/s)");
    yAxis.setAutoRanging(false);
    yAxis.setLowerBound(0);
    yAxis.setUpperBound(100);
    yAxis.setTickUnit(10);

    XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    XYChart.Series<Number, Number> series2 = new XYChart.Series<>();

    series1.setName("Position");
    series1.setName("Velocity");

    LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
    chart.setAnimated(false);
    chart.getData().add(series1);
    chart.getData().add(series2);

    VBox vbox = new VBox(SwitchGraphCB, chart);
    vbox.setAlignment(Pos.CENTER);
    Scene scene = new Scene(vbox, 620, 350);
    
    Stage graphStage = new Stage();
    graphStage.setTitle("Line Chart Example");graphStage.setScene(scene);
    graphStage.show();
    
    UpdatePositionwithTime(series1, graphStage);
    UpdateVelocitywithTime(series2, graphStage);
    
    }
    
   /* public String SiwtchGraph(ComboBox SwitchGraphCB ){
        int graph = 0;
        switch(graph){
            case SwitchGraphCB.getValue() == "Position Graph":
                break;
        }
        return null;   
    }*/
    public void UpdatePositionwithTime(XYChart.Series<Number, Number> series, Stage graphStage){
        Thread updateThreadwithTime;
        updateThreadwithTime = new Thread(() -> {
            
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
    updateThreadwithTime.setDaemon(true);
    updateThreadwithTime.start();
    
  } 
    
    public void UpdateVelocitywithTime(XYChart.Series<Number, Number> series, Stage graphStage){
        Thread updateThreadwithTime;
        updateThreadwithTime = new Thread(() -> {
            
            while (true) {
                
                try {
                    Thread.sleep(1000);
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(time.incrementAndGet()- 1, (int) (Math.random() * 100 ))));
                    System.out.println(Math.random());
                }
                
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }   
        });
    updateThreadwithTime.setDaemon(true);
    updateThreadwithTime.start();
    
  }

}
