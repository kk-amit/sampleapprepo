package assignmentapp.sample.com.mysampleapp.models;

/**
 * Model for category data.
 */
public class CategoryModel {
    private int offlineCategoryID;
    private String name;

    public void setOfflineCategoryID(int offlineCategoryID) {
        this.offlineCategoryID = offlineCategoryID;
    }

    public int getOfflineCategoryID() {
        return offlineCategoryID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
