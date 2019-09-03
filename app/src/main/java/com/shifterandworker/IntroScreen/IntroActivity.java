package com.shifterandworker.IntroScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.shifterandworker.Activities.LoginActivity;
import com.shifterandworker.R;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    TextView tvSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    //Ilk giris kontrolunun tutuldugu yer

        if (restorePrefData()) {

            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();


        }

        setContentView(R.layout.activity_intro);

        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // Ekrani doldurma

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Shift İsteği Gönder", "Çalışmak istediğin zamanları yöneticine bildirerek istekte bulun.Senin için en uygun zamanları çalışmaya ayır", R.drawable.img1));
        mList.add(new ScreenItem("Shift İsteği Gönder", "Çalışmak istediğin zamanları yöneticine bildirerek istekte bulun.Senin için en uygun zamanları çalışmaya ayır", R.drawable.img1));
        mList.add(new ScreenItem("Şirketin Hedefini Anlık İzle", "Çalıştığın şirketin hedefi senin hedefin olsun", R.drawable.img2));
        mList.add(new ScreenItem("Anlık Bildirim ve Mesaj", "Şirketindeki anlık gelişmeleri bildirimlerden takip et ve çalışma arkadaşlarınla kontakt kur", R.drawable.img3));

        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // Viewpager ile tablayout ayari

        tabIndicator.setupWithViewPager(screenPager);

        // next buton click listener fonksiyonu

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);


                }

                if (position == mList.size() - 1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }


            }
        });



        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size() - 1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // Baslat butonu ne yapacak kontrolu

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //open main activity

                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
                savePrefsData();
                finish();


            }
        });

        // Skip butonu kontrolu

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });


    }

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend", false);
        return isIntroActivityOpnendBefore;


    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend", true);
        editor.commit();


    }

    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
        btnGetStarted.setAnimation(btnAnim);


    }
}
