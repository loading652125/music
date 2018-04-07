package com.ldrtech.music.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.TextUtils;

import com.hwangjr.rxbus.RxBus;
import com.ldrtech.music.R;
import com.ldrtech.music.constants.RxBusTags;
import com.ldrtech.music.utils.Preferences;

/**
 * Created by zjs on 2018/3/25.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    private Preference mSoundEffect;
    private Preference mFilterSize;
    private Preference mFilterTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_setting);

        mFilterSize = findPreference(getString(R.string.setting_key_filter_size));
        mFilterTime = findPreference(getString(R.string.setting_key_filter_time));
        mFilterSize.setOnPreferenceChangeListener(this);
        mFilterTime.setOnPreferenceChangeListener(this);

        mFilterSize.setSummary(getSummary(Preferences.getFilterSize(), R.array.filter_size_entries, R.array.filter_size_entry_values));
        mFilterTime.setSummary(getSummary(Preferences.getFilterTime(), R.array.filter_time_entries, R.array.filter_time_entry_values));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mFilterSize) {
            Preferences.saveFilterSize((String) newValue);
            mFilterSize.setSummary(getSummary(Preferences.getFilterSize(), R.array.filter_size_entries, R.array.filter_size_entry_values));
            RxBus.get().post(RxBusTags.SCAN_MUSIC, new Object());
            return true;
        } else if (preference == mFilterTime) {
            Preferences.saveFilterTime((String) newValue);
            mFilterTime.setSummary(getSummary(Preferences.getFilterTime(), R.array.filter_time_entries, R.array.filter_time_entry_values));
            RxBus.get().post(RxBusTags.SCAN_MUSIC, new Object());
            return true;
        }
        return false;
    }

    private String getSummary(String value, int entries, int entryValues) {
        String[] entryArray = getResources().getStringArray(entries);
        String[] entryValueArray = getResources().getStringArray(entryValues);
        for (int i = 0; i < entryValueArray.length; i++) {
            String v = entryValueArray[i];
            if (TextUtils.equals(v, value)) {
                return entryArray[i];
            }
        }
        return entryArray[0];
    }
}
