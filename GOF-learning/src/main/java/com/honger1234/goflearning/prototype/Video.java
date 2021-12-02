package com.honger1234.goflearning.prototype;

import java.util.Date;

public class Video implements Cloneable{
    private String name;
    private Date date;

    public Video(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Video video = (Video) super.clone();
        //深克隆,克隆属性
        video.date= (Date) this.date.clone();
        return video;

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
