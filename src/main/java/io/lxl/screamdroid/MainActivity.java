package io.lxl.screamdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private boolean mNaviFirstHit = false;

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
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new PlaceholderFragment())
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_scream:
                scream();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void scream() {
        try {
            String text = "defaultText";
            EditText screamInput = (EditText)findViewById(R.id.input_scream);
            if (screamInput.getText() != null)
                text = screamInput.getText().toString();
            Intent screamIntent = new Intent(this, ScreamActivity.class);
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            if (sharedPref.getBoolean(getString(R.string.pref_key_upper_case), false)) {
                text = text.toUpperCase();
            }
            if (text.isEmpty()) {
                // TODO Error handling
                Toast.makeText(this, "Please enter some text first", Toast.LENGTH_SHORT).show();
            } else {
                // text = getString(R.string.lorem_ipsorum);
                screamIntent.putExtra(getString(R.string.extra_scream_text), text);
                startActivity(screamIntent);
            }
        } catch (NullPointerException e) {
            Log.e("Fatal error", "EditText.getText == NULL !");
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private EditText mScreamInput;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mScreamInput = (EditText) rootView.findViewById(R.id.input_scream);
            mScreamInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
            mScreamInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        ((MainActivity) getActivity()).scream();
                        return true;
                    }
                    return false;
                }
            });
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if (sharedPref.getBoolean(getString(R.string.pref_key_discard_input), false)) {
                mScreamInput.setText("");
            }
        }
    }

}
