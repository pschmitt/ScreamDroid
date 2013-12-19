package io.lxl.screamdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, TextInputFragment.OnTextUpdatedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private TextInputFragment mTextInputFragment;
    private PreviewFragment mPreviewFragment;
    private boolean mNaviFirstHit = false;
    private boolean mIsEditTextShow = true;
    private String mScreamText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mTextInputFragment = new TextInputFragment();
        mPreviewFragment = new PreviewFragment();
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mTextInputFragment)
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // Quick and dirty hack
        if (!mNaviFirstHit) {
            mNaviFirstHit = true;
            return;
        }
        switch (position) {
            case 0:
                startActivity(new Intent(this, SnippetsActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            default:
                Log.d("MainActivity", "Selected: ? (" + position + ")");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            //restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_scream:
                scream();
                return true;
            case R.id.action_preview:
                togglePreview();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String processString(String input) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.getBoolean(getString(R.string.pref_key_upper_case), false)) {
            input = input.toUpperCase();
        }
        return input;
    }

    private void togglePreview() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mIsEditTextShow) {
            fragmentManager.beginTransaction()
                .replace(R.id.container, mPreviewFragment)
                .commit();
            if (mPreviewFragment != null)
                mPreviewFragment.updatePreviewText(processString(mScreamText));
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, mTextInputFragment)
                    .commit();
        }
        mIsEditTextShow = !mIsEditTextShow;
    }

    public void scream() {
        try {
            String text = "defaultText";
            EditText screamInput = (EditText)findViewById(R.id.input_scream);
            if (screamInput.getText() != null)
                text = processString(screamInput.getText().toString());
            Intent screamIntent = new Intent(this, ScreamActivity.class);
            if (text == null || text.length() == 0) {
                Toast.makeText(this, getString(R.string.toast_no_input), Toast.LENGTH_SHORT).show();
            } else {
                screamIntent.putExtra(getString(R.string.extra_scream_text), text);
                startActivity(screamIntent);
            }
        } catch (NullPointerException e) {
            Log.e("Fatal error", "EditText.getText == NULL !");
        }
    }

    @Override
    public void onTextUpdated(String newText) {
        mScreamText = newText;
        //if (mPreviewFragment != null)
        //    mPreviewFragment.updatePreviewText(processString(newText));
    }
}
