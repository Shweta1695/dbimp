package com.example.qr_scan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ViewStudent extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener   {

        // creating variables for our array list,
        // dbhandler, adapter and recycler view.
        private ArrayList<StudentModal> courseModalArrayList ;
        private List<String> rollNo = null;
        private MyDBHandler dbHandler;
        private StudentAdapter courseRVAdapter;
        private RecyclerView coursesRV;
        protected BottomNavigationView navigationView;
      //  private ImageButton btn_edit,btn_delete;
    //ImageView qrPlaceHolder;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_student);

            ImageView qrPlaceHolder=(ImageView)findViewById(R.id.qrPlaceHolder);
            navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
            navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.action_add:
                            overridePendingTransition(R.anim.slide_out_down, R.anim.no_anim);
                            Intent intent1 = new Intent(ViewStudent.this, MainActivity.class);
                            startActivity(intent1);
                            break;

                        case R.id.action_view:
                            overridePendingTransition(R.anim.no_anim, R.anim.slide_out_down);
                            Intent intent2 = new Intent(ViewStudent.this, ViewStudent.class);
                            startActivity(intent2);
                            break;
                    }


                    return false;
                }
            });

            // initializing our all variables.
            courseModalArrayList = new ArrayList<>();
            dbHandler = new MyDBHandler(ViewStudent.this);


            // getting our course array
            // list from db handler class.
            courseModalArrayList = dbHandler.readCourses();

            // on below line passing our array list to our adapter class.
            courseRVAdapter = new StudentAdapter(courseModalArrayList, ViewStudent.this);
            coursesRV = findViewById(R.id.idRVCourses);


            //String rollNo=new conRollQr();
            // setting layout manager for our recycler view.
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewStudent.this, RecyclerView.VERTICAL, false);
            coursesRV.setLayoutManager(linearLayoutManager);

            // setting our adapter to recycler view.
            coursesRV.setAdapter(courseRVAdapter);


            rollNo= new ArrayList<>();
            rollNo= dbHandler.conRollQr();

            for(int i=0;i< rollNo.size();i++) {

                QRGEncoder qrgEncoder = new QRGEncoder(rollNo.get(i).toString(), null, QRGContents.Type.TEXT, 500);

                try {
                    Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                    //qrPlaceHolder.setImageBitmap(qrBits);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        }
    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    private int getNavigationMenuItemId() {
        return R.id.action_view;
    }


    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
