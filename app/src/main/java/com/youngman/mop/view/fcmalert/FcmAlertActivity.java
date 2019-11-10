package com.youngman.mop.view.fcmalert;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.youngman.mop.R;

public class FcmAlertActivity extends Activity {

    private Context context;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_fcm_alert);
        init();
    }

    private void init() {
        context = getApplicationContext();
        tvMessage = (TextView) findViewById(R.id.tv_message);

        String message = getIntent().getStringExtra("EXTRA_MESSAGE");
        tvMessage.setText(message);
    }


}
