package cn.neday.graduates.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import cn.neday.graduates.R;
import cn.neday.graduates.ThisApplication;
import cn.neday.graduates.ui.gameFragment.GameFragment;
import cn.neday.graduates.ui.gameFragment.StartFragmentA;
import cn.neday.graduates.util.SpScoreUtil;
import cn.neday.graduates.view.BaseActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class GameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUpFragment(savedInstanceState);
    }

    private void setUpFragment(Bundle savedInstanceState) {
        SpScoreUtil mSharedScoreUtil = mApplication.getSpScoreUtil();
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (mSharedScoreUtil.isScore()) {
                GameFragment fragment = new GameFragment();
                transaction.replace(R.id.fragment_game, fragment);
            } else {
                StartFragmentA fragment = new StartFragmentA();
                transaction.replace(R.id.fragment_game, fragment);
            }
            transaction.commit();
        }
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
