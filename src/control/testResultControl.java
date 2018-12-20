package control;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import model.word;
import java.util.ArrayList;

import static control.newQueryControl.getList;
import static control.newQueryControl.getRank;


/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/5  19:28
 * @package: control
 * @project: Dictionary
 */
public class testResultControl {

    public TableView tableView;
    public Label result;

    private javafx.collections.ObservableList<word> ObservableList;

    private ArrayList<word> list;

    public void initialize(){

        tableView.setOpacity(0.7);
        list=getList();
        ObservableList=FXCollections.observableArrayList(list);
        tableView.setItems(ObservableList);

        getResult();

    }



    public void getResult(){

        int sum=0;
        for (word w:list
             ) {
            if (w.isRightOrWrong())
                sum++;

        }

        result.setText("试卷等级 :"+getRank()+"  你的成绩是 ："+5*sum);
    }


}
