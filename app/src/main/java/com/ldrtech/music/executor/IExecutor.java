package com.ldrtech.music.executor;

/**
 * Created by zjs on 2018/3/25.
 */

public interface IExecutor<T> {
    void execute();

    void onPrepare();

    void onExecuteSuccess(T t);

    void onExecuteFail(Exception e);
}
