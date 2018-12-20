package model;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/8  10:10
 * @package: model
 * @project: Dictionary
 */
public class essay {
    private int id ;
    private String title;
    private String essay;
    private String essay_chinese;

    public essay(int id, String title,String essay, String essay_chinese) {
        this.id = id;
        this.title=title;
        this.essay = essay;
        this.essay_chinese = essay_chinese;
    }

    public essay(String title,String essay, String essay_chinese) {
        this.title=title;
        this.essay = essay;
        this.essay_chinese = essay_chinese;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }

    public void setEssay_chinese(String essay_chinese) {
        this.essay_chinese = essay_chinese;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getEssay() {
        return essay;
    }

    public String getEssay_chinese() {
        return essay_chinese;
    }

    @Override
    public String toString() {
        return "essay{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", essay='" + essay + '\'' +
                ", essay_chinese='" + essay_chinese + '\'' +
                '}';
    }
}
