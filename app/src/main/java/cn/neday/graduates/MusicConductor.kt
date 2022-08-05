package cn.neday.graduates

import android.content.Context
import android.media.MediaPlayer
import android.media.SoundPool
import android.view.View
import cn.neday.graduates.MusicConductor.playSound
import cn.neday.graduates.repository.Settings
import com.dylanc.longan.application

inline fun View.doOnClickWithSound(crossinline block: () -> Unit) =
    setOnClickListener {
        playSound(R.raw.button_0)
        block()
    }

object MusicConductor {
    private var mediaPlayer: MediaPlayer? = null
    private val musicId = intArrayOf(R.raw.bg_start, R.raw.bg_end, R.raw.bg_game)
    private lateinit var soundMap: MutableMap<Int, Int>
    private lateinit var soundPool: SoundPool

    // 音乐播放位置
    private var position = 0

    init {
        initSound(application)
    }

    private fun initSound(context: Context) {
        // 创建一个声音播放池
        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        // 读取音效
        soundMap = HashMap()
        soundMap[R.raw.button_0] = soundPool.load(context, R.raw.button_0, 0)
        soundMap[R.raw.button_1] = soundPool.load(context, R.raw.button_1, 0)
        soundMap[R.raw.read] = soundPool.load(context, R.raw.read, 0)
        soundMap[R.raw.train] = soundPool.load(context, R.raw.train, 0)
        soundMap[R.raw.parents] = soundPool.load(context, R.raw.parents, 0)
        soundMap[R.raw.internet] = soundPool.load(context, R.raw.internet, 0)
        soundMap[R.raw.classmate] = soundPool.load(context, R.raw.classmate, 0)
        soundMap[R.raw.exercise] = soundPool.load(context, R.raw.exercise, 0)
        soundMap[R.raw.bar] = soundPool.load(context, R.raw.bar, 0)
        soundMap[R.raw.money] = soundPool.load(context, R.raw.money, 0)
        soundMap[R.raw.hammer] = soundPool.load(context, R.raw.hammer, 0)
        soundMap[R.raw.shopping] = soundPool.load(context, R.raw.shopping, 0)
        soundMap[R.raw.tour] = soundPool.load(context, R.raw.tour, 0)
        soundMap[R.raw.sleep] = soundPool.load(context, R.raw.sleep, 0)
        soundMap[R.raw.lottery] = soundPool.load(context, R.raw.lottery, 0)
        soundMap[R.raw.appreciation] = soundPool.load(context, R.raw.appreciation, 0)
        soundMap[R.raw.next_month] = soundPool.load(context, R.raw.next_month, 0)
    }

    /**
     * 播放音乐
     */
    fun startMusic(id: Int) {
        if (isAllowMusic) {
            initMusic(id)
            mediaPlayer?.let {
                if (!it.isPlaying) {
                    mediaPlayer?.start()
                }
            }
        }
    }

    /**
     * 暂停音乐
     */
    fun pauseMusic() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                position = it.currentPosition
                it.stop()
            }
        }
    }

    /**
     * 继续音乐
     */
    fun resumeMusic(id: Int) {
        if (position > 0) {
            mediaPlayer?.reset()
            initMusic(id)
            mediaPlayer?.start()
            mediaPlayer?.seekTo(position)
            position = 0
        }
    }

    /**
     * 销毁音乐
     */
    fun destroyMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    /**
     * 初始化音乐播放器
     */
    private fun initMusic(id: Int) {
        // 创建MediaPlayer对象
        mediaPlayer = MediaPlayer.create(application, musicId[id])
        // 设置为循环播放
        mediaPlayer?.isLooping = true
    }

    /**
     * 获得音乐开关状态
     */
    private val isAllowMusic = Settings.isAllowMusicEnable

    /**
     * 设置音乐开关
     */
    fun setAllowMusicEnable(enable: Boolean) {
        Settings.isAllowMusicEnable = enable
        if (enable) {
            if (position > 0) {
                resumeMusic(0)
            } else {
                startMusic(0)
            }
        } else {
            pauseMusic()
        }
    }

    fun playSound(sound: Int) {
        if (isAllowSound) {
            val soundId = soundMap[sound]
            if (soundId != null) {
                soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }
    }

    /**
     * 获得音效开关状态
     */
    private val isAllowSound: Boolean = Settings.isAllowSoundEnable

    /**
     * 设置音效开关
     */
    fun setAllowSoundEnable(enable: Boolean) {
        Settings.isAllowSoundEnable = enable
    }
}