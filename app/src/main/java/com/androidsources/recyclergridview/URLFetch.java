package com.androidsources.recyclergridview;

import android.os.AsyncTask;
import android.util.Log;

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
    List<RowData> list = new ArrayList<RowData>();
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
                list.add(new RowData(url,likes,unlikes,type));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {
        activity.adapter.itemList.addAll(list);
        activity.adapter.notifyDataSetChanged();
    }
}