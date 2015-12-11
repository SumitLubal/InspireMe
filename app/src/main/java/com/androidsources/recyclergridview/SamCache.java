package com.androidsources.recyclergridview;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.Log;

import com.vincentbrison.openlibraries.android.dualcache.lib.DualCache;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheBuilder;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheContextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 12/12/15.
 */


//usage
/*
    SamCache instance = SamCache.getCacher();
    instance.configure();
       //to write data use writeaslist
       //to read use readaslist



*/
public class SamCache {
    public static String TAG = "SamCache";
    String key="data";
    private static SamCache instance;
    Context m_context;
    DualCache<RowData[]> cache;
    Files customCacher;
    String path="urls.txt";
    static public SamCache getCacher(){
        if(instance == null){
            Log.d(TAG,"Please configure Cache before use!!");
        }
        return instance;
    }
    private SamCache(Context context){
        m_context = context;
        /*
        DualCacheContextUtils.setContext(context);
        cache = new DualCacheBuilder<RowData[]>("CACHEFORURL", MainActivity.VERSION, RowData[].class)
                .useDefaultSerializerInRam(50)
                .useDefaultSerializerInDisk(100, true);*/
        customCacher = new Files(context);
    }
    public static void configure(Context context){
        if(instance==null){
            instance = new SamCache(context);
            Log.d(TAG,"Created new SamCache instance");
        }
    }
    /*public RowData[] getFromCache(){
        RowData[] object = null;
        object = cache.get(key);
        return object;
    }*/

    /*public void writeToCache(RowData[] data){
        Log.d(TAG,"Writting data");
        cache.put(key, data);
    }*/
    public void writeAsList(List<RowData> data){
        /*
        RowData[] rows =new RowData[data.size()];
        for( int i=0;i<rows.length;i++){
            rows[i] = data.get(i);
        }
        writeToCache(rows);*/
        customCacher.clear(path);
        for(RowData row :data) {
            customCacher.append(path, row.getWonderImageURL());
            Log.d(TAG,"Adding "+row.getWonderImageURL()+" to cache");
        }

    }
    public List readAsList(){
        /*RowData rows[] = getFromCache();
        if(rows!=null) {
            ArrayList<RowData> listFromCache = new ArrayList<RowData>();
            for (RowData row : rows) listFromCache.add(row);
            return listFromCache;
        }else{
            Log.d(TAG,"no data in cache!");
            return null;
        }*/
        ArrayList<RowData> listFromCache = new ArrayList<RowData>();
        String[] fileText = customCacher.read(path);
        if(fileText!=null){
            Log.d(TAG,"Totle "+ fileText.length + "rows in cache");
        for(int i = 0;i<fileText.length;i++){
            listFromCache.add(new RowData(fileText[i]));
            Log.d(TAG,"Data From Cache: "+fileText[i]);
        }
        }else{
            Log.d(TAG,"no data in cache!");
        }
        return listFromCache;
    }

}
