import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 This code uses JavaFX to create a population increase simulation program.

 The program displays a line chart of the population over time, and allows the user to adjust the growth rate using a slider.

 The program is defined by a `PopulationSimulation` class that extends the `Application` class. The `start()` method is the entry point of the program, and is called when the program is launched.

 The `start()` method first sets up the population chart by creating a new `NumberAxis` for the x-axis and y-axis, creating a new `LineChart` object, and adding a series for the population data. The population data is initially calculated for 50 time steps using the `initialPopulation` and `growthRate` fields, and added to the series.

 Next, a slider is created for adjusting the growth rate. The slider is added to an `HBox` along with a label, and the `HBox` is added to a `VBox` along with the chart. The `VBox` is then added to the scene.

 When the slider value changes, the `valueProperty().addListener()` method is called, which updates the `growthRate` field and calls the `updatePopulationData()` method to recalculate the population data for each time step.

 The `updatePopulationData()` method clears the existing data points in the series and recalculates the population data for each time step using the `initialPopulation` and `growthRate` fields.

 Finally, the `main()` method launches the program by calling the `launch()` method with the command-line arguments.
**/

 public class PopulationSimulation extends Application {
    private int initialPopulation = 1000;
    private double growthRate = 0.05;

    @Override
    public void start(Stage primaryStage) {
        // Set up the population chart
        NumberAxis xAxis = new NumberAxis();  // Create a new x-axis
        NumberAxis yAxis = new NumberAxis();  // Create a new y-axis
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);  // Create a new line chart
        chart.setTitle("Population Over Time");  // Set the chart title
        chart.setCreateSymbols(false);  // Turn off symbols for data points

        // Create a series for the population data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Population");  // Set the series name
        for (int i = 0; i < 50; i++) {  // Loop over 50 time steps
            double population = initialPopulation * Math.pow(1 + growthRate, i);  // Calculate the population
            series.getData().add(new XYChart.Data<>(i, population));  // Add the population data point to the series
        }
        chart.getData().add(series);  // Add the series to the chart

        // Create a slider for adjusting the growth rate
        Slider growthRateSlider = new Slider(0, 0.1, growthRate);  // Create a new slider with a range of 0-0.1 and a default value of growthRate
        growthRateSlider.setMajorTickUnit(0.01);  // Set the major tick unit to 0.01
        growthRateSlider.setMinorTickCount(0);  // Turn off minor ticks
        growthRateSlider.setSnapToTicks(true);  // Snap to the nearest tick
        growthRateSlider.setShowTickLabels(true);  // Show tick labels
        growthRateSlider.setShowTickMarks(true);  // Show tick marks
        growthRateSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            growthRate = newValue.doubleValue();  // Update the growth rate
            updatePopulationData(series, initialPopulation, growthRate);  // Update the population data
        });

        // Set up the main layout
        Label growthRateLabel = new Label("Growth Rate");  // Create a label for the growth rate slider
        HBox growthRateBox = new HBox(10, growthRateLabel, growthRateSlider);  // Create an HBox to hold the label and slider
        growthRateBox.setAlignment(Pos.CENTER);  // Center the HBox
        VBox root = new VBox(20, chart, growthRateBox);  // Create a VBox to hold the chart and HBox
        root.setPadding(new Insets(20));  // Add padding
        root.setAlignment(Pos.CENTER);  // Center the VBox

        // Set up the scene and show the stage
        Scene scene = new Scene(root, 800, 600);  // Create a new scene with the root node
        primaryStage.setTitle("Population Simulation");  // Set the stage title
        primaryStage.setScene(scene);  // Set the scene
        primaryStage.show();  // Show the stage
    }

    private void updatePopulationData(XYChart.Series<Number, Number> series, int initialPopulation, double growthRate) {
        series.getData().clear(); // Clear the existing data points in the series
        for (int i = 0; i < 50; i++) { // Loop over 50 time steps
            double population = initialPopulation * Math.pow(1 + growthRate, i); // Calculate the population
            series.getData().add(new XYChart.Data<>(i, population)); // Add the population data point to the series
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
