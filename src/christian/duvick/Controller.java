package christian.duvick;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Calendar;

public class Controller {
    Stats stats = Stats.getInstance();
    FoodList foodList = FoodList.getInstance();
    ObservableList<Food> foods;
    @FXML
    private TextField foodNameField;
    @FXML
    private TextField calorieField;
    @FXML
    private ListView<Food> foodListView;
    @FXML
    private Label calorieGoalLabel;
    @FXML
    private Label deficitLabel;
    @FXML
    private TextField targetField;
    @FXML
    private Label errorLabel;
    @FXML

    public void onRemoveButtonAction() {
        if (!foodList.getFoodList().isEmpty()) {
            int index = foodListView.getSelectionModel().getSelectedIndex();
            foodListView.getItems().remove(index);
            foodList.getFoodList().remove(index);
            System.out.println("foodlist size = " + foodList.getFoodList().size());
            System.out.println("foods size = " + foods.size());
            updateDeficit();
        }
    }

    public void onAddButtonAction() {
        if (foodNameField.getText().isBlank() && calorieField.getText().isBlank()) {
            errorLabel.setText("Food cannot be empty and calorie field" +
                    " must not be empty. The calorie field must contain only numbers.");
        } else if (foodNameField.getText().isBlank()) {
            errorLabel.setText("Food cannot be empty");
        } else if (calorieField.getText().isBlank()) {
            errorLabel.setText("Calorie field must not be empty.");
        } else {
            try {
                String name = foodNameField.getText();
                String cals = calorieField.getText();
                Food food = new Food(name, Integer.parseInt(cals));
                foods.add(food);
                foodList.addFood(food);
                foodNameField.clear();
                calorieField.clear();
                System.out.println(food);
                updateDeficit();
                clearError();
                System.out.println(foodList.getFoodList());
            } catch (NumberFormatException nfe) {
                errorLabel.setText("The calorie field must contain only numbers.");
            }
        }
    }


    public void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(foodList);
            objectOutputStream.writeObject(stats);
        } catch (Exception e) {
            System.out.println("Save aborted");
        }
    }

    public File saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        String date = java.time.LocalDate.now().toString();
        fileChooser.setInitialFileName(date + "_Calories");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        return file;
    }

    public void retrieve() {
        try {
            FileInputStream file = new FileInputStream(retrieveFile());
            ObjectInputStream input = new ObjectInputStream(file);
            FoodList fl = (FoodList) input.readObject();
            foodList.retrieve(fl);
            stats.retrieve((Stats) input.readObject());
            initialize();
            System.out.println(foodList.getFoodList().toString());
        } catch (Exception e) {
            System.out.println("No file selected.");
        }
    }

    //https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
    public File retrieveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }

    public void clearError() {
        errorLabel.setText(" ");
    }

    public void onSetButtonAction() {
        try {
            String goal = targetField.getText();
            stats.setTarget(Integer.valueOf(goal));
            calorieGoalLabel.setText(String.valueOf(stats.getTarget()));
            updateDeficit();
            targetField.clear();
            clearError();
        } catch (Exception e) {
            errorLabel.setText("Target field cannot be left empty\nand must contain only numbers.");
        }

    }

    public void updateDeficit() {
        int deficit = stats.calculateDeficit();
        deficitLabel.setText(String.valueOf(deficit));
        if (deficit < 0) {
            deficitLabel.setStyle("-fx-text-fill: #800000;");
        } else {
            deficitLabel.setStyle("-fx-text-fill: white;");
        }
    }


    public void initialize() throws IOException, ClassNotFoundException {
        foods = FXCollections.observableArrayList(foodList.getFoodList());
        calorieGoalLabel.setText(String.valueOf(stats.getTarget()));
        deficitLabel.setText(String.valueOf(stats.calculateDeficit()));
        foodListView.setItems(foods);
    }
}
