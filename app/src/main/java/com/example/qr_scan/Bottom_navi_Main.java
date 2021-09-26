package com.example.qr_scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bottom_navi_Main extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navi);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        overridePendingTransition(R.anim.no_anim, R.anim.slide_out_down);
                        Intent intent1 = new Intent(Bottom_navi_Main.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.action_view:
                        overridePendingTransition(R.anim.no_anim, R.anim.slide_out_down);
                        Intent intent2 = new Intent(Bottom_navi_Main.this, ViewStudent.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
        animation();

    }

    private void animation() {
        final ImageView imageView = findViewById(R.id.spin_loader);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);


        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
                                           @Override
                                           public void onAnimationStart(Animation animation) {

                                           }

                                           @Override
                                           public void onAnimationEnd(Animation animation) {
                                               finish();
                                               overridePendingTransition(R.anim.no_anim, R.anim.slide_out_down);
                                               Intent intent = new Intent(Bottom_navi_Main.this, MainActivity.class);
                                               startActivity(intent);
                                           }

                                           @Override
                                           public void onAnimationRepeat(Animation animation) {

                                           }

                                       }
        );


    }
}
