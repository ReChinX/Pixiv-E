package com.rechinx.pixiv_e.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chin on 2016/7/17.
 */
public class UserModel implements Parcelable {

    public int id;

    public String account;

    public String name;

    public String is_following;

    public String is_follower;

    public String is_friend;

    public String is_premium;

    public ProfileImageUrlsModel profile_image_urls;

    public String stats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_following() {
        return is_following;
    }

    public void setIs_following(String is_following) {
        this.is_following = is_following;
    }

    public String getIs_follower() {
        return is_follower;
    }

    public void setIs_follower(String is_follower) {
        this.is_follower = is_follower;
    }

    public String getIs_friend() {
        return is_friend;
    }

    public void setIs_friend(String is_friend) {
        this.is_friend = is_friend;
    }

    public String getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(String is_premium) {
        this.is_premium = is_premium;
    }

    public ProfileImageUrlsModel getProfile_image_urls() {
        return profile_image_urls;
    }

    public void setProfile_image_urls(ProfileImageUrlsModel profile_image_urls) {
        this.profile_image_urls = profile_image_urls;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.account);
        dest.writeString(this.name);
        dest.writeString(this.is_following);
        dest.writeString(this.is_follower);
        dest.writeString(this.is_friend);
        dest.writeString(this.is_premium);
        dest.writeParcelable(this.profile_image_urls, flags);
        dest.writeString(this.stats);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.id = in.readInt();
        this.account = in.readString();
        this.name = in.readString();
        this.is_following = in.readString();
        this.is_follower = in.readString();
        this.is_friend = in.readString();
        this.is_premium = in.readString();
        this.profile_image_urls = in.readParcelable(ProfileImageUrlsModel.class.getClassLoader());
        this.stats = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
