package com.androidsources.recyclergridview;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class RowData implements Parcelable{

    private String wonderImageURL;
    private Bitmap downloadedImage;
    private int likes;
    private int dislikes;
    private String category;
    static String[] definedCategory = {"Bussiness","Gym","Do it","Startup","Love","God"};


    public RowData( String wonderImageURL) {
        this(wonderImageURL,20,20,"Bussiness");
    }
    public RowData(String wonderImageURL,int likes,int dislikes,String category){
        this.wonderImageURL = wonderImageURL;
        this.likes = likes;
        this.dislikes = dislikes;
        this.category = category;
    }

    protected RowData(Parcel in) {
        wonderImageURL = in.readString();
        downloadedImage = in.readParcelable(Bitmap.class.getClassLoader());
        likes = in.readInt();
        dislikes = in.readInt();
        category = in.readString();
    }

    public static final Creator<RowData> CREATOR = new Creator<RowData>() {
        @Override
        public RowData createFromParcel(Parcel in) {
            return new RowData(in);
        }

        @Override
        public RowData[] newArray(int size) {
            return new RowData[size];
        }
    };

    public int getLikes() {
        return likes;
    }
    public String getLikesAsAString(){
        return ""+getLikes();
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public String getDisikesAsAString(){
        return ""+getDisikesAsAString();
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

     public Bitmap getDownloadedImage() {
        return downloadedImage;
    }

    public void setDownloadedImage(Bitmap downloadedImage) {
        this.downloadedImage = downloadedImage;
    }



    public String getWonderImageURL() {
        return wonderImageURL;
    }

    public void setWonderImageURL(String wonderImageURL) {
        this.wonderImageURL = wonderImageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RowData rowData = (RowData) o;

        return !(wonderImageURL != null ? !wonderImageURL.equals(rowData.wonderImageURL) : rowData.wonderImageURL != null);

    }

    @Override
    public int hashCode() {
        return wonderImageURL != null ? wonderImageURL.hashCode() : 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wonderImageURL);
        downloadedImage.writeToParcel(dest,flags);
        dest.writeInt(likes);
        dest.writeInt(dislikes);
        dest.writeString(category);
    }
}

