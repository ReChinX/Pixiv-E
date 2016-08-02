package com.rechinx.pixiv_e.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chin on 2016/8/2.
 */
public class FavoritedCountModel implements Parcelable {

    @SerializedName("public")
    public int public_int;

    @SerializedName("private")
    public int private_int;


    @Override
    public int describeContents() {
        return 0;
    }

    public int getPublic_int() {
        return public_int;
    }

    public void setPublic_int(int public_int) {
        this.public_int = public_int;
    }

    public int getPrivate_int() {
        return private_int;
    }

    public void setPrivate_int(int private_int) {
        this.private_int = private_int;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.public_int);
        dest.writeInt(this.private_int);
    }

    public FavoritedCountModel() {
    }

    protected FavoritedCountModel(Parcel in) {
        this.public_int = in.readInt();
        this.private_int = in.readInt();
    }

    public static final Parcelable.Creator<FavoritedCountModel> CREATOR = new Parcelable.Creator<FavoritedCountModel>() {
        @Override
        public FavoritedCountModel createFromParcel(Parcel source) {
            return new FavoritedCountModel(source);
        }

        @Override
        public FavoritedCountModel[] newArray(int size) {
            return new FavoritedCountModel[size];
        }
    };
}

