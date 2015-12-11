package com.androidsources.recyclergridview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;


public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView wonderImage;
    public String URL;
    public TextView wonderName;
    ProgressWheel wheel;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        itemView.setOnClickListener(this);
        wonderImage = (ImageView)itemView.findViewById(R.id.wonder_image);
        wonderName = (TextView)itemView.findViewById(R.id.wonder_name);
        wheel = (ProgressWheel)itemView.findViewById(R.id.progress_wheel);
        wheel.setBarColor(Color.BLUE);
        wheel.setEnabled(true);
        wheel.spin();
    }

    @Override
    public void onClick(View view) {
        //Every time you click on the row toast is displayed
        Toast.makeText(view.getContext(),  "This is "+URL,Toast.LENGTH_SHORT).show();
    }


    public void setReferenceURL(String url) {
        this.URL = url;
    }
    public String  getReferenceURL(){
        return URL;
    }
}