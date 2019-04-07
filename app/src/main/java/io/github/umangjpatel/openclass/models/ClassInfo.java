package io.github.umangjpatel.openclass.models;

public class ClassInfo {

    private String mTitle, mFaculty, mKey;

    public ClassInfo() {
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getFaculty() {
        return mFaculty;
    }

    public void setFaculty(String faculty) {
        mFaculty = faculty;
    }
}
