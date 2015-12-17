package com.androidsources.recyclergridview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;


class CustomPagerAdapter extends PagerAdapter  {

    fullscreenimageactivity mContext;
    LayoutInflater mLayoutInflater;


    Button share,like,dislike;
    ImageView imageview;
    private List<RowData> list_images;

    public CustomPagerAdapter(fullscreenimageactivity activity,List<RowData> list) {
        list_images = list;
        mContext = activity;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout)object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(list_images.get(position).getDownloadedImage());
        share = (Button) itemView.findViewById(R.id.shareBtn);
        like  = (Button) itemView.findViewById(R.id.likeBtn);
        dislike = (Button) itemView.findViewById(R.id.unlikeBtn);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }



    int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}