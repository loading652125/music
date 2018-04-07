package com.ldrtech.music.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.ldrtech.music.BuildConfig;
import com.ldrtech.music.R;

/**
 * Created by zjs on 2018/4/7.
 */

public class AboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private Preference mVersion;
    private Preference mShare;
    private Preference mStar;
    private Preference mGithub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_about);

        mVersion = findPreference("version");
        mShare = findPreference("share");
        mStar = findPreference("star");
        mGithub = findPreference("github");

        mVersion.setSummary("v " + BuildConfig.VERSION_NAME);
        setListener();
    }

    private void setListener() {
        mShare.setOnPreferenceClickListener(this);
        mStar.setOnPreferenceClickListener(this);
        mGithub.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mShare) {
            share();
            return true;
        } else if (preference == mStar) {
            openUrl(getString(R.string.about_project_url));
            return true;
        } else if (preference == mGithub) {
            openUrl(preference.getSummary().toString());
            return true;
        }
        return false;
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app, getString(R.string.app_name)));
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
