package com.example.stair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.stair.Platform.ETPlatform;
import com.example.stair.Platform.NPUPlatform;
import com.example.stair.model.PlatformModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Text angleStair;

    @FXML
    private Text heightWay;

    @FXML
    private Text lengthWayET;

    @FXML
    private Text lengthWayNPU;

    @FXML
    private Text lengthWaySteps;

    @FXML
    private Text liftingAngle;

    @FXML
    private Text liftingAngleNPU;

    @FXML
    private Text supports3SidesET;

    @FXML
    private Text supportsPassingET;

    @FXML
    private Text supportsPassingLowerBoxNPU;

    @FXML
    private Text supportsPassingUpBoxNPU;

    @FXML
    private Text supportsSideET;

    @FXML
    private Text supportsSideLowerBoxNPU;

    @FXML
    private Text supportsSideUpBoxNPU;

    @FXML
    private Text wall3SidesET;

    @FXML
    private Text wallPassingET;

    @FXML
    private Text wallPassingLowerBoxNPU;

    @FXML
    private Text wallPassingUpBoxNPU;

    @FXML
    private Text wallSideET;

    @FXML
    private Text wallSideLowerBoxNPU;

    @FXML
    private Text wallSideUpBoxNPU;

    @FXML
    private Text lengthLowerPlatformMinET;

    @FXML
    private Text lengthLowerPlatformMinNPU;

    @FXML
    private Text lengthLowerPlatformSideMinET;

    @FXML
    private Text lengthLowerPlatformSideMinNPU;

    @FXML
    void initialize() {
        PlatformModel model = new PlatformModel();
        model.startModel();
        angleStair.setText(String.valueOf(model.angle).substring(0, 4));
        heightWay.setText(String.valueOf(model.lengthStepsY.get(model.lengthStepsY.size())));
        lengthWaySteps.setText(String.valueOf(model.lengthStair()));
        liftingAngle.setText(String.valueOf(model.angleET));
        if(model.angleET > 30) {
            liftingAngle.setFill(Color.RED);
        }
        if (model.angleNPU > 0) {
            liftingAngleNPU.setText(String.format("%.1f", model.angleNPU));
            if(model.angleNPU > 30) {
                liftingAngleNPU.setFill(Color.RED);
            }
        } else {
            liftingAngleNPU.setText("0");
        }
        lengthWayET.setText(String.valueOf(model.lengthWayET));
        lengthWayNPU.setText(String.valueOf(model.lengthWayNPU));
        if(model.lengthWayET > 10000) {
            lengthWayET.setFill(Color.RED);
        }
        if (model.lengthWayNPU > 6500) {
            lengthWayNPU.setFill(Color.RED);
        }

        if(model.angleNPU > 0){
            lengthLowerPlatformMinNPU.setText(String.valueOf(model.lengthWayOnLowerPlatsNPU + NPUPlatform.lengthPassing + NPUPlatform.clearanceOnWall));
            lengthLowerPlatformSideMinNPU.setText(String.valueOf(model.lengthWayOnLowerPlatsNPU + NPUPlatform.lengthSide + NPUPlatform.clearanceOnWall));
        }
        else {
            lengthLowerPlatformMinNPU.setText("0");
            lengthLowerPlatformSideMinNPU.setText("0");
        }
        if(model.angleET > 0) {
            lengthLowerPlatformMinET.setText(String.valueOf(model.lengthWayOnLowerPlatsET + ETPlatform.lengthPassing + ETPlatform.clearanceOnWall));
            lengthLowerPlatformSideMinET.setText(String.valueOf(model.lengthWayOnLowerPlatsET + ETPlatform.lengthSide + ETPlatform.clearanceOnWall));
        } else {
            lengthLowerPlatformMinET.setText("0");
            lengthLowerPlatformSideMinET.setText("0");
        }

        //ET
        if(model.angleET <= 30) {
            if (model.lengthWayOnLowerPlatsET + ETPlatform.lengthPassing + ETPlatform.clearanceOnWall <= StartController.lengthLowerPlatformInt &&
                    ETPlatform.widthOnWallPassing + ETPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    ETPlatform.lengthRamp - model.lengthClearanceRampET <= StartController.lengthUpperPlatformInt && model.angleET > 0) {
                wallPassingET.setText("ДА");
                wallPassingET.setFill(Color.GREEN);
            }
            if (model.lengthWayOnLowerPlatsET + ETPlatform.lengthSide + ETPlatform.clearanceOnWall <= StartController.lengthLowerPlatformInt &&
                    ETPlatform.widthOnWallSide + ETPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    ETPlatform.lengthRamp - model.lengthClearanceRampET <= StartController.lengthUpperPlatformInt && model.angleET > 0) {
                wallSideET.setText("ДА");
                wallSideET.setFill(Color.GREEN);;
            }
            if (model.lengthWayOnLowerPlatsET + ETPlatform.lengthPassing + ETPlatform.clearanceOnWall <= StartController.lengthLowerPlatformInt &&
                    ETPlatform.widthOnWallSide + ETPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    ETPlatform.lengthRamp - model.lengthClearanceRampET <= StartController.lengthUpperPlatformInt && model.angleET > 0) {
                wall3SidesET.setText("ДА");
                wall3SidesET.setFill(Color.GREEN);
            }
            if (model.lengthWayOnLowerPlatsET + ETPlatform.lengthPassing + ETPlatform.clearanceOnWall <= StartController.lengthLowerPlatformInt &&
                    ETPlatform.widthOnSupportsPassing + ETPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    ETPlatform.lengthRamp - model.lengthClearanceRampET <= StartController.lengthUpperPlatformInt && model.angleET > 0) {
                supportsPassingET.setText("ДА");
                supportsPassingET.setFill(Color.GREEN);
            }
            if (model.lengthWayOnLowerPlatsET + ETPlatform.lengthSide + ETPlatform.clearanceOnWall <= StartController.lengthLowerPlatformInt &&
                    ETPlatform.widthOnSupportsSide + ETPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    ETPlatform.lengthRamp - model.lengthClearanceRampET <= StartController.lengthUpperPlatformInt && model.angleET > 0) {
                supportsSideET.setText("ДА");
                supportsSideET.setFill(Color.GREEN);
            }
            if (model.lengthWayOnLowerPlatsET + ETPlatform.lengthPassing + ETPlatform.clearanceOnWall <= StartController.lengthLowerPlatformInt &&
                    ETPlatform.widthOnSupportsSide + ETPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    ETPlatform.lengthRamp - model.lengthClearanceRampET <= StartController.lengthUpperPlatformInt && model.angleET > 0) {
                supports3SidesET.setText("ДА");
                supports3SidesET.setFill(Color.GREEN);
            }
        }

        //NPU
        if(model.angleNPU <= 30) {
            int lengthPassingNPU = model.lengthWayOnLowerPlatsNPU + NPUPlatform.lengthPassing + NPUPlatform.clearanceOnWall;
            int lengthSideNPU = model.lengthWayOnLowerPlatsNPU + NPUPlatform.lengthSide + NPUPlatform.clearanceOnWall;
            //Passing
            if (lengthPassingNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnWallPassingUpper + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformUpper <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                wallPassingUpBoxNPU.setText("ДА");
                wallPassingUpBoxNPU.setFill(Color.GREEN);
            }
            if (lengthPassingNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnWallPassingLower + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformLower <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                wallPassingLowerBoxNPU.setText("ДА");
                wallPassingLowerBoxNPU.setFill(Color.GREEN);
            }
            if (lengthPassingNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnSupportsPassingUpper + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformUpper <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                supportsPassingUpBoxNPU.setText("ДА");
                supportsPassingUpBoxNPU.setFill(Color.GREEN);
            }
            if (lengthPassingNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnSupportsPassingLower + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformLower <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                supportsPassingLowerBoxNPU.setText("ДА");
                supportsPassingLowerBoxNPU.setFill(Color.GREEN);
            }
            //Side
            if (lengthSideNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnWallSideUpper + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformUpper <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                wallSideUpBoxNPU.setText("ДА");
                wallSideUpBoxNPU.setFill(Color.GREEN);
            }
            if (lengthSideNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnWallSideLower + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformLower <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                wallSideLowerBoxNPU.setText("ДА");
                wallSideLowerBoxNPU.setFill(Color.GREEN);
            }
            if (lengthSideNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnSupportsSideUpper + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformUpper <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                supportsSideUpBoxNPU.setText("ДА");
                supportsSideUpBoxNPU.setFill(Color.GREEN);
            }
            if (lengthSideNPU <= StartController.lengthLowerPlatformInt &&
                    NPUPlatform.widthOnSupportsSideLower + NPUPlatform.clearanceOnWall <= StartController.widthStairInt &&
                    NPUPlatform.minUpperPlatformLower <= StartController.lengthUpperPlatformInt && model.angleNPU > 0) {
                supportsSideLowerBoxNPU.setText("ДА");
                supportsSideLowerBoxNPU.setFill(Color.GREEN);
            }
        }


        backButton.setOnAction(actionEvent -> {
            System.out.println("Back!");
            try {
                openReturnScene("start-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void openReturnScene(String win) throws IOException {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(win));
        Stage stage = new Stage();
        Image icon = new Image("stair_l.png");
        stage.getIcons().add(icon);
        stage.setTitle("ЛЕСЕНКА");
        stage.setScene(new Scene((Parent) loader.load()));
        StartController controller = loader.getController();
        controller.initialize();
        stage.show();
    }

}
