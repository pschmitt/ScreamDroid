package io.lxl.screamdroid;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Settings Fragment, to be displayed by PreferenceActivity
 * Created by pschmitt on 11/17/13.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
