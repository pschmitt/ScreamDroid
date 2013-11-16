package io.lxl.ScreamDroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Button mScreamButton;
    private EditText mEDitTExtInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mScreamButton = (Button) findViewById(R.id.buttonScream);
        mEDitTExtInput = (EditText) findViewById(R.id.textFieldInput);
    }
}
