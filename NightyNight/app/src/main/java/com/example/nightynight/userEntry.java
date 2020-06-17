package com.example.nightynight;

public class userEntry {

    private int mImageResource;
    private String mtxt1;
    private String mtxt2;

    public userEntry(int ImageResource, String text1, String text2)
    {
        this.mImageResource = ImageResource;
        this.mtxt1 = text1;
        this.mtxt2 = text2;
    }

    public int getmImageResource()
    {
        return mImageResource;
    }

    public String getMtxt1()
    {
        return mtxt1;
    }

    public String getMtxt2()
    {
        return  mtxt2;
    }

}
