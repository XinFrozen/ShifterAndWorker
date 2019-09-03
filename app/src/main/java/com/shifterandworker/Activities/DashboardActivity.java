package com.shifterandworker.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shifterandworker.Fragments.HomeFragment;
import com.shifterandworker.Messaging.MainActivity;
import com.shifterandworker.Messaging.RegisterActivity;
import com.shifterandworker.Messaging.StartActivity;
import com.shifterandworker.Models.Post;
import com.shifterandworker.R;

public class DashboardActivity extends AppCompatActivity {

    private static final int PReqCode = 2 ;
    private static final int REQUESCODE = 2 ;
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    de.hdodenhof.circleimageview.CircleImageView userPhoto;
    Dialog popAddPost ;
    ImageView popupUserImage,popupPostImage,popupAddBtn;
    TextView popupTitle,popupDescription;
    ProgressBar popupClickProgress;
    LinearLayout linearLayout;
    private Uri pickedImgUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ini

        linearLayout = findViewById(R.id.btnExit);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        userPhoto = findViewById(R.id.personView);


        // now we will use Glide to load user image
        // first we need to import the library

        Glide.with(this).load(currentUser.getPhotoUrl()).into(userPhoto);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });

        FloatingActionButton fab_menu_1 = (FloatingActionButton) findViewById(R.id.fab_menu_1);
        FloatingActionButton fab_menu_2 = (FloatingActionButton) findViewById(R.id.fab_menu_2);

        fab_menu_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, Home.class);
                startActivity(intent);

            }
        });
        fab_menu_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }


}
