package com.shifterandworker;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class ForgotActivity extends AppCompatActivity {
    Button btnForgot;
    EditText etForgotPass;
    RelativeLayout rlt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        btnForgot = findViewById(R.id.btnForgot);
        etForgotPass = findViewById(R.id.etForgotPass);
        rlt2 = findViewById(R.id.rlt2);

        AnimationDrawable animationDrawable = (AnimationDrawable) rlt2.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }
}
