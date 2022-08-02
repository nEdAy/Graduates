package com.xpple.graduates.ui;

import android.content.Context;
import android.os.Bundle;

import com.xpple.graduates.R;
import com.xpple.graduates.ThisApplication;
import com.xpple.graduates.view.BaseActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class OverActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ThisApplication.destroyMusic();
        ThisApplication.startMusic(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ThisApplication.resumeMusic(1);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ThisApplication.pauseMusic();
    }

    @Override
    public void onBackPressed() {
        // 屏蔽Back
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}

