package inputLocalData;

import model.word;
import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

import static connectDataBase.dictionarySql.insertWord;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/8/30  12:42
 * @package: inputLocalData
 * @project: Diction
 */
public class inputLocalTxtToBase
{


    TreeSet<word> wordTreeSet=new TreeSet<>();

    public static void main(String[] a) throws FileNotFoundException {

        inputLocalTxtToBase toBase=new inputLocalTxtToBase();

        String path="C:\\Users\\16051\\Diction\\source\\word.txt";
        toBase.getLocalTxt(path);
        toBase.outputToDataBase();

        System.out.println("输入成功");
    }


    public Boolean getLocalTxt(String path) throws FileNotFoundException {
        int LineNumber=0;

        Scanner in=new Scanner(new File(path));

        while (in.hasNext()) {

            String word=in.next();
            String meaning=in.nextLine();
            if (LineNumber%2==0)
                wordTreeSet.add(new word(word,meaning.trim(), "",""));
            else
                wordTreeSet.add(new word(word,meaning.trim(), "","","六级"));

            LineNumber++;
            System.out.println("NO "+LineNumber+"    word  :"+word+"  meaning  :"+meaning);

        }

        in.close();
        System.out.println("word number "+LineNumber);

        return true;
    }


    public Boolean outputToDataBase(){

        for (word w:
             wordTreeSet) {

            insertWord(w);
        }
        return true;
    }



}
