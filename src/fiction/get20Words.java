package fiction;

import model.word;
import java.util.ArrayList;
import java.util.Random;
import static connectDataBase.dictionarySql.selectAssignWord;
import static control.newQueryControl.getRank;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/4  17:09
 * @package: fiction
 * @project: Dictionary
 */
public class get20Words {

    private ArrayList<word> list=new ArrayList<>();
    private ArrayList<word> testList=new ArrayList<>();
    private static int size;

    /**
     * 根据用户选择的等级，从数据库查询相对应的数据
     */
    public void getWords(){
        String getRank=getRank();
        switch(getRank){
            case "四级" :list=selectAssignWord("四级");break;
            case "六级" :list=selectAssignWord("六级");break;
        }

    }

    public void getTestWords(){
        getWords();
        size=list.size();
        getTestWordsFiction(size);
    }
    /**
     * 从可选择的数据中挑选20个单词
     * @param size
     */

    public void getTestWordsFiction(int size){

        Random random=new Random((int)(Math.random()*100));
        int[] array=new int[20];
        initializeArray(array);
        for (int j=0;j<20;j++) {               //用长度为20的数组储存所要20个单词在list中的下标
            int i=-1;
            boolean judge=true;
            while (judge) {                  //遍历数组，确认存储的下标没有重复的
                int times=0;
                i=random.nextInt(size);          //随机生成不超过list.size()的整数
                for (Integer a:array
                     ) {
                    if (i!=a)
                        ++times;
                }
                if (times==20){
                    judge=false;
                }
            }
             array[j]=i;
        }

        ArrayList<word> temp=new ArrayList<>();
        for (Integer i:array
             ) {
         temp.add(list.get(i));
        }
        testList=temp;
    }

    /**
     * 使数组中储存的下标初始化为-1
     * @param array
     */
    public void initializeArray(int[] array){
        for (int i=0;i<array.length;i++) {
            array[i] = -1;
        }
    }

    public  ArrayList<word> getTestList() {
        getTestWords();
        return testList;
    }

    public static int getSize() {
        return size;
    }
}
