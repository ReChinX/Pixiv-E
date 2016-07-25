package com.rechinx.pixiv_e.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chin on 2016/7/17.
 */
public class ProfileImageUrlsModel implements Parcelable {

    public String px_170x170;

    public String px_50x50;

    public String getPx_170x170() {
        return px_170x170;
    }

    public void setPx_170x170(String px_170x170) {
        this.px_170x170 = px_170x170;
    }

    public String getPx_50x50() {
        return px_50x50;
    }

    public void setPx_50x50(String px_50x50) {
        this.px_50x50 = px_50x50;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.px_170x170);
        dest.writeString(this.px_50x50);
    }

    public ProfileImageUrlsModel() {
    }

    protected ProfileImageUrlsModel(Parcel in) {
        this.px_170x170 = in.readString();
        this.px_50x50 = in.readString();
    }

    public static final Parcelable.Creator<ProfileImageUrlsModel> CREATOR = new Parcelable.Creator<ProfileImageUrlsModel>() {
        @Override
        public ProfileImageUrlsModel createFromParcel(Parcel source) {
            return new ProfileImageUrlsModel(source);
        }

        @Override
        public ProfileImageUrlsModel[] newArray(int size) {
            return new ProfileImageUrlsModel[size];
        }
    };
}
