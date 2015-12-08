package com.androidsources.recyclergridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting up imageloader library which will do fetch of images
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
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
        currentItem.add(new RowData("Taj Mahal",null));
        currentItem.add(new RowData("Brazil Christ", "http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Great Wall","http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Taj Mahal",null));
        currentItem.add(new RowData("Brazil Christ", "http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Great Wall","http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Taj Mahal",null));
        currentItem.add(new RowData("Brazil Christ", "http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Great Wall","http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Taj Mahal",null));
        currentItem.add(new RowData("Brazil Christ", "http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Great Wall","http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Taj Mahal",null));
        currentItem.add(new RowData("Brazil Christ", "http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Great Wall","http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Taj Mahal",null));
        currentItem.add(new RowData("Brazil Christ", "http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));
        currentItem.add(new RowData("Great Wall","http://inspireeducationgroup.com/wp-content/uploads/2013/10/12867061-inspire-concept-with-other-related-words-written-with-chalk-on-a-blackboard.jpg"));

        return currentItem;
    }
}
