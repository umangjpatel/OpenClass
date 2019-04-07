package io.github.umangjpatel.openclass.models;

public class ClassFeed {

    private String mKey, mFaculty, mContent;
    private long mDate;

    public ClassFeed() {
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getFaculty() {
        return mFaculty;
    }

    public void setFaculty(String faculty) {
        mFaculty = faculty;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }
}
