package com.androidsources.recyclergridview;

import android.annotation.SuppressLint;
import android.inputmethodservice.Keyboard;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class fullscreenimageactivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */

    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private boolean mVisible;
    private ViewPager mViewPager;
    private CustomPagerAdapter mCustomPagerAdapter;
    int position;
    List<RowData> list;
    private String currentURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreenimageactivity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //recieve list of data
        //recieve clicked position
        Bundle bundle = getIntent().getExtras();
        currentURL = bundle.getString("currentitem");
        list = RecyclerViewAdapter.itemList;
        if(list!=null) {
            Toast.makeText(this, "Recieved: " + list.size(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "nothing ", Toast.LENGTH_SHORT).show();
        }
        mVisible = true;
        mCustomPagerAdapter = new CustomPagerAdapter(this,list);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        //get current view
        int position = 0;
        //mViewPager.setCurrentItem(position);
        
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */


}
