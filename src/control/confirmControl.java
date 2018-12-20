package control;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import model.*;

import static connectDataBase.dictionarySql.delete;
import static connectDataBase.dictionarySql.selectSentence;
import static connectDataBase.dictionarySql.update;
import static control.queryResultControl.*;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/7  8:39
 * @package: control
 * @project: Dictionary
 */
public class confirmControl {

    public Text word;
    public Text meaning;
    public Text sentence;
    public Text rank;
    public Text sentence_meaning;
    private word theWord;
    private static boolean judge;

    public void initialize(){
        word.setWrappingWidth(400);
        meaning.setWrappingWidth(400);
        sentence.setWrappingWidth(400);
        theWord=getTheWord();
        word.setText(theWord.getWord());
        meaning.setText(theWord.getMeaning());
        sentence.setText(theWord.getSentence());
        rank.setText(theWord.getRanking());
        sentence_meaning.setText(theWord.getSentence_meaning());
        judge=isJudge();

    }
    public void confirmButton(ActionEvent actionEvent) {

        if (judge) {
            update("words", getWordConfirm());
            update("sentence", getWordConfirm());
        }
        else {
            delete("words",getWordConfirm().getWord());
            if (selectSentence(getWordConfirm().getWord())!=null)
                delete("sentence",getWordConfirm().getWord());
        }
        getStage().close();

    }

    public void backoutButton(ActionEvent actionEvent) {
        getStage().close();
    }

}
