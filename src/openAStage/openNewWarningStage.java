package openAStage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/8  15:01
 * @package: openAStage
 * @project: Dictionary
 */
public class openNewWarningStage {

    public static void openNewWarningStage(String warn,double width) throws InterruptedException {

        Stage warningStage=new Stage();
        warningStage.initModality(Modality.APPLICATION_MODAL);
        StackPane stackPane = new StackPane();
        Label label = new Label(warn);
        stackPane.getChildren().add(label);
        Scene scene = new Scene(stackPane,width,100);
        warningStage.setTitle("Warning");
        warningStage.setScene(scene);
//        warningStage.show();
        warningStage.showAndWait();
    }
}
