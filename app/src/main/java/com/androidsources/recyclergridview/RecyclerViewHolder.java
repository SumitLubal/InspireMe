package com.androidsources.recyclergridview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;


public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private MainActivity activity;
    public ImageView wonderImage;
    public String URL;
    public TextView wonderName;

    public ProgressWheel getWheel() {
        return wheel;
    }

    public void setWheel(ProgressWheel wheel) {
        this.wheel = wheel;
    }

    ProgressWheel wheel ;
    public RecyclerViewHolder(View itemView,MainActivity activity) {
        super(itemView);
        //implementing onClickListener
        this.activity = activity;
        itemView.setOnClickListener(this);
        wonderImage = (ImageView)itemView.findViewById(R.id.wonder_image);
        wonderName = (TextView)itemView.findViewById(R.id.wonder_name);
        wheel = (ProgressWheel) itemView.findViewById(R.id.progress_wheel);

    }


    @Override
    public void onClick(View view) {
        //Every time you click on the row toast is displayed
        Intent intent = new Intent(activity, fullscreenimageactivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("currentitem",URL);
        intent.putExtras(bundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, (View) wonderImage, "profile");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivity(intent, options.toBundle());
        }else{
            activity.startActivity(intent);
        }
    }


    public void setReferenceURL(String url) {
        this.URL = url;
    }
    public String  getReferenceURL(){
        return URL;
    }
}