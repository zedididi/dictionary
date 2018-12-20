package control;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import static control.newQueryControl.getRank;
import static control.newQueryControl.getTestStage;
import static openAStage.openTestStage.openIt;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/1  15:22
 * @package: control
 * @project: Dictionary */
public class testControl {

    private String rank;
    private static Stage testStage;
    private static boolean[] booleans=new boolean[20];


    private static int times;


    public void initialize(){

        times=0;
        rank=getRank();
        System.out.println(rank);

    }


    public void beginTest(ActionEvent actionEvent) {

        testStage=getTestStage();
        openIt(testStage,"../gui/oneWord.fxml","../css/testCss.css");
    }


    public static int getTimes() {
        return times;
    }

    public static void setTimes(int times) {

        testControl.times = times;
    }

    public static boolean[] getBooleans() {
        return booleans;
    }

}
