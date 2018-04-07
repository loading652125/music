package com.ldrtech.music.activity;


import android.os.Bundle;

import com.ldrtech.music.R;
import com.ldrtech.music.fragment.SettingFragment;


/**
 * Created by xiaoh on 2018/3/25.
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void onServiceBound() {
        SettingFragment settingFragment = new SettingFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.ll_fragment_container, settingFragment)
                .commit();
    }
}
