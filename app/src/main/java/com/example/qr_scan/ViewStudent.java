package com.example.qr_scan;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ViewStudent extends AppCompatActivity {

        // creating variables for our array list,
        // dbhandler, adapter and recycler view.
        private ArrayList<StudentModal> courseModalArrayList ;
        private List<String> rollNo = null;
        private MyDBHandler dbHandler;
        private StudentAdapter courseRVAdapter;
        private RecyclerView coursesRV;
        //ImageView qrPlaceHolder;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_student);
            ImageView qrPlaceHolder=(ImageView)findViewById(R.id.qrPlaceHolder);


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
    }
