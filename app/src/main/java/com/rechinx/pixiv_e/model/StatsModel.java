package com.rechinx.pixiv_e.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chin on 2016/7/17.
 */
public class StatsModel implements Parcelable {

    public int scored_count;

    public int score;

    public int views_count;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.scored_count);
        dest.writeInt(this.score);
        dest.writeInt(this.views_count);
    }

    public StatsModel() {
    }

    protected StatsModel(Parcel in) {
        this.scored_count = in.readInt();
        this.score = in.readInt();
        this.views_count = in.readInt();
    }

    public static final Parcelable.Creator<StatsModel> CREATOR = new Parcelable.Creator<StatsModel>() {
        @Override
        public StatsModel createFromParcel(Parcel source) {
            return new StatsModel(source);
        }

        @Override
        public StatsModel[] newArray(int size) {
            return new StatsModel[size];
        }
    };
}
