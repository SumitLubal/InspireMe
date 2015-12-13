package com.androidsources.recyclergridview;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class URLFetch extends AsyncTask<String, String, String> {

    final String TAG = "AsyncTaskParseJson.java";
    static MainActivity activity;

    // set your json string url here
    String yourJsonStringUrl = "http://sam042.netau.net/fetch.php";

    // contacts JSONArray
    JSONArray dataJsonArr = null;
    List<RowData> list;
    public URLFetch(MainActivity main_activity) {
        setActiviy(main_activity);
    }
    public URLFetch(){

    }
    public static void setActiviy(MainActivity main_activity){
        activity = main_activity;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... ar) {
        try {
            list = new ArrayList<RowData>();
            list.addAll(activity.adapter.itemList);
            //open cache
            SamCache cacher = SamCache.getCacher();
            //get from cache
            List<RowData> fromCache=null,fromInternet=null;
            if(cacher!=null){
                fromCache = cacher.readAsList();
            }
            //if internet is available then get data from internet
            //yes-? then get data and check for repeatative data with Primary key as URL and return both
            //no? return only cached data with checking if something is repeated

            if(activity.isNetworkAvailable()){
                fromInternet = getListFromInternet();
                if(fromCache!=null&&fromInternet!=null){
                    for(RowData row :fromInternet){
                        if(!fromCache.contains(row)){
                            fromCache.add(row);
                            Log.d(TAG,"New URL in internet "+row.getWonderImageURL());
                        }
                    }
                    for(RowData row : fromCache){
                        if(!list.contains(row)){
                            list.add(row);
                        }
                    }
                    cacher.writeAsList(list);
                    fromCache = null;
                    fromInternet =null;
                }else{
                    if(fromCache!=null){
                        for(RowData row: fromCache){
                            if(!list.contains(row)){
                                list.add(row);
                            }
                        }
                        fromCache =null;
                    }else {
                        if (fromInternet != null) {
                            for (RowData row : fromInternet) {
                                if (!list.contains(row)) {
                                    list.add(row);
                                }
                            }
                            if (cacher!=null) {
                                Log.d(TAG,"adding data to cache!!");
                                cacher.writeAsList(list);
                            }else{
                                Log.d(TAG,"cacher is  null");
                            }
                            fromInternet = null;
                        }
                    }
                    cacher.writeAsList(list);
                }
            }else {
                Log.d(TAG, "no internet connection");
                if (null != fromCache) {
                    for (RowData row : fromCache) {
                        if (!list.contains(row)) {
                            list.add(row);
                        }
                    }
                    fromCache = null;
                }
            }

        } catch (JSONException e ) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<RowData> getListFromInternet() throws JSONException{
        List<RowData> fromInternet = new ArrayList<RowData>();
        // instantiate our json parser
        JsonParser jParser = new JsonParser();

        // get json string from url
        JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);

        // get the array of users
        dataJsonArr = json.getJSONArray("urls");

        // loop through all users
        for (int i = 0; i < dataJsonArr.length(); i++) {
            JSONObject c = dataJsonArr.getJSONObject(i);
            // Storing each json item in variable
            String type = c.getString("type");
            String url = c.getString("url");
            String like = c.getString("like");
            String dislike = c.getString("dislike");

            // show the values in our logcat
            Log.e(TAG, "firstname: " + type
                    + ", lastname: " + url
                    + ", username: " + like + ", dislikes" + dislike);

            int likes = Integer.parseInt(like);
            int unlikes = Integer.parseInt(dislike);
            fromInternet.add(new RowData(url, likes, unlikes, type));
        }
        return fromInternet;
    }


    @Override
    protected void onPostExecute(String strFromDoInBg) {
        //activity.adapter.itemList.addAll(list);
        for(RowData row:list){
            if(!activity.adapter.itemList.contains(row)){
                activity.adapter.itemList.add(row);
            }
        }
        activity.adapter.notifyDataSetChanged();
    }
}