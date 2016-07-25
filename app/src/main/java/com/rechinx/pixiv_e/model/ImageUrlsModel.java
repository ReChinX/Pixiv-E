package com.rechinx.pixiv_e.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chin on 2016/7/17.
 */
public class ImageUrlsModel implements Parcelable {

    public String px_128x128;

    public String px_480mw;

    public String large;

    public String small;

    public String medium;

    public String getPx_128x128() {
        return px_128x128;
    }

    public void setPx_128x128(String px_128x128) {
        this.px_128x128 = px_128x128;
    }

    public String getPx_480mw() {
        return px_480mw;
    }

    public void setPx_480mw(String px_480mw) {
        this.px_480mw = px_480mw;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.px_128x128);
        dest.writeString(this.px_480mw);
        dest.writeString(this.large);
        dest.writeString(this.small);
        dest.writeString(this.medium);
    }

    public ImageUrlsModel() {
    }

    protected ImageUrlsModel(Parcel in) {
        this.px_128x128 = in.readString();
        this.px_480mw = in.readString();
        this.large = in.readString();
        this.small = in.readString();
        this.medium = in.readString();
    }

    public static final Parcelable.Creator<ImageUrlsModel> CREATOR = new Parcelable.Creator<ImageUrlsModel>() {
        @Override
        public ImageUrlsModel createFromParcel(Parcel source) {
            return new ImageUrlsModel(source);
        }

        @Override
        public ImageUrlsModel[] newArray(int size) {
            return new ImageUrlsModel[size];
        }
    };
}
