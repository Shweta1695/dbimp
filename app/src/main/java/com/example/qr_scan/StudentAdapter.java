package com.example.qr_scan;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
//For recycler view
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<StudentModal> courseModalArrayList;
    private Context context;
    ArrayList<StudentModal> rollNo= new ArrayList<>();

    // constructor
    public StudentAdapter(ArrayList<StudentModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        StudentModal modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDescTV.setText(modal.getCourseDescription());
        holder.courseDurationTV.setText(modal.getCourseDuration());
        holder.courseTracksTV.setText(modal.getCourseTracks());
        holder.qrPlaceHolder.setImageBitmap(modal.getQrPlaceHolder(modal.getCourseDuration()));
        holder.qrPlaceHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_faceid);

                TextView stname,stroll,stcoursename,stmarks;
                ImageView bitmapqr;
                Button close = (Button) dialog.findViewById(R.id.buttonClose);
                stname =(TextView) dialog.findViewById(R.id.stname);
                stroll =(TextView) dialog.findViewById(R.id.stroll);
                stmarks =(TextView) dialog.findViewById(R.id.stmarks);
                stcoursename =(TextView) dialog.findViewById(R.id.stcoursename);
                bitmapqr=(ImageView) dialog.findViewById(R.id.bitmapqr);

                stname.setText(modal.getCourseName());
                stroll.setText(modal.getCourseDuration());
                stmarks.setText(modal.getCourseTracks());
                stcoursename.setText(modal.getCourseDescription());
                bitmapqr.setImageBitmap(modal.getQrPlaceHolder(modal.getCourseDuration()));

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                     }
                });
                dialog.show();
                dialog.setCancelable(false);
        }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView courseNameTV, courseDescTV, courseDurationTV, courseTracksTV;
        private  ImageView qrPlaceHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseTracksTV = itemView.findViewById(R.id.idTVCourseTracks);
            qrPlaceHolder=itemView.findViewById(R.id.qrPlaceHolder);

        }
    }
}
