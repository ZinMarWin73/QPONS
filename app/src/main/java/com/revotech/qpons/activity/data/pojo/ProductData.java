package com.revotech.qpons.activity.data.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Created by zinmarwin on 2/10/16.
 */
public class ProductData implements Parcelable{

    int mProduct_Image ;
    String mProd_Name;
    String mSold_Out;
    String mOrigin_Price;
    String mDiscount_Price;
    int mHightlight_id;
    String expired ;

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public ProductData() {
    }

    public int getmHightlight_id() {
        return mHightlight_id;
    }

    public void setmHightlight_id(int mHightlight_id) {
        this.mHightlight_id = mHightlight_id;
    }

    public int getmProduct_Image() {
        return mProduct_Image;
    }

    public void setmProduct_Image(int mProduct_Image) {
        this.mProduct_Image = mProduct_Image;
    }

    public String getmProd_Name() {
        return mProd_Name;
    }

    public void setmProd_Name(String mProd_Name) {
        this.mProd_Name = mProd_Name;
    }

    public String getmSold_Out() {
        return mSold_Out;
    }

    public void setmSold_Out(String mSold_Out) {
        this.mSold_Out = mSold_Out;
    }

    public String getmOrigin_Price() {
        return mOrigin_Price;
    }

    public void setmOrigin_Price(String mOrigin_Price) {
        this.mOrigin_Price = mOrigin_Price;
    }

    public String getmDiscount_Price() {
        return mDiscount_Price;
    }

    public void setmDiscount_Price(String mDiscount_Price) {
        this.mDiscount_Price = mDiscount_Price;
    }



    public ProductData(Parcel in) {
       readFromParcelable(in);
    }


    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
         dest.writeInt(mProduct_Image);
         dest.writeString(mProd_Name);
         dest.writeString(mSold_Out);
         dest.writeString(mOrigin_Price);
         dest.writeString(mDiscount_Price);
         dest.writeInt(mHightlight_id);
         dest.writeString(expired);
    }

    private void readFromParcelable(Parcel in){
        mProduct_Image = in.readInt();
        mProd_Name = in.readString();
        mSold_Out = in.readString();
        mOrigin_Price = in.readString();
        mDiscount_Price = in.readString();
        //obj_hightlight = in.readParcelable(ProductHightLight.class.getClassLoader());
        mHightlight_id = in.readInt();
        expired = in.readString();

    }

}
