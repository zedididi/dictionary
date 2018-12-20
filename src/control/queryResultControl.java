package control;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.word;
import openAStage.openAStage;
import java.io.IOException;
import static connectDataBase.dictionarySql.*;
import static openAStage.openNewWarningStage.openNewWarningStage;


/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/1  15:15
 * @package: control
 * @project: Dictionary
 */
public class queryResultControl {
    public TextArea sentenceL;
    public TextField wordL;
    public TextArea meaningL;
    public Label result;
    public TextField rankL;
    public TextArea sentence_meaningL;
    private static word theWord;           //原始单词信息
    private static word wordConfirm;       //修改后的单词信息
    private static Stage Stage;
    private static double X;               //当前界面的X坐标
    private static double Y;               //当前界面的坐标
    private static boolean judge;



    public void initialize(){
        meaningL.setWrapText(true);
        sentenceL.setWrapText(true);
        sentence_meaningL.setWrapText(true);
    }


    public void updateButton(ActionEvent actionEvent) throws IOException, CloneNotSupportedException, InterruptedException {
        result.setText("");
        if (!wordL.getText().trim().equalsIgnoreCase("")) {
            theWord = selectWord(wordL.getText().trim());
            if (theWord == null)
                openNewWarningStage("此单词不存在，请添加此单词！",400);
            else {
                wordConfirm = (word) theWord.clone();
                wordConfirm.setSentence(sentenceL.getText().trim());
                wordConfirm.setMeaning(meaningL.getText().trim());
                wordConfirm.setWord(wordL.getText().trim());
                wordConfirm.setRanking(rankL.getText().trim());
                wordConfirm.setSentence_meaning(sentence_meaningL.getText().trim());
                System.out.println("wordConfirm   :"+wordConfirm);
                judge=true;
                openAStage openAStage = new openAStage();
                openAStage.openTheStage("../gui/confirmGui.fxml", "../css/optimize.css", "Warning",true,false);
                Stage = openAStage.getStage();
                Stage.setX(X+820);
                Stage.setY(Y);
                Stage.showAndWait();
            }

        }
        else
            openNewWarningStage("请输入此单词！",300);
    }

    public void addButton(ActionEvent actionEvent) throws InterruptedException, IOException {
        result.setText("");

        if ((!wordL.getText().trim().equalsIgnoreCase(""))&&(!meaningL.getText().trim().equalsIgnoreCase(""))&&(!rankL.getText().trim().equalsIgnoreCase(""))){
            theWord = selectWord(wordL.getText().trim());
            if (theWord != null)
                openNewWarningStage("此单词已存在，请修改此单词！",400);
            else {
                wordConfirm = new word(wordL.getText().trim(),meaningL.getText().trim(),sentenceL.getText().trim(),sentence_meaningL.getText().trim(),rankL.getText().trim());
                insertWord(wordConfirm);
                if (!sentenceL.getText().equalsIgnoreCase("".trim()))
                    insertSentence(wordConfirm);
                result.setText("成功");
            }
        }
        else
            openNewWarningStage("请至少输入此单词,释义和等级！",300);

    }

    public void deleteButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        result.setText("");

        if (!wordL.getText().equalsIgnoreCase("".trim())) {
            theWord = selectWord(wordL.getText().trim());
            if (theWord == null)
                openNewWarningStage("此单词不存在，请添加此单词！",400);
            else {
                wordConfirm = theWord;
                judge=false;
                openAStage openAStage = new openAStage();
                openAStage.openTheStage("../gui/confirmGui.fxml", "../css/optimize.css", "Warning",true,true);
                Stage = openAStage.getStage();
                Stage.setX(X+820);
                Stage.setY(Y);
                Stage.showAndWait();
            }
        }
        else
            openNewWarningStage("请输入此单词！",300);

    }

    public void clearButton(ActionEvent actionEvent) {
        result.setText("");
        sentenceL.clear();
        meaningL.clear();
        wordL.clear();
        rankL.clear();
        sentence_meaningL.clear();
    }


    public static word getWordConfirm() {
        return wordConfirm;
    }

    public static javafx.stage.Stage getStage() {
        return Stage;
    }

    public static word getTheWord() {
        return theWord;
    }

    public static void setX(double x) {
        X = x;
    }

    public static void setY(double y) {
        Y = y;
    }

    public static boolean isJudge() {
        return judge;
    }
}
