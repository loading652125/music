package com.ldrtech.music.enums;

/**
 * Created by xiaoh on 2018/3/25.
 */

public enum PlayModeEnum {
    LOOP(0),
    SHUFFLE(1),
    SINGLE(2);

    private int value;

    PlayModeEnum(int value) {
        this.value = value;
    }

    public static PlayModeEnum valueOf(int value) {
        switch (value) {
            case 1:
                return SHUFFLE;
            case 2:
                return SINGLE;
            case 0:
            default:
                return LOOP;
        }
    }

    public int value() {
        return value;
    }
}
