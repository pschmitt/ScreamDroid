package io.lxl.screamdroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Settings screen
 * Created by pschmitt on 11/17/13.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
