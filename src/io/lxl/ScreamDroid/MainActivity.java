package io.lxl.ScreamDroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        mScreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scream();
            }
        });
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
    protected void onResume() {
         super.onResume();
         mEDitTExtInput.setText("");
         mEDitTExtInput.requestFocus();
    }

    private void scream() {
        try {
            String text = mEDitTExtInput.getText().toString();
            Intent screamIntent = new Intent(this, ScreamActivity.class);
            screamIntent.putExtra(getString(R.string.input_text), text);
            startActivity(screamIntent);
        } catch (NullPointerException e) {
            Log.e(getString(R.string.log_error), "EditText.getText == NULL !");
        }
    }

}
