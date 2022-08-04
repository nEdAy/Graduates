package cn.neday.graduates.activity

import android.os.Bundle
import cn.neday.graduates.MusicConductor.destroyMusic
import cn.neday.graduates.MusicConductor.pauseMusic
import cn.neday.graduates.MusicConductor.resumeMusic
import cn.neday.graduates.MusicConductor.startMusic
import cn.neday.graduates.databinding.ActivityOverBinding

class OverActivity : BaseBindingActivity<ActivityOverBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        destroyMusic()
        startMusic(1)
    }

    override fun onResume() {
        super.onResume()
        resumeMusic(1)
    }

    override fun onPause() {
        super.onPause()
        pauseMusic()
    }

    override fun onBackPressed() {
        // 屏蔽Back
    }
}