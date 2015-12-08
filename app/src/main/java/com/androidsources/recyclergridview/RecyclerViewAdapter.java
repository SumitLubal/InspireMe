package com.androidsources.recyclergridview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

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
        holder.wonderName.setText(itemList.get(position).getWonderName());
        ImageLoader imageLoader = ImageLoader.getInstance();
        if(itemList.get(position).getWonderImageURL()==null){
            Log.d("Info","URL is null");
            holder.wonderImage.setImageResource(R.drawable.notfound);
        }else{
            imageLoader.displayImage(itemList.get(position).getWonderImageURL(), holder.wonderImage);
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
}
