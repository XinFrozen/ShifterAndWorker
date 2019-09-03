package com.shifterandworker.Activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shifterandworker.R;
import com.shifterandworker.Storage.ShrdPrferencs;

public class LoginActivity extends AppCompatActivity {


    private EditText userMail,userPassword;
    private Button btnLogin;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent DashboardActivity;
    private ImageView loginPhoto;

    TextView forgotPass;
    RelativeLayout rlt;
    CheckBox chkBeniHatirla;
    Animation btnAnim;

    Context context = this;


    ShrdPrferencs shrdPrferencs;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        manager = getFragmentManager();
        rlt = findViewById(R.id.rlt);
        userMail = findViewById(R.id.login_mail);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.loginBtn);
        loginProgress = findViewById(R.id.login_progress);
        mAuth = FirebaseAuth.getInstance();
        DashboardActivity = new Intent(this, com.shifterandworker.Activities.DashboardActivity.class);
        loginPhoto = findViewById(R.id.login_photo);
        forgotPass = findViewById(R.id.forgotPass);
        chkBeniHatirla = findViewById(R.id.chkBeniHatirla);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        AnimationDrawable animationDrawable = (AnimationDrawable) rlt.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        shrdPrferencs = new ShrdPrferencs();
        if (shrdPrferencs.getValueBoolean(context, "remember")) {//beni hatırla işaretlenmişse
            userMail.setText(shrdPrferencs.getValueString(context, "username"));
            chkBeniHatirla.setChecked(shrdPrferencs.getValueBoolean(context, "remember"));
        }

        loginPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerActivity = new Intent(context,RegisterActivity.class);
                startActivity(registerActivity);
                finish();


            }
        });

        loginProgress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setAnimation(btnAnim);
                loginProgress.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if (chkBeniHatirla.isChecked()) {
                    shrdPrferencs.saveString(context, "username", userMail.getText().toString());
                } else {
                    shrdPrferencs.saveString(context, "username", "");
                }
                shrdPrferencs.saveBoolean(context, "remember", chkBeniHatirla.isChecked());

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify All Field");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    signIn(mail,password);
                }




            }
        });


    }

    private void signIn(String mail, String password) {


        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    loginProgress.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();

                }
                else {
                    showMessage(task.getException().getMessage());
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }


            }
        });



    }

    private void updateUI() {

        startActivity(DashboardActivity);
        finish();

    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            //user is already connected  so we need to redirect him to home page
            updateUI();

        }

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotActivity.class));
            }
        });

    }
}
