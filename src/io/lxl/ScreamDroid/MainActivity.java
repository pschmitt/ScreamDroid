package io.lxl.ScreamDroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {


    /**
     * Called when the activity is first created.
     */

    private EditText mEDitTExtInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mEDitTExtInput = (EditText) findViewById(R.id.textFieldInput);
        mEDitTExtInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mEDitTExtInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    scream();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_scream:
                scream();
                break;
            case R.id.action_settings:
                settings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.getBoolean(getString(R.string.pref_erase), true)) {
            mEDitTExtInput.setText("");
        }
        mEDitTExtInput.requestFocus();
    }

    private void scream() {
        try {
            String text = mEDitTExtInput.getText().toString();
            Intent screamIntent = new Intent(this, ScreamActivity.class);
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            if (sharedPref.getBoolean(getString(R.string.pref_upper_case), false)) {
                text = text.toUpperCase();
            }
            screamIntent.putExtra(getString(R.string.input_text), text);
            startActivity(screamIntent);
        } catch (NullPointerException e) {
            Log.e(getString(R.string.log_error), "EditText.getText == NULL !");
        }
    }

    private void settings() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

}
