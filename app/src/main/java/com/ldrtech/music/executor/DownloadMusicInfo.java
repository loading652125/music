package com.ldrtech.music.executor;

/**
 * Created by zjs on 2018/3/25.
 */

public class DownloadMusicInfo {
    private String title; //标题
    private String musicPath; //
    private String coverPath; //专辑封面路径

    public DownloadMusicInfo(String title, String musicPath, String coverPath) {
        this.title = title;
        this.musicPath = musicPath;
        this.coverPath = coverPath;
    }

    public String getTitle() {
        return title;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public String getCoverPath() {
        return coverPath;
    }
}
