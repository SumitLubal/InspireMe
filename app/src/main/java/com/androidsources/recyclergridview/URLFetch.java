package com.androidsources.recyclergridview;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class URLFetch extends AsyncTask<String, String, String> {

    final String TAG = "AsyncTaskParseJson.java";

    // set your json string url here
    String yourJsonStringUrl = "http://sam042.netau.net/fetch.php";

    // contacts JSONArray
    JSONArray dataJsonArr = null;

    @Override
    protected void onPreExecute() {}

    @Override
    protected String doInBackground(String... arg0) {

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
                        + ", username: " + like+", "+dislike);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg) {}
}