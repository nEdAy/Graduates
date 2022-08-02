package com.xpple.graduates.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import com.xpple.graduates.R;
import com.xpple.graduates.ThisApplication;
import com.xpple.graduates.ui.mainFragment.MainFragment;
import com.xpple.graduates.view.BaseActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SpSettingsUtil mSharedSettingsUtil = mApplication.getSpSettingsUtil();
//        boolean isUserSuggest = mSharedSettingsUtil.isUserSuggest();
//        if (!isUserSuggest) {
//            //预加载插屏广告数据
//            SpotManager.getInstance(this).loadSpotAds();
//        }
        setUpFragment(savedInstanceState);
    }

    private void setUpFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            MainFragment fragment = new MainFragment();
            transaction.replace(R.id.fragment_main, fragment);
            transaction.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ThisApplication.destroyMusic();
        ThisApplication.startMusic(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ThisApplication.resumeMusic(0);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ThisApplication.pauseMusic();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        SpotManager.getInstance(this).onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        SpotManager.getInstance(this).onDestroy();
//    }

//    @Override
//    public void onBackPressed() {
//        // 如果有需要，可以点击后退关闭插播广告。
//        if (!SpotManager.getInstance(this).disMiss()) {
//            // 弹出退出窗口，可以使用自定义退屏弹出和回退动画,参照demo,若不使用动画，传入-1
//            super.onBackPressed();
//        }
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}

