package connectDataBase;

import model.essay;
import model.word;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/8/30  17:48
 * @package: connectDataBase
 * @project: Dictionary
 */
public class dictionarySql {


    private static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/newdictionary?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
        String username = "root";
        String password = "1605103328";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            //  System.out.println("Connecting to database.....v");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static word selectWord(String word) {

        word word1 = null;
        Connection conn = getConn();
        ResultSet set = null;

        String sql= "select  words.word,meaning,ranking,sentence,sentence_meaning " +
                "from  words left outer join sentence on (words.word = sentence.word)" +
                " where words.word=?;";

        try {

            try (PreparedStatement stmt=conn.prepareStatement(sql)){
                stmt.setString(1, word);
                set = stmt.executeQuery();
                if (set.next()) {
                    String sWord = set.getString(1);
                    String sMeaning = set.getString(2);
                    String sRanking=set.getString(3);
                    String sentence=set.getString(4);
                    String sentence_meaning=set.getString(5);
                    word1 = new word(sWord, sMeaning, sentence, sentence_meaning,sRanking);
                    System.out.println(word1);
                }
            } finally {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return word1;
    }

    public static ArrayList selectAssignWord(String ranking) {

        ArrayList<word> list = new ArrayList<>();
        Connection conn = getConn();
        ResultSet set = null;
        String sql = "select * from words where ranking='"+ranking+"';";

        Statement stmt = null;

        try {

            try {
                stmt = conn.createStatement();
                set = stmt.executeQuery(sql);
                while (set.next()) {
                    String sWord = set.getString(1);
                    String sMeaning = set.getString(2);
                    String sRanking=set.getString(3);
                    list.add(new word(sWord, sMeaning, "","", ranking));
                }
            } finally {
                stmt.close();
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("查询完毕！");

        return list;
    }

    public static word selectSentence(String word) {

        word word1 = null;
        Connection conn = getConn();
        ResultSet set = null;
        String sql = "select  words.word,meaning,ranking,sentence,sentence_meaning " +
                "from  words right outer join sentence on (words.word = sentence.word)" +
                " where sentence.word=?;";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                preparedStatement.setString(1, word);
                set = preparedStatement.executeQuery();
                if (set.next()) {
                    String sWord = set.getString(1);
                    String sMeaning = set.getString(2);
                    String sRanking=set.getString(3);
                    String sentence=set.getString(4);
                    String sentence_meaning=set.getString(5);
                    word1 = new word(sWord, sMeaning, sentence,sentence_meaning, sRanking);
                }

                conn.close();
        }catch (SQLException e) {
                e.printStackTrace();
                System.out.println(word);
        }


        System.out.println("查询完毕！");
        return word1;
    }

    public static essay  selectEssay(String co,String c){

        essay essay=null;
        Connection conn=getConn();
        ResultSet set=null;
        String sql="select * from essay where "+co+"=?;";
        try(PreparedStatement pStat=conn.prepareStatement(sql)) {
                pStat.setString(1,c);
            set=pStat.executeQuery();
            if (set.next()){
                int id=set.getInt(1);
                String title=set.getString(2);
                String sEssay=set.getString(3);
                String sEssay_chinese=set.getString(4);
                essay=new essay(id,title,sEssay,sEssay_chinese);
                System.out.println(essay);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return essay;
    }




    public static ArrayList  selectAllEssay(){

        ArrayList<essay> list=new ArrayList<>();
        Connection conn=getConn();
        ResultSet set=null;
        String sql="select * from essay;";
        try(PreparedStatement pStat=conn.prepareStatement(sql)) {
            set=pStat.executeQuery();
            while (set.next()){
                int id=set.getInt(1);
                String title=set.getString(2);
                String sEssay=set.getString(3);
                String sEssay_chinese=set.getString(4);
                list.add(new essay(id,title,sEssay,sEssay_chinese));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static TreeSet selectAllWord() {

        TreeSet<word> wordTreeSet = new TreeSet<>();
        Connection conn = getConn();
        ResultSet set = null;
        String sql = "select  words.word,meaning,ranking,sentence,sentence_meaning " +
                "from  words left outer join sentence on (words.word = sentence.word);";
        Statement stmt;

        try {
            stmt = conn.createStatement();
            set = stmt.executeQuery(sql);

            while (set.next()) {

                String sWord = set.getString(1);
                String sMeaning = set.getString(2);
                String sRanking=set.getString(3);
                String sentence=set.getString(4);
                String sentence_meaning=set.getString(5);
                wordTreeSet.add(new word(sWord, sMeaning, sentence,sentence_meaning,sRanking));
            }
            System.out.println("查询完毕！");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wordTreeSet;
    }


    public static int insertWord(word aWord) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into words (word,meaning,ranking) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aWord.getWord());
            pstmt.setString(2, aWord.getMeaning());
            pstmt.setString(3, aWord.getRanking());
            i = pstmt.executeUpdate();
            System.out.println("插入完成！！！");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return i;
    }

    public static int insertSentence(word aWord) {

        Connection conn = getConn();
        int i = 0;
        String sql = "insert into sentence (word,sentence,sentence_meaning) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aWord.getWord());
            pstmt.setString(2, aWord.getSentence());
            pstmt.setString(3,aWord.getSentence_meaning());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return i;
    }

    public static int insertEssay(essay essay){

        Connection conn = getConn();
        int i = 0;
        String sql = "insert into essay (title,essay,essay_chinese) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,essay.getTitle());
            pstmt.setString(2,essay.getEssay());
            pstmt.setString(3,essay.getEssay_chinese());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return i;


    }


    public static int update(String type, word aWord) {

        Connection conn = getConn();
        String sql = null;
        if (type.equalsIgnoreCase("sentence"))
            sql = "update sentence set sentence='" + aWord.getSentence() + "',sentence_meaning='"+aWord.getSentence_meaning()+"'  where word='" + aWord.getWord() + "'";
        else
            sql = "update words set meaning='" + aWord.getMeaning() + "' where word='" + aWord.getWord() + "'";
        PreparedStatement pstmt = null;
        System.out.println("update  "+sql);
        int i = getJDBC(pstmt, sql, conn);
        return i;
    }

    public static int updateEssay(int id,String title,String essay,String essay_chinese){
        int i=0;
        Connection conn=getConn();
        String sql="update essay set title=?,essay=?,essay_chinese=? where id=?;";
        try(PreparedStatement pStat=conn.prepareStatement(sql)){
            pStat.setString(1,title);
            pStat.setString(2,essay);
            pStat.setString(3,essay_chinese);
            pStat.setInt(4,id);
            i=pStat.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    public static int[] updateIdOrder(){

        Connection conn=getConn();
        String[] sql={"ALTER  TABLE  essay DROP id ;","ALTER TABLE essay ADD (id int NOT NULL PRIMARY KEY AUTO_INCREMENT) ;" ,
                "CREATE UNIQUE INDEX essay_id_uindex ON essay (id);" ,
                "ALTER TABLE essay MODIFY COLUMN id int NOT NULL auto_increment FIRST;"};

        try(Statement stat=conn.createStatement()) {

            for(int i=0;i<sql.length;i++)
                stat.addBatch(sql[i]);
            return stat.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static int delete(String type, String word) {
        Connection conn = getConn();
        String sql = "delete from " + type + " where word='" + word + "'";
        PreparedStatement pstmt = null;

        int i = getJDBC(pstmt, sql, conn);
        return i;
    }

    public static int deleteEssay(int id){

        int i=0;
        Connection conn=getConn();
        String sql="delete from essay where id=?";

        try(PreparedStatement pStat=conn.prepareStatement(sql)) {
            pStat.setInt(1,id);
            i=pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public static int getJDBC(PreparedStatement pstmt, String sql, Connection conn) {
        int i = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("result: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return i;
    }


}
