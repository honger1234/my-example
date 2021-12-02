package com.honger1234.goflearning.prototype;

import java.util.Date;

public class Bilibili {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date();
        Video video = new Video("张三", date);
        Video video1 = (Video) video.clone();
        System.out.println(video);
        System.out.println(video1);
        System.out.println(video == video1);

        date.setDate(111213);

        System.out.println(video);
        System.out.println(video1);
    }
}
