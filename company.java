package com.example.place;

public class company {
String title;
String shortdesc;
String image;

    public company(String title, String shortdesc, String image) {
        this.title = title;
        this.shortdesc = shortdesc;
        this.image=image;

    }

    public company() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
