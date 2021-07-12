package com.mohit.quake;

public class Earthquake {
    private String mPlace,mLink;
    private double mMagnitude;
    private Long mtimeInMillis;
    Earthquake(double mMagnitude,String mPlace,Long mtimeInMillis,String mLink){
        this.mMagnitude=mMagnitude;
        this.mtimeInMillis=mtimeInMillis;
        this.mPlace=mPlace;
        this.mLink=mLink;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public Long getmtimeInMillis() {
        return mtimeInMillis;
    }

    public String getmPlace() {
        return mPlace;
    }
    public String getmLink(){return mLink;}
}
