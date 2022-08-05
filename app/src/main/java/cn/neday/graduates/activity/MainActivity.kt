package cn.neday.graduates.activity

import android.os.Bundle
import androidx.fragment.app.commit
import cn.neday.graduates.MusicConductor.destroyMusic
import cn.neday.graduates.MusicConductor.pauseMusic
import cn.neday.graduates.MusicConductor.resumeMusic
import cn.neday.graduates.MusicConductor.startMusic
import cn.neday.graduates.R
import cn.neday.graduates.databinding.ActivityMainBinding
import cn.neday.graduates.ui.mainFragment.MainFragment

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_main, MainFragment())
        }
    }

    override fun onStart() {
        super.onStart()
        destroyMusic()
        startMusic(0)
    }

    override fun onResume() {
        super.onResume()
        resumeMusic(0)
    }

    override fun onPause() {
        super.onPause()
        pauseMusic()
    }
}