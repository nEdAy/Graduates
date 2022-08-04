package cn.neday.graduates.activity

import android.os.Bundle
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
        setUpFragment(savedInstanceState)
    }

    private fun setUpFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            when (Score.partnerStory) {
                0 -> { //小樱
                    val fragment = XiaoyingFragment()
                    transaction.replace(R.id.fragment_story, fragment)
                }
                1, 2, 3 -> { //昭君
                    val fragment = ZhaojunFragment()
                    transaction.replace(R.id.fragment_story, fragment)
                }
                4, 5, 6, 7, 8, 9, 10 -> { //十娘
                    val fragment = ShiniangFragment()
                    transaction.replace(R.id.fragment_story, fragment)
                }
            }
            transaction.commit()
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