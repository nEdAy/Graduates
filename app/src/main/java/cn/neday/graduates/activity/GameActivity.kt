package cn.neday.graduates.activity

import android.os.Bundle
import androidx.fragment.app.commit
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.databinding.ActivityGameBinding
import cn.neday.graduates.repository.Settings
import cn.neday.graduates.ui.gameFragment.GameFragment
import cn.neday.graduates.ui.gameFragment.StartFragmentA

class GameActivity : BaseBindingActivity<ActivityGameBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_game, if (Settings.isPlaying) {
                    GameFragment()
                } else {
                    StartFragmentA()
                }
            )
        }
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