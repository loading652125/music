package com.ldrtech.music.activity;

import android.os.Bundle;

import com.ldrtech.music.R;
import com.ldrtech.music.fragment.AboutFragment;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getFragmentManager().beginTransaction().replace(R.id.ll_fragment_container, new AboutFragment()).commit();
    }

}
