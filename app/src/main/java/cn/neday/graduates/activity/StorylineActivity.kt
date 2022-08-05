package cn.neday.graduates.activity

import android.os.Bundle
import androidx.fragment.app.commit
import cn.neday.graduates.MusicConductor.destroyMusic
import cn.neday.graduates.MusicConductor.pauseMusic
import cn.neday.graduates.MusicConductor.resumeMusic
import cn.neday.graduates.MusicConductor.startMusic
import cn.neday.graduates.R
import cn.neday.graduates.databinding.ActivityStorylineBinding
import cn.neday.graduates.repository.Score
import cn.neday.graduates.ui.storyFragment.ShiniangFragment
import cn.neday.graduates.ui.storyFragment.XiaoyingFragment
import cn.neday.graduates.ui.storyFragment.ZhaojunFragment

class StorylineActivity : BaseBindingActivity<ActivityStorylineBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpFragment()
    }

    private fun setUpFragment() {
        supportFragmentManager.commit {
            replace(
                //TODO enum
                R.id.fragment_story, when (Score.partnerStory) {
                    0 -> XiaoyingFragment()
                    1, 2, 3 -> ZhaojunFragment()
                    4, 5, 6, 7, 8, 9, 10 -> ShiniangFragment()
                    else -> ShiniangFragment()
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        destroyMusic()
        startMusic(2)
    }

    override fun onResume() {
        super.onResume()
        resumeMusic(2)
    }

    override fun onPause() {
        super.onPause()
        pauseMusic()
    }

    override fun onBackPressed() {
        // 屏蔽Back
    }
}