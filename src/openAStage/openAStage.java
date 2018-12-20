package openAStage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/1  19:35
 * @package: PACKAGE_NAME
 * @project: Dictionary
 */
public class openAStage {

    private Stage Stage=new Stage();
    private AnchorPane pane;

    public void openTheStage(String urlFxml,String urlCss,String Title,boolean judge,boolean judgeShow) throws IOException {


        FXMLLoader loader=new FXMLLoader(getClass().getResource(urlFxml));
        if (judge)
            Stage.initModality(Modality.APPLICATION_MODAL);
        pane=(AnchorPane) loader.load();
        Scene scene=new Scene(pane);

        if (urlCss!=null)
            scene.getStylesheets().add(
                getClass().getResource(urlCss)
                        .toExternalForm());

        Stage.setTitle(Title);
        Stage.setScene(scene);
        if (judgeShow)
            Stage.show();

    }

    public javafx.stage.Stage getStage() {
        return Stage;
    }

    public AnchorPane getPane() {
        return pane;
    }
}
