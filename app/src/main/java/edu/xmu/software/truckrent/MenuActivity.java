package edu.xmu.software.truckrent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.baidu.mapapi.map.MapFragment;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

   // private ResideMenu resideMenu;
    private MenuActivity mContext;
    /*private ResideMenuItem itemMap;
    private ResideMenuItem itemGuide;
    private ResideMenuItem itemPostcard;
    private ResideMenuItem itemDownload;
    private ResideMenuItem itemMessage;
    private ResideMenuItem itemSetting;*/

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            ;
           // changeFragment(new MapFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
       /* resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemMap     = new ResideMenuItem(this, R.drawable.img_map_no,       "地图导航");
        itemGuide  = new ResideMenuItem(this, R.drawable.img_guide_no,      "校游社区");
        itemPostcard = new ResideMenuItem(this, R.drawable.img_postcard_no,"明信片DIY");
        itemDownload = new ResideMenuItem(this, R.drawable.img_download_no,"离线下载");
        itemMessage = new ResideMenuItem(this, R.drawable.img_message_no,  "消息通知");
        itemSetting = new ResideMenuItem(this,R.drawable.img_setting_no,   "设置");

        itemMap.setOnClickListener(this);
        itemGuide.setOnClickListener(this);
        itemPostcard.setOnClickListener(this);
        itemDownload.setOnClickListener(this);
        itemMessage.setOnClickListener(this);
        itemSetting.setOnClickListener(this);

        resideMenu.addMenuItem(itemMap, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemGuide, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemPostcard, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemDownload, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemMessage, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSetting,ResideMenu.DIRECTION_LEFT);

        //禁止向右侧滑
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);*/
        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        /*
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
            }
        });
        */
    }

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }*/

    @Override
    public void onClick(View view) {

        /*if (view == itemMap){
            changeFragment(new MapFragment());
        }else if (view == itemGuide){
            changeFragment(new Guide());
        }else if (view == itemPostcard){
            changeFragment(new Postcard());
        }else if (view == itemDownload){
            changeFragment(new DownloadFragment());
        }else if (view == itemMessage){
            changeFragment(new MessageFragment());
        }else if (view == itemSetting){
            changeFragment(new SettingsFragment());
        }

        resideMenu.closeMenu();*/
    }

    /*//menu打开和关闭的监听
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };*/

   /* private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }*/

   /* // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    @Override
    public void onBackPressed() {

        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

    }*/
}
