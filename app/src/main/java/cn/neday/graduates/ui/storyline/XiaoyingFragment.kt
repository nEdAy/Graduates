package cn.neday.graduates.ui.storyline

import android.os.Bundle
import android.view.View
import cn.neday.graduates.R
import cn.neday.graduates.activity.GameActivity
import cn.neday.graduates.databinding.FragmentStoryBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import com.dylanc.longan.doOnClick
import com.dylanc.longan.startActivity

class XiaoyingFragment : BaseBindingFragment<FragmentStoryBinding>() {
    private var mStroy: Int = 0
    private var mStroyMax: Int = 0

    private fun setUpViews() {
        setListener()
    }

    private fun setListener() {
        binding.tvTalk.doOnClick { onTalk() }
        binding.rbYes.doOnClick {
            binding.rgStory.visibility = View.GONE
            binding.tvTalk.isClickable = true
            mStroy = 63
            mStroyMax = 66
            Score.partnerXy = 91
        }
        binding.rbNo.doOnClick {
            binding.rgStory.visibility = View.GONE
            binding.tvTalk.isClickable = true
            mStroy = 67
            mStroyMax = 72
            Score.partnerXy = 93
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        mStroy = 0
        mStroyMax = 100
        binding.tvTalk.setText(xy_talk_0[0])
        binding.ivStory.setBackgroundResource(R.mipmap.xy00)
    }

    private fun onTalk() {
        mStroy++
        if (mStroy <= mStroyMax) {
            binding.tvTalk.setText(xy_talk_0[mStroy])
        } else {
            startActivity<GameActivity>()
            activity?.finish()
        }
        when (mStroy) {
            15 -> binding.ivStory.setBackgroundResource(R.mipmap.xy01)
            32 -> binding.ivStory.setBackgroundResource(R.mipmap.xy02)
            55 -> binding.ivStory.setBackgroundResource(R.mipmap.xy03)
            60 -> binding.ivStory.setBackgroundResource(R.mipmap.xy04)
            62 -> {
                binding.tvTalk.isClickable = false
                binding.rbYes.setText(xy_talk_0[63])
                binding.rbNo.setText(xy_talk_0[64])
                binding.rgStory.visibility = View.VISIBLE
            }
            64 -> binding.ivStory.setBackgroundResource(R.mipmap.xy05)
            65 -> binding.ivStory.setBackgroundResource(R.mipmap.xy06)
            72 -> binding.ivStory.setBackgroundResource(R.mipmap.xy07)
        }
    }

    companion object {
        private val xy_talk_0 = intArrayOf(
            R.string.xiaoying_0_00, R.string.xiaoying_0_01, R.string.xiaoying_0_02,
            R.string.xiaoying_0_03, R.string.xiaoying_0_04, R.string.xiaoying_0_05,
            R.string.xiaoying_0_06, R.string.xiaoying_0_07, R.string.xiaoying_0_08,
            R.string.xiaoying_0_09, R.string.xiaoying_0_10, R.string.xiaoying_0_11,
            R.string.xiaoying_0_12, R.string.xiaoying_0_13, R.string.xiaoying_0_14,
            R.string.xiaoying_0_15, R.string.xiaoying_0_16, R.string.xiaoying_0_17,
            R.string.xiaoying_0_18, R.string.xiaoying_0_19, R.string.xiaoying_0_20,
            R.string.xiaoying_0_21, R.string.xiaoying_0_22, R.string.xiaoying_0_23,
            R.string.xiaoying_0_24, R.string.xiaoying_0_25, R.string.xiaoying_0_26,
            R.string.xiaoying_0_27, R.string.xiaoying_0_28, R.string.xiaoying_0_29,
            R.string.xiaoying_0_30, R.string.xiaoying_0_31, R.string.xiaoying_0_32,
            R.string.xiaoying_0_33, R.string.xiaoying_0_34, R.string.xiaoying_0_35,
            R.string.xiaoying_0_36, R.string.xiaoying_0_37, R.string.xiaoying_0_38,
            R.string.xiaoying_0_39, R.string.xiaoying_0_40, R.string.xiaoying_0_41,
            R.string.xiaoying_0_42, R.string.xiaoying_0_43, R.string.xiaoying_0_44,
            R.string.xiaoying_0_45, R.string.xiaoying_0_46, R.string.xiaoying_0_47,
            R.string.xiaoying_0_48, R.string.xiaoying_0_49, R.string.xiaoying_0_50,
            R.string.xiaoying_0_51, R.string.xiaoying_0_52, R.string.xiaoying_0_53,
            R.string.xiaoying_0_54, R.string.xiaoying_0_55, R.string.xiaoying_0_56,
            R.string.xiaoying_0_57, R.string.xiaoying_0_58, R.string.xiaoying_0_59,
            R.string.xiaoying_0_60, R.string.xiaoying_0_61, R.string.xiaoying_0_62,
            R.string.xiaoying_0_63, R.string.xiaoying_0_64, R.string.xiaoying_0_65,
            R.string.xiaoying_0_66, R.string.xiaoying_0_67, R.string.xiaoying_0_68,
            R.string.xiaoying_0_69, R.string.xiaoying_0_70, R.string.xiaoying_0_71,
            R.string.xiaoying_0_72
        )
    }
}