package com.example.stair;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.example.stair.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button calculateButton;

    @FXML
    private TextField lengthLowerPlatform;

    @FXML
    private TextField lengthUpperPlatform;

    @FXML
    private TextField stepHeight1;

    @FXML
    private TextField stepHeight10;

    @FXML
    private TextField stepHeight11;

    @FXML
    private TextField stepHeight12;

    @FXML
    private TextField stepHeight13;

    @FXML
    private TextField stepHeight14;

    @FXML
    private TextField stepHeight15;

    @FXML
    private TextField stepHeight16;

    @FXML
    private TextField stepHeight17;

    @FXML
    private TextField stepHeight18;

    @FXML
    private TextField stepHeight19;

    @FXML
    private TextField stepHeight2;

    @FXML
    private TextField stepHeight20;

    @FXML
    private TextField stepHeight21;

    @FXML
    private TextField stepHeight22;

    @FXML
    private TextField stepHeight23;

    @FXML
    private TextField stepHeight24;

    @FXML
    private TextField stepHeight25;

    @FXML
    private TextField stepHeight26;

    @FXML
    private TextField stepHeight27;

    @FXML
    private TextField stepHeight28;

    @FXML
    private TextField stepHeight29;

    @FXML
    private TextField stepHeight3;

    @FXML
    private TextField stepHeight30;

    @FXML
    private TextField stepHeight4;

    @FXML
    private TextField stepHeight5;

    @FXML
    private TextField stepHeight6;

    @FXML
    private TextField stepHeight7;

    @FXML
    private TextField stepHeight8;

    @FXML
    private TextField stepHeight9;

    @FXML
    private TextField stepLength1;

    @FXML
    private TextField stepLength10;

    @FXML
    private TextField stepLength11;

    @FXML
    private TextField stepLength12;

    @FXML
    private TextField stepLength13;

    @FXML
    private TextField stepLength14;

    @FXML
    private TextField stepLength15;

    @FXML
    private TextField stepLength16;

    @FXML
    private TextField stepLength17;

    @FXML
    private TextField stepLength18;

    @FXML
    private TextField stepLength19;

    @FXML
    private TextField stepLength2;

    @FXML
    private TextField stepLength20;

    @FXML
    private TextField stepLength21;

    @FXML
    private TextField stepLength22;

    @FXML
    private TextField stepLength23;

    @FXML
    private TextField stepLength24;

    @FXML
    private TextField stepLength25;

    @FXML
    private TextField stepLength26;

    @FXML
    private TextField stepLength27;

    @FXML
    private TextField stepLength28;

    @FXML
    private TextField stepLength29;

    @FXML
    private TextField stepLength3;

    @FXML
    private TextField stepLength30;

    @FXML
    private TextField stepLength4;

    @FXML
    private TextField stepLength5;

    @FXML
    private TextField stepLength6;

    @FXML
    private TextField stepLength7;

    @FXML
    private TextField stepLength8;

    @FXML
    private TextField stepLength9;

    @FXML
    private TextField widthStair;

    public static Map<Integer, Integer> stepHeights = new HashMap<>();
    public static Map<Integer, Integer> stepLengths = new HashMap<>();

    public static int widthStairInt = 0;

    public static int lengthUpperPlatformInt;

    public static int lengthLowerPlatformInt;

    @FXML
    void initialize() {
        recoveryFields();
        recoveryField(widthStair, widthStairInt);
        recoveryField(lengthLowerPlatform, lengthLowerPlatformInt);
        recoveryField(lengthUpperPlatform, lengthUpperPlatformInt);

        calculateButton.setOnAction(event -> {
            System.out.println("CLICK BUTTON");
            addSteps();
            boolean startNewView = true;
            widthStairInt = checkFields(widthStair);
            lengthLowerPlatformInt = checkFields(lengthLowerPlatform);
            lengthUpperPlatformInt = checkFields(lengthUpperPlatform);
            for (int i = 2; i < stepLengths.size(); i++) {
                if (stepHeights.get(1) == 0 ||
                        (stepHeights.get(1) != 0 && stepLengths.get(1) != 0 && stepHeights.get(2) == 0) ||
                        (stepHeights.get(i) != 0 && stepLengths.get(i) != 0 && stepHeights.get(i + 1) == 0) ||
                        (stepHeights.get(i) != 0 && stepHeights.get(i - 1) != 0 && stepLengths.get(i - 1) == 0) ||
                        (stepHeights.get(30) != 0 && stepLengths.get(30) == 0 && stepLengths.get(29) == 0) ||
                        (stepHeights.get(30) != 0 && stepLengths.get(30) != 0)) {
                    Shake stepField = new Shake(calculateButton);
                    stepField.playAnim();
                    System.out.println("ERROR " + i + " line");
                    startNewView = false;
                    break;
                }
            }

            if (startNewView && widthStairInt > 0 && lengthUpperPlatformInt > 0 && lengthLowerPlatformInt > 0) {
                System.out.println("length lower platform: " + widthStairInt);
                System.out.println("Width: " + widthStairInt);
                System.out.println("length upper platform: " + lengthUpperPlatformInt);
                System.out.println(stepHeights.toString());
                System.out.println(stepLengths.toString());
                System.out.println("successful");
                calculateButton.getScene().getWindow().hide();
                openNewScene("result-view.fxml", "РЕЗУЛЬТАТ");
            } else openNewScene("error-view.fxml", "ОШИБКА");
        });
    }

    public void openNewScene(String win, String nameWin) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(win));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        Image icon = new Image("stair_l.png");
        stage.getIcons().add(icon);
        stage.setTitle(nameWin);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    private void addSteps() {
        addField(stepHeights, stepHeight1, 1);
        addField(stepHeights, stepHeight2, 2);
        addField(stepHeights, stepHeight3, 3);
        addField(stepHeights, stepHeight4, 4);
        addField(stepHeights, stepHeight5, 5);
        addField(stepHeights, stepHeight6, 6);
        addField(stepHeights, stepHeight7, 7);
        addField(stepHeights, stepHeight8, 8);
        addField(stepHeights, stepHeight9, 9);
        addField(stepHeights, stepHeight10, 10);
        addField(stepHeights, stepHeight11, 11);
        addField(stepHeights, stepHeight12, 12);
        addField(stepHeights, stepHeight13, 13);
        addField(stepHeights, stepHeight14, 14);
        addField(stepHeights, stepHeight15, 15);
        addField(stepHeights, stepHeight16, 16);
        addField(stepHeights, stepHeight17, 17);
        addField(stepHeights, stepHeight18, 18);
        addField(stepHeights, stepHeight19, 19);
        addField(stepHeights, stepHeight20, 20);
        addField(stepHeights, stepHeight21, 21);
        addField(stepHeights, stepHeight22, 22);
        addField(stepHeights, stepHeight23, 23);
        addField(stepHeights, stepHeight24, 24);
        addField(stepHeights, stepHeight25, 25);
        addField(stepHeights, stepHeight26, 26);
        addField(stepHeights, stepHeight27, 27);
        addField(stepHeights, stepHeight28, 28);
        addField(stepHeights, stepHeight29, 29);
        addField(stepHeights, stepHeight30, 30);

        addField(stepLengths, stepLength1, 1);
        addField(stepLengths, stepLength2, 2);
        addField(stepLengths, stepLength3, 3);
        addField(stepLengths, stepLength4, 4);
        addField(stepLengths, stepLength5, 5);
        addField(stepLengths, stepLength6, 6);
        addField(stepLengths, stepLength7, 7);
        addField(stepLengths, stepLength8, 8);
        addField(stepLengths, stepLength9, 9);
        addField(stepLengths, stepLength10, 10);
        addField(stepLengths, stepLength11, 11);
        addField(stepLengths, stepLength12, 12);
        addField(stepLengths, stepLength13, 13);
        addField(stepLengths, stepLength14, 14);
        addField(stepLengths, stepLength15, 15);
        addField(stepLengths, stepLength16, 16);
        addField(stepLengths, stepLength17, 17);
        addField(stepLengths, stepLength18, 18);
        addField(stepLengths, stepLength19, 19);
        addField(stepLengths, stepLength20, 20);
        addField(stepLengths, stepLength21, 21);
        addField(stepLengths, stepLength22, 22);
        addField(stepLengths, stepLength23, 23);
        addField(stepLengths, stepLength24, 24);
        addField(stepLengths, stepLength25, 25);
        addField(stepLengths, stepLength26, 26);
        addField(stepLengths, stepLength27, 27);
        addField(stepLengths, stepLength28, 28);
        addField(stepLengths, stepLength29, 29);
        addField(stepLengths, stepLength30, 30);
    }

    private void addField(Map<Integer, Integer> mapStep, TextField field, int n) {
        String num = field.getText();
        if (!num.matches("[0-9]*")) {
            Shake stepField = new Shake(field);
            stepField.playAnim();
            System.out.println("Error " + n + " line fields");
            openNewScene("error-view.fxml", "ОШИБКА");
        } else if (num.equals("")) {
            num = "0";

        }
        mapStep.put(n, Integer.parseInt(num));
    }

    private int checkFields(TextField field) {
        String num = field.getText();
        if (!num.matches("[0-9]*") || num.equals("") || num.equals("0")) {
            Shake stepField = new Shake(field);
            stepField.playAnim();
            num = "0";
            System.out.println("Error field .......");
        }
        return Integer.parseInt(num);
    }

    private void recoveryField(TextField textField, Map<Integer, Integer> map, int n) {
        if (map.get(n) != null)
            textField.setText(String.valueOf(map.get(n)));
    }

    private void recoveryField(TextField textField, int field) {
        if (textField != null)
            textField.setText(String.valueOf(field));
    }

    private void recoveryFields() {
        recoveryField(stepHeight1, stepHeights, 1);
        recoveryField(stepHeight2, stepHeights, 2);
        recoveryField(stepHeight3, stepHeights, 3);
        recoveryField(stepHeight4, stepHeights, 4);
        recoveryField(stepHeight5, stepHeights, 5);
        recoveryField(stepHeight6, stepHeights, 6);
        recoveryField(stepHeight7, stepHeights, 7);
        recoveryField(stepHeight8, stepHeights, 8);
        recoveryField(stepHeight9, stepHeights, 9);
        recoveryField(stepHeight10, stepHeights, 10);
        recoveryField(stepHeight11, stepHeights, 11);
        recoveryField(stepHeight12, stepHeights, 12);
        recoveryField(stepHeight13, stepHeights, 13);
        recoveryField(stepHeight14, stepHeights, 14);
        recoveryField(stepHeight15, stepHeights, 15);
        recoveryField(stepHeight16, stepHeights, 16);
        recoveryField(stepHeight17, stepHeights, 17);
        recoveryField(stepHeight18, stepHeights, 18);
        recoveryField(stepHeight19, stepHeights, 19);
        recoveryField(stepHeight20, stepHeights, 20);
        recoveryField(stepHeight21, stepHeights, 21);
        recoveryField(stepHeight22, stepHeights, 22);
        recoveryField(stepHeight23, stepHeights, 23);
        recoveryField(stepHeight24, stepHeights, 24);
        recoveryField(stepHeight25, stepHeights, 25);
        recoveryField(stepHeight26, stepHeights, 26);
        recoveryField(stepHeight27, stepHeights, 27);
        recoveryField(stepHeight28, stepHeights, 28);
        recoveryField(stepHeight29, stepHeights, 29);
        recoveryField(stepHeight30, stepHeights, 30);

        recoveryField(stepLength1, stepLengths, 1);
        recoveryField(stepLength2, stepLengths, 2);
        recoveryField(stepLength3, stepLengths, 3);
        recoveryField(stepLength4, stepLengths, 4);
        recoveryField(stepLength5, stepLengths, 5);
        recoveryField(stepLength6, stepLengths, 6);
        recoveryField(stepLength7, stepLengths, 7);
        recoveryField(stepLength8, stepLengths, 8);
        recoveryField(stepLength9, stepLengths, 9);
        recoveryField(stepLength10, stepLengths, 10);
        recoveryField(stepLength11, stepLengths, 11);
        recoveryField(stepLength12, stepLengths, 12);
        recoveryField(stepLength13, stepLengths, 13);
        recoveryField(stepLength14, stepLengths, 14);
        recoveryField(stepLength15, stepLengths, 15);
        recoveryField(stepLength16, stepLengths, 16);
        recoveryField(stepLength17, stepLengths, 17);
        recoveryField(stepLength18, stepLengths, 18);
        recoveryField(stepLength19, stepLengths, 19);
        recoveryField(stepLength20, stepLengths, 20);
        recoveryField(stepLength21, stepLengths, 21);
        recoveryField(stepLength22, stepLengths, 22);
        recoveryField(stepLength23, stepLengths, 23);
        recoveryField(stepLength24, stepLengths, 24);
        recoveryField(stepLength25, stepLengths, 25);
        recoveryField(stepLength26, stepLengths, 26);
        recoveryField(stepLength27, stepLengths, 27);
        recoveryField(stepLength28, stepLengths, 28);
        recoveryField(stepLength29, stepLengths, 29);
        recoveryField(stepLength30, stepLengths, 30);
    }
}
