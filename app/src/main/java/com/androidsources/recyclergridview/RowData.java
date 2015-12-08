package com.androidsources.recyclergridview;


public class RowData {

    private String wonderImage;
    private String wonderName;


    public RowData(String wonderName, String wonderImageURL) {
        this.wonderName = wonderName;
        this.wonderImage = wonderImageURL;
    }

    public String getWonderImageURL() {
        return wonderImage;
    }

    public void setWonderImageURL(String wonderImageURL) {
        this.wonderImage = wonderImage;
    }

    public String getWonderName() {
        return wonderName;
    }

    public void setWonderName(String wonderName) {
        this.wonderName = wonderName;
    }


}

