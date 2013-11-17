package io.lxl.ScreamDroid;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by pschmitt on 11/17/13.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
