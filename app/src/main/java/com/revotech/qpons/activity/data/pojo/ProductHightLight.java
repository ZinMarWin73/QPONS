package com.revotech.qpons.activity.data.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zinmarwin on 2/15/16.
 */
public class ProductHightLight implements Parcelable{

    int mHight_id;
    String mDesription;

    public  ProductHightLight(){

    }
    protected ProductHightLight(Parcel in) {
       readFromParcelable(in);
    }

    public int getmHight_id() {
        return mHight_id;
    }

    public void setmHight_id(int mHight_id) {
        this.mHight_id = mHight_id;
    }

    public String getmDesription() {
        return mDesription;
    }

    public void setmDesription(String mDesription) {
        this.mDesription = mDesription;
    }


    public static final Creator<ProductHightLight> CREATOR = new Creator<ProductHightLight>() {
        @Override
        public ProductHightLight createFromParcel(Parcel in) {
            return new ProductHightLight(in);
        }

        @Override
        public ProductHightLight[] newArray(int size) {
            return new ProductHightLight[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       readFromParcelable(dest);
    }

    private void readFromParcelable(Parcel in){

        in.writeInt(mHight_id);
        in.writeString(mDesription);
    }
}
