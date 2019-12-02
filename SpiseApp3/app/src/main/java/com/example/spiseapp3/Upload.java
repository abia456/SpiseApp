package com.example.spiseapp3;

public class Upload {
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(String mImageurl) {
        this.mImageurl = mImageurl;
    }

    String mName;
    String mImageurl;

    public Upload(String mName, String mImageurl) {
        this.mName = mName;
        this.mImageurl = mImageurl;
    }

    public Upload() {
    }
}
