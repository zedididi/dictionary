/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/8/31  8:57
 * @package: PACKAGE_NAME
 * @project: Dictionary
 */

import javafx.application.Application;
import javafx.stage.Stage;
import openAStage.*;
import java.io.IOException;


public class app extends Application {

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        new openAStage().openTheStage("../gui/newQueryGui.fxml ","../css/optimize.css","Dictionary",false,true);
    }
}
