package control;

import fiction.get20Words;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.essay;
import model.word;
import openAStage.openAStage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

import static connectDataBase.dictionarySql.*;
import static control.queryResultControl.setX;
import static control.queryResultControl.setY;
import static dataBaseFiction.dataBaseFiction.*;
import static openAStage.openNewWarningStage.openNewWarningStage;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/5  21:38
 * @package: control
 * @project: Dictionary
 */
public class newQueryControl {
    public Text sentence;
    public TableView tableView;
    public Text word;
    public Text meaning;
    public TextField getWord;
    public TabPane tabPane;
    public Text rankL;
    public TabPane essayPane;
    public TableView readTableVIewEssay;
    public TextField readQueryF;
    public TextArea addEnglishA;
    public TextArea addChineseA;
    public TableView alterEssay;
    public TextField alterF;
    public TextArea alterEnglishA;
    public TextArea alterChineseA;
    public TableView deleteTableView;
    public TextField deleteQueryF;
    public TextArea readEnglishA;
    public TextArea readChineseA;
    public TextArea deleteEnglishA;
    public TextArea deleteChineseA;
    private static TreeSet<word> wordTreeSet=new TreeSet<>();
    public TextField alterTitleF;
    public TextField addTitleF;
    public Text sentence_meaning;
    private javafx.collections.ObservableList<word> ObservableList;
    private javafx.collections.ObservableList<essay> ObservableListEssay;
    private ArrayList<essay> essayArrayList=new ArrayList<>();
    private static String rank;
    private static Stage testStage;
    private static ArrayList<word> list;

    public void initialize() throws SQLException {
        sentence.setWrappingWidth(600);
        sentence_meaning.setWrappingWidth(580);
        sentence.setText("");
        word.setText("");
        meaning.setText("");
        rankL.setText("");
        sentence_meaning.setText("");
        updateIdOrder();
        getWordDataFromBase();
        getEssayDataFromBase();
        deleteEnglishA.setEditable(false);
        deleteChineseA.setEditable(false);
        readChineseA.setEditable(false);
        readEnglishA.setEditable(false);
        readEnglishA.setWrapText(true);
        readChineseA.setWrapText(true);
        addEnglishA.setWrapText(true);
        addChineseA.setWrapText(true);
        alterEnglishA.setWrapText(true);
        alterChineseA.setWrapText(true);
        deleteChineseA.setWrapText(true);
        deleteEnglishA.setWrapText(true);
    }
    /**
     * 添加单词
     * @param actionEvent
     * @throws IOException
     */
    public void addWord(ActionEvent actionEvent) throws IOException {
        openAStage openAStage=new openAStage();
        openAStage.openTheStage("../gui/queryResultGui.fxml","../css/queryResultCss.css","Result",false,true);
        setX(openAStage.getStage().getX());
        setY(openAStage.getStage().getY());
    }
    /**
     * 查询单词
     * @param actionEvent
     */
    public void queryTheWord(ActionEvent actionEvent) {
        query();
    }
    /**
     * 查询单词
     * @param actionEvent
     */
    public void enter(ActionEvent actionEvent) {
        query();
    }
    /**
     * 实现查询单词功能
     */
    public void query(){

        word needWord=selectWord(getWord.getText().trim());
        if (needWord==null){
            word.setText("不存在此单词");
            meaning.setText("");
            sentence.setText("");
            rankL.setText("");
            sentence_meaning.setText("");
        }
        else {
            word.setText(needWord.getWord());
            meaning.setText(needWord.getMeaning());
            sentence.setText(needWord.getSentence());
            rankL.setText(needWord.getRanking());
            sentence_meaning.setText(needWord.getSentence_meaning());
        }
    }
    /**
     * 四级测试
     * @param actionEvent
     * @throws IOException
     */
    public void primary_ranking(ActionEvent actionEvent) throws IOException {
        rank="四级";
        list=new get20Words().getTestList();
        openATestStage();
    }
    /**
     * 六级测试
     * @param actionEvent
     * @throws IOException
     */
    public void middle_ranking(ActionEvent actionEvent) throws IOException {
        rank="六级";
        list=new get20Words().getTestList();
        openATestStage();
    }

    /**
     *打开测试界面
     * @throws IOException
     */
    private void openATestStage() throws IOException {

        openAStage openAStage= new openAStage();
        openAStage.openTheStage("../gui/testGUi.fxml","../css/testCss.css","Test",false,true);
        testStage=openAStage.getStage();

    }
    /**
     * 查询数据库单词并展示
     */

    public void getEssayDataFromBase(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                essayArrayList=selectAllEssay();
                ObservableListEssay=FXCollections.observableArrayList(essayArrayList);
                readTableVIewEssay.setItems(ObservableListEssay);
                deleteTableView.setItems(ObservableListEssay);
                alterEssay.setItems(ObservableListEssay);
                System.out.println("设置成功！！！");
            }
        }).start();
    }
    /**
     * 查询数据库单词并展示
     * @throws SQLException
     */
    public void getWordDataFromBase() throws SQLException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                wordTreeSet=selectAllWord();
                ObservableList=FXCollections.observableArrayList(wordTreeSet);
                tableView.setItems(ObservableList);
                System.out.println("设置成功！！！");
            }
        }).start();
    }

    /**
     * 从本地文件恢复数据到数据库
     * @param actionEvent
     * @throws IOException
     */
    public void recoverButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        recover();
    }

    /**
     * 把本地数据库数据备份到本地文件
     * @param actionEvent
     * @throws IOException
     */
    public void backupButton(ActionEvent actionEvent) throws IOException, InterruptedException {
        backup();
    }

    @FXML
    public void readQueryF(ActionEvent actionEvent) throws InterruptedException {
        essay essay=selectEssay("id",readQueryF.getText().trim());
        if (essay==null)
            openNewWarningStage("此段短文不存在，请先添加此文章！",400);
        else {
            readChineseA.setText(essay.getEssay_chinese());
            readEnglishA.setText(essay.getEssay());
        }
    }

    public void addButton(ActionEvent actionEvent) throws InterruptedException {

        essay theEssayConfirm;
        essay theEssayEnglish;
        if ((!addEnglishA.getText().trim().equalsIgnoreCase(""))&&(!addChineseA.getText().trim().equalsIgnoreCase(""))&&(!addTitleF.getText().trim().equalsIgnoreCase(""))){
            theEssayEnglish = selectEssay("essay", addEnglishA.getText().trim());
            if (theEssayEnglish!= null)
                openNewWarningStage("此短文已存在，请修改此文！",400);
            else {
                theEssayConfirm = new essay(addTitleF.getText().trim(),addEnglishA.getText(),addChineseA.getText().trim());
                insertEssay(theEssayConfirm);
                getEssayDataFromBase();
                openNewWarningStage("  添加成功！",400);
            }
        }
        else
            openNewWarningStage("请至少输入中文和英文！",300);
    }

    public void alterF(ActionEvent actionEvent) throws InterruptedException {
        essay essay=selectEssay("id",alterF.getText().trim());
        if (essay==null)
            openNewWarningStage("此段短文不存在，请先添加此文！",400);
        else {
            alterTitleF.setText(essay.getTitle());
            alterEnglishA.setText(essay.getEssay_chinese());
            alterChineseA.setText(essay.getEssay());
        }
    }

    public void alterButton(ActionEvent actionEvent) throws InterruptedException {

        if ((!alterEnglishA.getText().trim().equalsIgnoreCase(""))&&(!alterChineseA.getText().trim().equalsIgnoreCase(""))){
            int result= updateEssay(Integer.parseInt(alterF.getText().trim()),alterTitleF.getText().trim(),alterEnglishA.getText().trim(),alterChineseA.getText().trim());
            if (result==0)
                openNewWarningStage("此短文已存在！",400);
            else {
                getEssayDataFromBase();
                openNewWarningStage("  修改成功！",400);
            }
        }
        else
            openNewWarningStage("请至少输入中文和英文！",300);
    }

    public void deleteQueryF(ActionEvent actionEvent) throws InterruptedException {
        essay essay=selectEssay("id",deleteQueryF.getText().trim());
        if (essay==null)
            openNewWarningStage("此段短文不存在，请先添加此文！",400);
        else {
            deleteEnglishA.setText(essay.getEssay());
            deleteChineseA.setText(essay.getEssay_chinese());
        }
    }

    public void deleteButton(ActionEvent actionEvent) throws InterruptedException {

        if ((!deleteEnglishA.getText().trim().equalsIgnoreCase(""))&&(!deleteChineseA.getText().trim().equalsIgnoreCase(""))){
                deleteEssay(Integer.parseInt(deleteQueryF.getText().trim()));
                updateIdOrder();
                getEssayDataFromBase();
                openNewWarningStage("  删除成功！",400);
        }
        else
            openNewWarningStage("请至少输入此单词,释义和等级！",300);
    }

    public static String getRank() {
        return rank;
    }

    public static Stage getTestStage() {
        return testStage;
    }

    public static ArrayList<word> getList() {
        return list;
    }

}
