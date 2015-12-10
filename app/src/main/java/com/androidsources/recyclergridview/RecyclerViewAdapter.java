package com.androidsources.recyclergridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<RowData> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<RowData> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclingTest","onCreateViewHolder method is called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Log.d("RecyclingTest", "onBindViewHolder method is called");
        holder.wonderName.setText(itemList.get(position).getLikesAsAString());
        ImageLoader imageLoader = ImageLoader.getInstance();
        if(itemList.get(position).getWonderImageURL()==null){
            Log.d("Info", "URL is null");
            //holder.wonderImage.setImageResource(R.drawable.notfound);
        }else{
            if(itemList.get(position).getDownloadedImage()!=null){
                holder.wonderImage.setImageBitmap(itemList.get(position).getDownloadedImage());
            }else {
                //imageLoader.displayImage(itemList.get(position).getWonderImageURL(), holder.wonderImage);
                ImageSize targetSize = new ImageSize(MainActivity.width, MainActivity.height); // result Bitmap will be fit to this size

                imageLoader.loadImage(itemList.get(position).getWonderImageURL(), targetSize, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        for (int i = 0; i < itemList.size(); i++) {
                            if (itemList.get(i).getWonderImageURL().equals(imageUri)) {
                                //found object with refered URL
                                itemList.get(i).setDownloadedImage(loadedImage);
                                notifyDataSetChanged();
                                Log.d("Recycler View", "Image is downloaded");
                            }
                        }
                    }
                });
            }
        }
        //Toast.makeText(holder.wonderImage.getContext(), itemList.get(position).getWonderImageURL(), Toast.LENGTH_SHORT).show();
        if(position == itemList.size()-1){
            Log.d("RecyclerviewAdapter","Bottom is hit");

        }

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
    private class LongOperation extends AsyncTask<String, Void, String> {
        List<RowData> fetchList = new ArrayList<RowData>();
        @Override
        protected String doInBackground(String... params) {

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            // / might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
            
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
