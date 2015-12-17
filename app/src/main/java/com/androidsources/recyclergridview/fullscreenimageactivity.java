package com.androidsources.recyclergridview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static final String MY_PREFS_NAME = "MyPrefsFile";
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

    boolean buttonVisible = true;
    private boolean mVisible;
    private ViewPager mViewPager;
    private CustomPagerAdapter mCustomPagerAdapter;
    int position;
    List<RowData> list;
    private String currentURL;
    private SharedPreferences sPrefs;
    Button share,like,dislike;
    ImageView imageview;
    int currentPagePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreenimageactivity);

        //recieve list of data
        //recieve clicked position
        Bundle bundle = getIntent().getExtras();
        currentURL = bundle.getString("currentitem");
        Log.d("FullScreen","recieved"+currentURL);
        list = RecyclerViewAdapter.itemList;
        if(list!=null) {
           // Toast.makeText(this, "Recieved: " + list.size(),Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this, "nothing ", Toast.LENGTH_SHORT).show();
        }
        mVisible = true;
        mCustomPagerAdapter = new CustomPagerAdapter(this,list);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        //get current view
        int position = 0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getWonderImageURL().equals(currentURL)){
                position = i;
                break;
            }
        }
        Log.d("Fullscreen", "Position " + position);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setCurrentItem(position);
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


        if(share == null){
            Log.d("InspireMe","Share button is null");
        }
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int arg0) {
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageSelected(int currentPage) {
                //currentPage is the position that is currently displayed.
                currentPagePosition = currentPage;
            }

        });
        /*
        share.setOnClickListener(this);
        like.setOnClickListener(this);
        dislike.setOnClickListener(this);
*/
    }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    public void onclickImage(View V){
        Log.d("fullscrreen","image clicked");
        mCustomPagerAdapter.share.setEnabled(false);
        mCustomPagerAdapter.like.setEnabled(false);
        mCustomPagerAdapter.dislike.setEnabled(false);

    }
    public void onClickLike(View V){
        String likeMsg[] = {"Awesome","Cool","Writer is happy","I like it too!","Thats awesome","Keep going on","You will achieve it!"};
        int random = mCustomPagerAdapter.randomWithRange(0, likeMsg.length - 1);
        Toast.makeText(this, likeMsg[random], Toast.LENGTH_SHORT).show();
    }
    public void onClickShare(View v) {
        try {
            //startDefaultAppOrPromptUserForSelection();
            String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/InspireMe";
            File dir = new File(file_path);
            if(!dir.exists())
                dir.mkdirs();

            String format = new SimpleDateFormat("yyyyMMddHHmmss",
                    java.util.Locale.getDefault()).format(new Date());

            File file = new File(dir, format + ".png");
            FileOutputStream fOut;
            try {
                fOut = new FileOutputStream(file);
                list.get(currentPagePosition).getDownloadedImage().compress(Bitmap.CompressFormat.PNG, 85, fOut);
                fOut.flush();
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            sendToWhatsApp(file);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void onClickUnlike(View v) {

        String dislikeMsg[] ={"Always be positive !!","It not good? OK","Whoa, I thought its good","Will keep better feed","Never mind","Writer won't get hurt"};
        int random = mCustomPagerAdapter.randomWithRange(0,dislikeMsg.length-1);
        Toast.makeText(this,dislikeMsg[random],Toast.LENGTH_SHORT).show();

    }
    void sendToWhatsApp(File pictureFile){
        Uri imageUri = Uri.parse(pictureFile.getAbsolutePath());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //Target whatsapp:
        shareIntent.setPackage("com.whatsapp");
        //Add text and then Image URI
        shareIntent.putExtra(Intent.EXTRA_TEXT, "InspireMe for everyday! Get it on PLAY STORE: https://goo.gl/Dp5PBs ");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/jpeg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,"Whatsapp have not been installed.",Toast.LENGTH_SHORT).show();
        }
    }
    public void startDefaultAppOrPromptUserForSelection() {
        String action = Intent.ACTION_SEND;
        sPrefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        // Get list of handler apps that can send
        Intent intent = new Intent(action);
        intent.setType("image/jpeg");
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resInfos = pm.queryIntentActivities(intent, 0);

        boolean useDefaultSendApplication = false;
        if (!useDefaultSendApplication) {
            // Referenced http://stackoverflow.com/questions/3920640/how-to-add-icon-in-alert-dialog-before-each-item

            // Class for a singular activity item on the list of apps to send to
            class ListItem {
                public final String name;
                public final Drawable icon;
                public final String context;
                public final String packageClassName;
                public ListItem(String text, Drawable icon, String context, String packageClassName) {
                    this.name = text;
                    this.icon = icon;
                    this.context = context;
                    this.packageClassName = packageClassName;
                }
                @Override
                public String toString() {
                    return name;
                }
            }

            // Form those activities into an array for the list adapter
            final ListItem[] items = new ListItem[resInfos.size()];
            int i = 0;
            for (ResolveInfo resInfo : resInfos) {
                String context = resInfo.activityInfo.packageName;
                String packageClassName = resInfo.activityInfo.name;
                CharSequence label = resInfo.loadLabel(pm);
                Drawable icon = resInfo.loadIcon(pm);
                items[i] = new ListItem(label.toString(), icon, context, packageClassName);

            }
            ListAdapter adapter = new ArrayAdapter<ListItem>(
                    this,
                    android.R.layout.select_dialog_item,
                    android.R.id.text1,
                    items){

                public View getView(int position, View convertView, ViewGroup parent) {
                    // User super class to create the View
                    View v = super.getView(position, convertView, parent);
                    TextView tv = (TextView)v.findViewById(android.R.id.text1);

                    // Put the icon drawable on the TextView (support various screen densities)
                    int dpS = (int) (32 * getResources().getDisplayMetrics().density  + 0.5f);
                    items[position].icon.setBounds(0, 0, dpS, dpS);
                    tv.setCompoundDrawables(items[position].icon, null, null, null);

                    // Add margin between image and name (support various screen densities)
                    int dp5 = (int) (5 * getResources().getDisplayMetrics().density  + 0.5f);
                    tv.setCompoundDrawablePadding(dp5);

                    return v;
                }
            };

            // Build the list of send applications
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose your app:");
            builder.setIcon(R.mipmap.icon);
            CheckBox checkbox = new CheckBox(getApplicationContext());
            checkbox.setText(getString(R.string.enable_default_send_application));
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                // Save user preference of whether to use default send application
                @Override
                public void onCheckedChanged(CompoundButton paramCompoundButton,
                                             boolean paramBoolean) {
                    SharedPreferences.Editor editor = sPrefs.edit();
                    editor.putBoolean("useDefaultSendApplication", paramBoolean);
                    editor.commit();
                }
            });
            builder.setView(checkbox);
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface paramDialogInterface) {
                    // do something
                }
            });

            // Set the adapter of items in the list
            builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor editor = sPrefs.edit();
                    editor.putString("defaultSendApplicationName", items[which].name);
                    editor.putString("defaultSendApplicationPackageContext", items[which].context);
                    editor.putString("defaultSendApplicationPackageClassName", items[which].packageClassName);
                    editor.commit();

                    dialog.dismiss();

                    // Start the selected activity sending it the URLs of the resized images
                    Intent intent;
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/jpeg");
                    intent.setClassName(items[which].context, items[which].packageClassName);
                    startActivity(intent);
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


        } else { // Start the default send application

            // Get default app name saved in preferences
            String defaultSendApplicationName = sPrefs.getString("defaultSendApplicationName", "<null>");
            String defaultSendApplicationPackageContext = sPrefs.getString("defaultSendApplicationPackageContext", "<null>");
            String defaultSendApplicationPackageClassName = sPrefs.getString("defaultSendApplicationPackageClassName", "<null>");
            if (defaultSendApplicationPackageContext == "<null>" || defaultSendApplicationPackageClassName == "<null>") {
                Toast.makeText(getApplicationContext(), "Can't find app: "  +defaultSendApplicationName+
                        " ("   +defaultSendApplicationPackageClassName  + ")", Toast.LENGTH_LONG).show();

                // don't have default application details in prefs file so set use default app to null and rerun this method
                SharedPreferences.Editor editor = sPrefs.edit();
                editor.putBoolean("useDefaultSendApplication", false);
                editor.commit();
                startDefaultAppOrPromptUserForSelection();
                return;
            }

            // Check app is still installed
            try {
                ApplicationInfo info = getPackageManager().getApplicationInfo(defaultSendApplicationPackageContext, 0);
            } catch (PackageManager.NameNotFoundException e){
                Toast.makeText(getApplicationContext(),  "Can't find app: "   +defaultSendApplicationName +
                        " (" +  defaultSendApplicationPackageClassName   + ")", Toast.LENGTH_LONG).show();

                // don't have default application installed so set use default app to null and rerun this method
                SharedPreferences.Editor editor = sPrefs.edit();
                editor.putBoolean("useDefaultSendApplication", false);
                editor.commit();
                startDefaultAppOrPromptUserForSelection();
                return;
            }

            // Start the selected activity
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jpeg");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setClassName(defaultSendApplicationPackageContext, defaultSendApplicationPackageClassName);
            startActivity(intent);
            finish();
            return;
        }
    }

    /*if(v == share){
        startDefaultAppOrPromptUserForSelection();
    }else if(v == like){
        String likeMsg[] = {"Awesome","Cool","Writer is happy","I like it too!","Thats awesome","Keep going on","You will achieve it!"};
        int random = mCustomPagerAdapter.randomWithRange(0, likeMsg.length - 1);
        Toast.makeText(this, random, Toast.LENGTH_SHORT).show();
    }else if(v == dislike){
        String dislikeMsg[] ={"Always be positive !!","It not good? OK","Whoa, I thought its good","Will keep better feed","Never mind","Writer won't get hurt"};
        int random = mCustomPagerAdapter.randomWithRange(0, dislikeMsg.length - 1);
        Toast.makeText(this,dislikeMsg[random],Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(this,"Something is clicked",Toast.LENGTH_SHORT).show();
        Log.d("PageAdapter","disabling");
        v.setEnabled(false);
    }*/


}
