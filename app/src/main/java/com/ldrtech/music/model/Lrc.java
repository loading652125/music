package com.ldrtech.music.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xiaoh on 2018/3/25.
 */

public class Lrc {
    @SerializedName("lrcContent")
    private String lrcContent;

    public String getLrcContent() {
        return lrcContent;
    }

    public void setLrcContent(String lrcContent) {
        this.lrcContent = lrcContent;
    }
}
