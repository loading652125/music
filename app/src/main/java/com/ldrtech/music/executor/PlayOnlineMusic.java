package com.ldrtech.music.executor;

import android.app.Activity;
import android.text.TextUtils;

import com.ldrtech.music.http.HttpCallback;
import com.ldrtech.music.http.HttpClient;
import com.ldrtech.music.model.DownloadInfo;
import com.ldrtech.music.model.Music;
import com.ldrtech.music.model.OnlineMusic;
import com.ldrtech.music.utils.FileUtils;

import java.io.File;

/**
 * Created by xiaoh on 2018/3/25.
 */

public abstract class PlayOnlineMusic extends PlayMusic {
    private OnlineMusic mOnlineMusic;

    public PlayOnlineMusic(Activity activity, OnlineMusic onlineMusic) {
        super(activity, 3);
        mOnlineMusic = onlineMusic;
    }

    @Override
    protected void getPlayInfo() {
        String artist = mOnlineMusic.getArtist_name();
        String title = mOnlineMusic.getTitle();

        music = new Music();
        music.setType(Music.Type.ONLINE);
        music.setTitle(title);
        music.setArtist(artist);
        music.setAlbum(mOnlineMusic.getAlbum_title());

        // 下载歌词
        String lrcFileName = FileUtils.getLrcFileName(artist, title);
        File lrcFile = new File(FileUtils.getLrcDir() + lrcFileName);
        if (!lrcFile.exists() && !TextUtils.isEmpty(mOnlineMusic.getLrclink())) {
            downloadLrc(mOnlineMusic.getLrclink(), lrcFileName);
        } else {
            mCounter++;
        }

        // 下载封面
        String albumFileName = FileUtils.getAlbumFileName(artist, title);
        File albumFile = new File(FileUtils.getAlbumDir(), albumFileName);
        String picUrl = mOnlineMusic.getPic_big();
        if (TextUtils.isEmpty(picUrl)) {
            picUrl = mOnlineMusic.getPic_small();
        }
        if (!albumFile.exists() && !TextUtils.isEmpty(picUrl)) {
            downloadAlbum(picUrl, albumFileName);
        } else {
            mCounter++;
        }
        music.setCoverPath(albumFile.getPath());

        // 获取歌曲播放链接
        HttpClient.getMusicDownloadInfo(mOnlineMusic.getSong_id(), new HttpCallback<DownloadInfo>() {
            @Override
            public void onSuccess(DownloadInfo response) {
                if (response == null || response.getBitrate() == null) {
                    onFail(null);
                    return;
                }

                music.setPath(response.getBitrate().getFile_link());
                music.setDuration(response.getBitrate().getFile_duration() * 1000);
                checkCounter();
            }

            @Override
            public void onFail(Exception e) {
                onExecuteFail(e);
            }
        });
    }

    private void downloadLrc(String url, String fileName) {
        HttpClient.downloadFile(url, FileUtils.getLrcDir(), fileName, new HttpCallback<File>() {
            @Override
            public void onSuccess(File file) {
            }

            @Override
            public void onFail(Exception e) {
            }

            @Override
            public void onFinish() {
                checkCounter();
            }
        });
    }

    private void downloadAlbum(String picUrl, String fileName) {
        HttpClient.downloadFile(picUrl, FileUtils.getAlbumDir(), fileName, new HttpCallback<File>() {
            @Override
            public void onSuccess(File file) {
            }

            @Override
            public void onFail(Exception e) {
            }

            @Override
            public void onFinish() {
                checkCounter();
            }
        });
    }
}
