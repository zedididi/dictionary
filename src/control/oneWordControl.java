package control;

import fiction.get20Words;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.word;
import java.util.ArrayList;
import static control.newQueryControl.getList;
import static control.newQueryControl.getTestStage;
import static control.testControl.getBooleans;
import static control.testControl.getTimes;
import static control.testControl.setTimes;
import static openAStage.openTestStage.openIt;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/4  10:22
 * @package: control
 * @project: Dictionary
 */
public class oneWordControl {

    @FXML
    public  TextField result;
    @FXML
    public  Text wordL;
    private  String resultS;
    private  Stage testStage;
    private  word aWord;
    private boolean[] booleans;
    private int times;
    private ArrayList<word> arrayList;

    /**
     * 初始化界面
     * 从挑选的单词中一次读取，展示
     */
    public void initialize(){

        wordL.setWrappingWidth(400);
        testStage=getTestStage();
        times=getTimes();
        setTimes(getTimes()+1);
        System.out.println("times   "+times);
        arrayList=getList();
        if (times<arrayList.size()) {
            aWord = arrayList.get(times);
            wordL.setText(aWord.getMeaning());
        }
    }

    /**
     * 从TextField获取结果
     * 判断对错
     * 给舞台更换场景
     * @param actionEvent
     */
    public void getResult(ActionEvent actionEvent) {

        if (times<arrayList.size()-1) {
            resultS = result.getText();
            booleans = getBooleans();
            if (resultS.equalsIgnoreCase(aWord.getWord())) {
                booleans[times] = true;
                arrayList.get(times).setRightOrWrong(true);
            } else
                booleans[times] = false;


            openIt(testStage, "../gui/oneWord.fxml", "../css/testCss.css");
        }
        else{
            openIt(testStage,"../gui/testResultGui.fxml","../css/testCss.css");
        }





    }

}
