package com.xpple.graduates.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xpple.graduates.R;
import com.xpple.graduates.view.BaseActivity;


public class SplashActivity extends BaseActivity {
    private static final int GO_GUIDE = 100;
    private static final int GO_MAIN = 200;
    //    private Boolean isUserSuggest;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_GUIDE:
                    startAnimActivity(MainActivity.class);
                    finish();
                    break;
                case GO_MAIN:
//                    if (!isUserSuggest) {
//                        goAdsAndMain();
//                    } else {
                    startAnimActivity(MainActivity.class);
                    finish();
//                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        SpSettingsUtil mSharedSettingsUtil = mApplication.getSpSettingsUtil();
        // Boolean user_first = mSharedSettingsUtil.isAllowFirst();
//        isUserSuggest = mSharedSettingsUtil.isUserSuggest();
//        // 初始化 有米广告 SDK
//        if (!isUserSuggest) {
//            AdManager.getInstance(this).init(Config.YOUMI_KEY, Config.YOUMI_SECRET, true, true);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //暂时屏蔽引导页
//        if (user_first) {
//            mHandler.sendEmptyMessageDelayed(GO_GUIDE, 1500);
//        } else {
        mHandler.sendEmptyMessageDelayed(GO_MAIN, 1500);
//        }
    }

//    private void goAdsAndMain() {
//        if (isNetConnected()) {
//            SpotManager.getInstance(this).showSplashSpotAds(this, MainActivity.class);
//        } else {
//        startAnimActivity(MainActivity.class);
//        finish();
//        }
//    }
}
