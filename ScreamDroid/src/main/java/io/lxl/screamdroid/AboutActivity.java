package io.lxl.screamdroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * About activity - Shows links to homepage and external libraries
 * Created by pschmitt on 11/18/13.
 */
public class AboutActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        setContentView(R.layout.activity_about);
    }
}
