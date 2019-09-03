package com.shifterandworker.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.shifterandworker.R;

public class ForgotActivity extends AppCompatActivity {
    Button btnForgot;
    EditText etForgotPass;
    RelativeLayout rlt2;
    Animation btnAnim ;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        btnForgot = findViewById(R.id.btnForgot);
        etForgotPass = findViewById(R.id.etForgotPass);
        rlt2 = findViewById(R.id.rlt2);

        firebaseAuth = FirebaseAuth.getInstance();

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_rotate);

        AnimationDrawable animationDrawable = (AnimationDrawable) rlt2.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etForgotPass.getText().toString();

                if (email.equals("")){
                    Toast.makeText(ForgotActivity.this,"Alanlar doldurulmalidir!",Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgotActivity.this,"Email adresinizden sifirlama linkini kontrol ediniz.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(ForgotActivity.this,error,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        btnForgot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });
    }
}
