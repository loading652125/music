package com.ldrtech.music.http;

/**
 * Created by zjs on 2018/3/25.
 */

public abstract class HttpCallback<T> {
    public abstract void onSuccess(T t);

    public abstract void onFail(Exception e);

    public void onFinish() {
    }
}
