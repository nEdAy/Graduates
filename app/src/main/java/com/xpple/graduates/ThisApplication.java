package com.xpple.graduates;

import android.app.Application;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.xpple.graduates.util.SpSaveUtil;
import com.xpple.graduates.util.SpScoreUtil;
import com.xpple.graduates.util.SpSettingsUtil;

import java.util.HashMap;
import java.util.Map;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

/**
 * 自定义全局Application类
 *
 * @author nEdAy
 */
public class ThisApplication extends Application {

    private static final String PREFERENCE_NAME_0 = "_score";
    private static final String PREFERENCE_NAME_1 = "_settings";
    private static final String PREFERENCE_NAME_2 = "_save";
    private static final int[] musicId = {R.raw.bg_start, R.raw.bg_end, R.raw.bg_game};
    private static ThisApplication mInstance;
    private static SoundPool mSoundPool;
    private static MediaPlayer mMediaPlayer;
    //音乐播放位置
    private static int position;
    private static Map<Integer, Integer> soundMap;
    // 单例模式，才能及时返回数据
    private SpScoreUtil mSpScoreUtil;
    private SpSettingsUtil mSpSettingsUtil;
    private SpSaveUtil mSpSaveUtil;

    public static ThisApplication getInstance() {
        return mInstance;
    }

    public static void playSound(int sound) {
        if (isAllowSound()) {
            Integer soundId = soundMap.get(sound);
            if (soundId != null) {
                mSoundPool.play(soundId, 1, 1, 0, 0, 1);
            }
        }
    }

    /**
     * 获得音效开关状态
     */
    private static boolean isAllowSound() {
        return ThisApplication.getInstance().getSpSettingsUtil().isAllowSound();
    }

    /**
     * 设置音效开关
     */
    public static void setAllowSoundEnable(boolean enable) {
        ThisApplication.getInstance().getSpSettingsUtil().setAllowSoundEnable(enable);
    }

    /**
     * 初始化音乐播放器
     */
    private static void initMusic(Integer id) {
        // 创建MediaPlayer对象
        mMediaPlayer = MediaPlayer.create(mInstance, musicId[id]);
        // 设置为循环播放
        mMediaPlayer.setLooping(true);
    }

    /**
     * 暂停音乐
     */
    public static void pauseMusic() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            position = mMediaPlayer.getCurrentPosition();
            mMediaPlayer.stop();
        }
    }

    /**
     * 播放音乐
     */
    public static void startMusic(Integer id) {
        if (isAllowMusic()) {
            initMusic(id);
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
            }
        }
    }

    /**
     * 摧毁音乐
     */
    public static void destroyMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    /**
     * 继续音乐
     */
    public static void resumeMusic(Integer id) {
        if (position > 0 && mMediaPlayer != null) {
            mMediaPlayer.reset();
            initMusic(id);
            mMediaPlayer.start();
            mMediaPlayer.seekTo(position);
            position = 0;
        }
    }

    /**
     * 获得音乐开关状态
     */
    private static boolean isAllowMusic() {
        return ThisApplication.getInstance().getSpSettingsUtil().isAllowMusic();
    }

    /**
     * 设置音乐开关
     */
    public static void setAllowMusicEnable(boolean enable) {
        ThisApplication.getInstance().getSpSettingsUtil().setAllowMusicEnable(enable);
        if (enable) {
            if (position > 0) {
                resumeMusic(0);
            } else {
                startMusic(0);
            }
        } else {
            pauseMusic();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initTextType();
        initSound();
    }

    private void initTextType() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/MyFont.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    private void initSound() {
        // 创建一个声音播放池
        mSoundPool = new SoundPool.Builder().setMaxStreams(1).build();
        // 读取音效
        soundMap = new HashMap<>();
        soundMap.put(R.raw.button_0, mSoundPool.load(this, R.raw.button_0, 0));
        soundMap.put(R.raw.button_1, mSoundPool.load(this, R.raw.button_1, 0));
        soundMap.put(R.raw.read, mSoundPool.load(this, R.raw.read, 0));
        soundMap.put(R.raw.train, mSoundPool.load(this, R.raw.train, 0));
        soundMap.put(R.raw.parents, mSoundPool.load(this, R.raw.parents, 0));
        soundMap.put(R.raw.internet, mSoundPool.load(this, R.raw.internet, 0));
        soundMap.put(R.raw.classmate, mSoundPool.load(this, R.raw.classmate, 0));
        soundMap.put(R.raw.exercise, mSoundPool.load(this, R.raw.exercise, 0));
        soundMap.put(R.raw.bar, mSoundPool.load(this, R.raw.bar, 0));
        soundMap.put(R.raw.money, mSoundPool.load(this, R.raw.money, 0));
        soundMap.put(R.raw.hammer, mSoundPool.load(this, R.raw.hammer, 0));
        soundMap.put(R.raw.shopping, mSoundPool.load(this, R.raw.shopping, 0));
        soundMap.put(R.raw.tour, mSoundPool.load(this, R.raw.tour, 0));
        soundMap.put(R.raw.sleep, mSoundPool.load(this, R.raw.sleep, 0));
        soundMap.put(R.raw.lottery, mSoundPool.load(this, R.raw.lottery, 0));
        soundMap.put(R.raw.appreciation, mSoundPool.load(this, R.raw.appreciation, 0));
        soundMap.put(R.raw.next_month, mSoundPool.load(this, R.raw.next_month, 0));
    }

    public synchronized SpScoreUtil getSpScoreUtil() {
        if (mSpScoreUtil == null) {
            mSpScoreUtil = new SpScoreUtil(this, PREFERENCE_NAME_0);
        }
        return mSpScoreUtil;
    }

    public synchronized SpSettingsUtil getSpSettingsUtil() {
        if (mSpSettingsUtil == null) {
            mSpSettingsUtil = new SpSettingsUtil(this, PREFERENCE_NAME_1);
        }
        return mSpSettingsUtil;
    }

    public synchronized SpSaveUtil getSpSaveUtil() {
        if (mSpSaveUtil == null) {
            mSpSaveUtil = new SpSaveUtil(this, PREFERENCE_NAME_2);
        }
        return mSpSaveUtil;
    }
}
