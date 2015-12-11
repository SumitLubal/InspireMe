package com.androidsources.recyclergridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yalantis.phoenix.PullToRefreshView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    static int  width;
    static int  height;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    private PullToRefreshView mPullToRefreshView;
    public static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get device size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        //setting up imageloader library which will do fetch of imagesFile cacheDir = StorageUtils.getCacheDirectory(context);
        configureImageLoaderLibrary();
        //Setting up the toolbar
        Toolbar toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        List<RowData> rowListItem = getRowList();
        layoutManager = new GridLayoutManager(MainActivity.this, 2);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);
        recyclerView.setAdapter(adapter);
        if(adapter!=null) {
            new URLFetch(this).execute();
            URLFetch.setActiviy(this);
        }else{
            Toast.makeText(this,"Failed to start adapter",Toast.LENGTH_SHORT).show();
        }
        //adding pull to refresh interface
        configPullToRefresh();

    }

    private void configureImageLoaderLibrary() {
        File cacheDir = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this);
        builder.memoryCacheExtraOptions(width, height);
        builder.diskCacheExtraOptions(width, height, null);
        builder.threadPoolSize(6);
        builder.threadPriority(Thread.NORM_PRIORITY - 2);
        builder.tasksProcessingOrder(QueueProcessingType.FIFO);
        builder.memoryCacheSizePercentage(13);
        builder.diskCache(new UnlimitedDiskCache(cacheDir));
        builder.diskCacheSize(50 * 1024 * 1024);
        builder.diskCacheFileCount(100);
        builder.diskCacheFileNameGenerator(new HashCodeFileNameGenerator());
        builder.imageDownloader(new BaseImageDownloader(this));
        builder.defaultDisplayImageOptions(DisplayImageOptions.createSimple());
        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);
    }

    private void configPullToRefresh() {
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        if(URLFetch.activity!=null){
                            new URLFetch().execute();
                            Log.d(TAG,"Fetching URL now");
                        }else{
                            Log.d(TAG,"URLFetch doesn't have activity");
                        }
                    }
                }, 3500);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    private List<RowData> getRowList(){

        List<RowData> currentItem = new ArrayList<RowData>();
        return currentItem;
    }
}
