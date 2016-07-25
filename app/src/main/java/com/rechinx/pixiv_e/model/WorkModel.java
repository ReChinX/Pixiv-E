package com.rechinx.pixiv_e.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chin on 2016/7/17.
 */
public class WorkModel implements Parcelable {

    public int id;

    public String title;

    public String caption;

    public List<String> tags;

    public List<String> tools;

    public ImageUrlsModel image_urls;

    public int width;

    public int height;

    public StatsModel stats;

    public int publicity;

    public String age_limit;

    public String created_time;

    public String reuploaded_time;

    public UserModel user;

    public String is_manga;

    public String is_liked;

    public String favorite_id;

    public int page_count;

    public String book_style;

    public String type;

    public String metadata;

    public String content_type;

    public String sanity_level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ImageUrlsModel getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(ImageUrlsModel image_urls) {
        this.image_urls = image_urls;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public StatsModel getStats() {
        return stats;
    }

    public void setStats(StatsModel stats) {
        this.stats = stats;
    }

    public String getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(String age_limit) {
        this.age_limit = age_limit;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getReuploaded_time() {
        return reuploaded_time;
    }

    public void setReuploaded_time(String reuploaded_time) {
        this.reuploaded_time = reuploaded_time;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getIs_manga() {
        return is_manga;
    }

    public void setIs_manga(String is_manga) {
        this.is_manga = is_manga;
    }

    public String getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(String is_liked) {
        this.is_liked = is_liked;
    }

    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSanity_level() {
        return sanity_level;
    }

    public void setSanity_level(String sanity_level) {
        this.sanity_level = sanity_level;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public int getPublicity() {
        return publicity;
    }

    public void setPublicity(int publicity) {
        this.publicity = publicity;
    }

    public String getBook_style() {
        return book_style;
    }

    public void setBook_style(String book_style) {
        this.book_style = book_style;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.caption);
        dest.writeStringList(this.tags);
        dest.writeStringList(this.tools);
        dest.writeParcelable(this.image_urls, flags);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeParcelable(this.stats, flags);
        dest.writeInt(this.publicity);
        dest.writeString(this.age_limit);
        dest.writeString(this.created_time);
        dest.writeString(this.reuploaded_time);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.is_manga);
        dest.writeString(this.is_liked);
        dest.writeString(this.favorite_id);
        dest.writeInt(this.page_count);
        dest.writeString(this.book_style);
        dest.writeString(this.type);
        dest.writeString(this.metadata);
        dest.writeString(this.content_type);
        dest.writeString(this.sanity_level);
    }

    public WorkModel() {
    }

    protected WorkModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.caption = in.readString();
        this.tags = in.createStringArrayList();
        this.tools = in.createStringArrayList();
        this.image_urls = in.readParcelable(ImageUrlsModel.class.getClassLoader());
        this.width = in.readInt();
        this.height = in.readInt();
        this.stats = in.readParcelable(StatsModel.class.getClassLoader());
        this.publicity = in.readInt();
        this.age_limit = in.readString();
        this.created_time = in.readString();
        this.reuploaded_time = in.readString();
        this.user = in.readParcelable(UserModel.class.getClassLoader());
        this.is_manga = in.readString();
        this.is_liked = in.readString();
        this.favorite_id = in.readString();
        this.page_count = in.readInt();
        this.book_style = in.readString();
        this.type = in.readString();
        this.metadata = in.readString();
        this.content_type = in.readString();
        this.sanity_level = in.readString();
    }

    public static final Parcelable.Creator<WorkModel> CREATOR = new Parcelable.Creator<WorkModel>() {
        @Override
        public WorkModel createFromParcel(Parcel source) {
            return new WorkModel(source);
        }

        @Override
        public WorkModel[] newArray(int size) {
            return new WorkModel[size];
        }
    };
}
