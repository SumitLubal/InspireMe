package com.androidsources.recyclergridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    static int  width;
    static int  height;
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
        //Setting up the toolbar
        Toolbar toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        List<RowData> rowListItem = getRowList();
        layoutManager = new GridLayoutManager(MainActivity.this, 2);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);
        recyclerView.setAdapter(adapter);
        //new URLFetch().execute();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<RowData> getRowList(){

        List<RowData> currentItem = new ArrayList<RowData>();
        currentItem.add(new RowData("https://lh3.googleusercontent.com/-1V63lb__1G8/U9h5MNZG-6I/AAAAAAAAAGc/isVo0d8zXo8/w500-h400/Inspire%2BLogopng.png"));
        currentItem.add(new RowData( "http://www.globalteacherprize.org/wp-content/uploads/2015/03/People-inspire-people.jpg"));
        currentItem.add(new RowData("http://www.businessblogshub.com/wp-content/uploads/2012/09/quotes_to_inspire_17.jpg"));
        currentItem.add(new RowData("http://41.media.tumblr.com/tumblr_m8frnlOn0x1rpis3bo1_1280.jpg"));
        currentItem.add(new RowData("http://cdn-media-1.lifehack.org/wp-content/files/2013/08/20-Brilliant-Books-To-Influence-And-Inspire-You.jpg"));
        currentItem.add(new RowData("http://cdn-media-2.lifehack.org/wp-content/files/2013/09/Find-things-that-inspire-you-and-pursue-them-even-if-there-s-no-money-in-it..jpg"));
        currentItem.add(new RowData("http://bitsofwisdom.org/wp-content/uploads/2013/02/people-inspire-or-drain-you.jpg"));
        currentItem.add(new RowData( "http://1.bp.blogspot.com/-eDGdVtqykWw/VANVv_zQhpI/AAAAAAAAO7E/As21z388DpQ/s1600/No%2BNeed%2BTo%2BBe%2BPerfect%2BTo%2BInspire%2BOthers.jpg"));
        currentItem.add(new RowData("http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("http://blog.book-pal.com/wp-content/uploads/2015/06/MM3.png"));
        currentItem.add(new RowData( "https://s-media-cache-ak0.pinimg.com/736x/cc/06/41/cc06414572a156ebe150d3cdcde816e1.jpg"));
        currentItem.add(new RowData("http://ak-hdl.buzzfed.com/static/2015-03/6/18/enhanced/webdr04/enhanced-buzz-wide-14896-1425684830-23.jpg"));
        currentItem.add(new RowData("http://officeninjas.com/wp-content/uploads/2015/11/Nightingale-674x1024.jpg"));
        currentItem.add(new RowData( "http://www.shoutmeloud.com/wp-content/uploads/2015/11/Books-To-Inspire-Creativity.png"));
        currentItem.add(new RowData("https://s-media-cache-ak0.pinimg.com/236x/79/f4/a6/79f4a6f06c0e762d08c1c4aca8ce672f.jpg"));
        currentItem.add(new RowData("https://2982-presscdn-29-70-pagely.netdna-ssl.com/wp-content/uploads/2015/03/Manifesto2.jpg"));
        currentItem.add(new RowData( "https://s-media-cache-ak0.pinimg.com/736x/3a/13/ef/3a13efc6f355bda745ae65b030c1c855.jpg"));
        currentItem.add(new RowData("https://2982-presscdn-29-70-pagely.netdna-ssl.com/wp-content/uploads/2015/03/Manifesto4.jpg"));

        return currentItem;
    }
}
