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
import library.dynamics.Body;
import library.math.Vectors2D;

/**
 *
 * @author 2163918
 */
public class Graph{
    
    private Body targetBody;
    
  
    public Graph (Body targetBody){
        this.targetBody = targetBody;
        
        
        ComboBox SwitchGraphCB = new ComboBox();
        SwitchGraphCB.getItems().addAll("Position Graph", "Velocity Graph", "Acceleration Graph");
        SwitchGraphCB.setValue("Position Graph");

        final NumberAxis xAxis1 = new NumberAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        final NumberAxis xAxis2 = new NumberAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        final NumberAxis xAxis3 = new NumberAxis();
        final NumberAxis yAxis3 = new NumberAxis();

        yAxis1.setAutoRanging(false);
        yAxis1.setLowerBound(0);
        yAxis1.setUpperBound(100);
        yAxis1.setTickUnit(10);

        yAxis2.setAutoRanging(false);
        yAxis2.setLowerBound(0);
        yAxis2.setUpperBound(100);
        yAxis2.setTickUnit(10);

        yAxis3.setAutoRanging(false);
        yAxis3.setLowerBound(0);
        yAxis3.setUpperBound(100);
        yAxis3.setTickUnit(10);

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();

        series1.setName("Position");
        series2.setName("Velocity");
        series3.setName("Acceleration");

        LineChart<Number, Number> chart1 = new LineChart<>(xAxis1, yAxis1);
        LineChart<Number, Number> chart2= new LineChart<>(xAxis2, yAxis2);
        LineChart<Number, Number> chart3 = new LineChart<>(xAxis3, yAxis3);

        chart1.setAnimated(false);
        chart2.setAnimated(false);
        chart3.setAnimated(false);


        
        chart1.getData().add(series1);
        chart2.getData().add(series2);
        chart3.getData().add(series3);

        xAxis1.setLabel("Position X (m)");
        yAxis1.setLabel("Position Y (m)");
        xAxis2.setLabel("Time (s)");
        yAxis2.setLabel("Position (m)");
        xAxis3.setLabel("Time (s)");
        yAxis3.setLabel("Velocity (m/s)");

        VBox vbox = new VBox(SwitchGraphCB,chart1);

        Stage graphStageP = new Stage();
        UpdatePositionGraph(series1, graphStageP);

        SwitchGraphCB.setOnAction(event ->{
            String string = SwitchGraphCB.getValue().toString();

            switch(string){
                case "Position Graph" -> {
                    vbox.getChildren().clear();
                    UpdatePositionGraph(series1, graphStageP);
                    vbox.getChildren().addAll(SwitchGraphCB,chart1);
                }

                case "Velocity Graph" -> {
                    vbox.getChildren().clear();
                    UpdateVelocityGraph(series2, graphStageP);                                      
                    vbox.getChildren().addAll(SwitchGraphCB,chart2);

                }

                case "Acceleration Graph" -> {
                    vbox.getChildren().clear();
                    UpdateAccelerationGraph(series3, graphStageP);
                    vbox.getChildren().addAll(SwitchGraphCB,chart3);

                }
            }});

        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 620, 350);
        graphStageP.setTitle("Line Chart Example");
        graphStageP.setScene(scene);
        graphStageP.show();

    }

    public void UpdatePositionGraph(XYChart.Series<Number, Number> series, Stage graphStage){
        Thread updateThreadwithTime = new Thread();
        AtomicInteger time = new AtomicInteger(0);
        updateThreadwithTime = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(time.incrementAndGet()- 1, (int) targetBody.position.length())));                   
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        updateThreadwithTime.setDaemon(true);
        updateThreadwithTime.start();
    }

    public void UpdateVelocityGraph(XYChart.Series<Number, Number> series, Stage graphStage){
        Thread updateThreadwithTime;
        AtomicInteger time = new AtomicInteger(0);
        updateThreadwithTime = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(time.incrementAndGet()- 1, (int) targetBody.velocity.length())));
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        updateThreadwithTime.setDaemon(true);
        updateThreadwithTime.start();
    }

    public void UpdateAccelerationGraph(XYChart.Series<Number, Number> series, Stage graphStage){
        AtomicInteger time = new AtomicInteger(0);
        Thread updateThreadwithTime;
        updateThreadwithTime = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(time.incrementAndGet()- 1, (int) new Vectors2D(targetBody.force.x,targetBody.force.y).length())));
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        updateThreadwithTime.setDaemon(true);
        updateThreadwithTime.start();
    }
    
     public void stopUpdates() {
        Thread.currentThread().interrupt();
        Platform.exit();
    }
    
    public void setBody(Body targetBody){
        this.targetBody = targetBody;
    }

}
