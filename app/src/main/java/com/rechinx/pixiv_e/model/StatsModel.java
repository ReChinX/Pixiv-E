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

    public FavoritedCountModel favorited_count;

    public int commented_count;

    public StatsModel() {
    }

    public int getScored_count() {
        return scored_count;
    }

    public void setScored_count(int scored_count) {
        this.scored_count = scored_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getCommented_count() {
        return commented_count;
    }

    public void setCommented_count(int commented_count) {
        this.commented_count = commented_count;
    }

    public FavoritedCountModel getFavorited_count() {
        return favorited_count;
    }

    public void setFavorited_count(FavoritedCountModel favorited_count) {
        this.favorited_count = favorited_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.scored_count);
        dest.writeInt(this.score);
        dest.writeInt(this.views_count);
        dest.writeParcelable(this.favorited_count, flags);
        dest.writeInt(this.commented_count);
    }

    protected StatsModel(Parcel in) {
        this.scored_count = in.readInt();
        this.score = in.readInt();
        this.views_count = in.readInt();
        this.favorited_count = in.readParcelable(FavoritedCountModel.class.getClassLoader());
        this.commented_count = in.readInt();
    }

    public static final Creator<StatsModel> CREATOR = new Creator<StatsModel>() {
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
