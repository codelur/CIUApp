package codelur.ciuapp;

/**
 * Created by codelur on 2/23/16.
 */

public class Item {
    
    private String title;
    private String description;
    private int grade;

    public Item(String title, String description, int grade) {
        super();
        this.title = title;
        this.description = description;
        this.grade = grade;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
