package io.lxl.ScreamDroid;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pschmitt on 11/16/13.
 *
 */
public class ScreamActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scream);
        TextView textView  = (TextView) findViewById(R.id.textScream);
        String text = getIntent().getStringExtra(getString(R.string.input_text));
        textView.setText(text);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button redo = (Button) findViewById(R.id.buttonRedo);
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
