package cn.neday.graduates.activity

import android.os.Bundle
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
        setUpFragment(savedInstanceState)
    }

    private fun setUpFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = MainFragment()
            transaction.replace(R.id.fragment_main, fragment)
            transaction.commit()
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