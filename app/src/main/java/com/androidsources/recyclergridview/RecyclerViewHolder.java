package com.androidsources.recyclergridview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView wonderImage;



    public TextView wonderName;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        itemView.setOnClickListener(this);
        wonderImage = (ImageView)itemView.findViewById(R.id.wonder_image);
        wonderName = (TextView)itemView.findViewById(R.id.wonder_name);
    }

    @Override
    public void onClick(View view) {
        //Every time you click on the row toast is displayed
        Toast.makeText(view.getContext(),  "This is ",Toast.LENGTH_LONG).show();
    }


    public TextView getWonderName() {
        return wonderName;
    }

    public void setWonderName(TextView wonderName) {
        this.wonderName = wonderName;
    }

    public ImageView getWonderImage() {
        return wonderImage;
    }

    public void setWonderImage(ImageView wonderImage) {
        this.wonderImage = wonderImage;
    }

}