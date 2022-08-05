package cn.neday.graduates.activity

import android.os.Bundle
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.databinding.ActivityGameBinding
import cn.neday.graduates.repository.Settings
import cn.neday.graduates.ui.gameFragment.GameFragment
import cn.neday.graduates.ui.gameFragment.StartFragmentA

class GameActivity : BaseBindingActivity<ActivityGameBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpFragment()
    }

    private fun setUpFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        if (Settings.isPlaying) {
            val fragment = GameFragment()
            transaction.replace(R.id.fragment_game, fragment)
        } else {
            val fragment = StartFragmentA()
            transaction.replace(R.id.fragment_game, fragment)
        }
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()
        MusicConductor.destroyMusic()
        MusicConductor.startMusic(1)
    }

    override fun onResume() {
        super.onResume()
        MusicConductor.resumeMusic(1)
    }

    override fun onPause() {
        super.onPause()
        MusicConductor.pauseMusic()
    }
}