package cn.neday.graduates.ui.storyline

import android.os.Bundle
import android.view.View
import cn.neday.graduates.R
import cn.neday.graduates.activity.GameActivity
import cn.neday.graduates.databinding.FragmentStoryBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import cn.neday.graduates.repository.Score.partnerStory
import com.dylanc.longan.doOnClick
import com.dylanc.longan.startActivity
import com.dylanc.longan.toast

class ZhaojunFragment : BaseBindingFragment<FragmentStoryBinding>() {
    private var mStroy: Int = 0
    private var mStroyMax: Int = 0
    private var mPartnerStory: Int = 0

    private fun setUpViews() {
        setListener()
    }

    private fun setListener() {
        binding.tvTalk.doOnClick { onTalk() }
        binding.rbYes.doOnClick {
            binding.rgStory.visibility = View.GONE
            binding.tvTalk.isClickable = true
            mStroy = 35
            mStroyMax = 44
        }
        binding.rbNo.doOnClick {
            binding.rgStory.visibility = View.GONE
            binding.tvTalk.isClickable = true
            mStroy = 45
            mStroyMax = 74
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        mPartnerStory = partnerStory
        when (mPartnerStory) {
            1 -> {
                //第一幕
                mStroyMax = 16
                binding.tvTalk.setText(zj_talk_0[0])
                binding.ivStory.setBackgroundResource(R.mipmap.zj00)
            }
            2 -> {
                //第二幕
                mStroyMax = 39
                binding.tvTalk.setText(zj_talk_1[0])
                binding.ivStory.setBackgroundResource(R.mipmap.zj01)
            }
            3 -> {
                //第三幕
                mStroyMax = 74
                binding.tvTalk.setText(zj_talk_2[0])
                binding.ivStory.setBackgroundResource(R.mipmap.zj05)
            }

        }
        mStroy = 0
    }

    private fun onTalk() {

        mStroy = mStroy.plus(1);
        when (mPartnerStory) {
            1 ->                         //第一幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(zj_talk_0[mStroy])
                } else {
                    Score.partnerZj = 1
                    startActivity<GameActivity>()
                    activity?.finish()
                }
            2 -> {
                //第二幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(zj_talk_1[mStroy])
                } else {
                    Score.partnerZj = 2
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    21 -> binding.ivStory.setBackgroundResource(R.mipmap.zj02)
                    29 -> binding.ivStory.setBackgroundResource(R.mipmap.zj03)
                    36 -> binding.ivStory.setBackgroundResource(R.mipmap.zj04)
                }
            }
            3 -> {
                //第三幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(zj_talk_2[mStroy])
                } else {
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    12 -> binding.ivStory.setBackgroundResource(R.mipmap.zj06)
                    20 -> binding.ivStory.setBackgroundResource(R.mipmap.zj07)
                    34 -> {
                        binding.tvTalk.isClickable = false
                        binding.rbYes.setText(zj_talk_2[35])
                        binding.rbNo.setText(zj_talk_2[45])
                        binding.rgStory.visibility = View.VISIBLE
                    }
                    36 -> binding.ivStory.setBackgroundResource(R.mipmap.zj12)
                    40 -> binding.ivStory.setBackgroundResource(R.mipmap.zj13)
                    44 -> {
                        toast("你和女友昭君的感情加深了，爱情机遇指数+2")
                        Score.love = Score.love.plus(2)
                    }
                    47 -> binding.ivStory.setBackgroundResource(R.mipmap.zj09)
                    51 -> binding.ivStory.setBackgroundResource(R.mipmap.zj10)
                    60 -> binding.ivStory.setBackgroundResource(R.mipmap.zj11)
                    74 -> {
                        toast("你和女友昭君分手了")
                        Score.partner = 0
                        // TODO:红娘会馆删除昭君信息
                        Score.partnerZj = 3
                    }
                }
            }
        }
    }

    companion object {
        private val zj_talk_0 = intArrayOf(
            R.string.zhaojun_0_00, R.string.zhaojun_0_01, R.string.zhaojun_0_02,
            R.string.zhaojun_0_03, R.string.zhaojun_0_04, R.string.zhaojun_0_05,
            R.string.zhaojun_0_06, R.string.zhaojun_0_07, R.string.zhaojun_0_08,
            R.string.zhaojun_0_09, R.string.zhaojun_0_10, R.string.zhaojun_0_11,
            R.string.zhaojun_0_12, R.string.zhaojun_0_13, R.string.zhaojun_0_14,
            R.string.zhaojun_0_15, R.string.zhaojun_0_16
        )
        private val zj_talk_1 = intArrayOf(
            R.string.zhaojun_1_00, R.string.zhaojun_1_01, R.string.zhaojun_1_02,
            R.string.zhaojun_1_03, R.string.zhaojun_1_04, R.string.zhaojun_1_05,
            R.string.zhaojun_1_06, R.string.zhaojun_1_07, R.string.zhaojun_1_08,
            R.string.zhaojun_1_09, R.string.zhaojun_1_10, R.string.zhaojun_1_11,
            R.string.zhaojun_1_12, R.string.zhaojun_1_13, R.string.zhaojun_1_14,
            R.string.zhaojun_1_15, R.string.zhaojun_1_16, R.string.zhaojun_1_17,
            R.string.zhaojun_1_18, R.string.zhaojun_1_19, R.string.zhaojun_1_20,
            R.string.zhaojun_1_21, R.string.zhaojun_1_22, R.string.zhaojun_1_23,
            R.string.zhaojun_1_24, R.string.zhaojun_1_25, R.string.zhaojun_1_26,
            R.string.zhaojun_1_27, R.string.zhaojun_1_28, R.string.zhaojun_1_29,
            R.string.zhaojun_1_30, R.string.zhaojun_1_31, R.string.zhaojun_1_32,
            R.string.zhaojun_1_33, R.string.zhaojun_1_34, R.string.zhaojun_1_35,
            R.string.zhaojun_1_36, R.string.zhaojun_1_37, R.string.zhaojun_1_38,
            R.string.zhaojun_1_39
        )
        private val zj_talk_2 = intArrayOf(
            R.string.zhaojun_2_00, R.string.zhaojun_2_01, R.string.zhaojun_2_02,
            R.string.zhaojun_2_03, R.string.zhaojun_2_04, R.string.zhaojun_2_05,
            R.string.zhaojun_2_06, R.string.zhaojun_2_07, R.string.zhaojun_2_08,
            R.string.zhaojun_2_09, R.string.zhaojun_2_10, R.string.zhaojun_2_11,
            R.string.zhaojun_2_12, R.string.zhaojun_2_13, R.string.zhaojun_2_14,
            R.string.zhaojun_2_15, R.string.zhaojun_2_16, R.string.zhaojun_2_17,
            R.string.zhaojun_2_18, R.string.zhaojun_2_19, R.string.zhaojun_2_20,
            R.string.zhaojun_2_21, R.string.zhaojun_2_22, R.string.zhaojun_2_23,
            R.string.zhaojun_2_24, R.string.zhaojun_2_25, R.string.zhaojun_2_26,
            R.string.zhaojun_2_27, R.string.zhaojun_2_28, R.string.zhaojun_2_29,
            R.string.zhaojun_2_30, R.string.zhaojun_2_31, R.string.zhaojun_2_32,
            R.string.zhaojun_2_33, R.string.zhaojun_2_34, R.string.zhaojun_2_35,
            R.string.zhaojun_2_36, R.string.zhaojun_2_37, R.string.zhaojun_2_38,
            R.string.zhaojun_2_39, R.string.zhaojun_2_40, R.string.zhaojun_2_41,
            R.string.zhaojun_2_42, R.string.zhaojun_2_43, R.string.zhaojun_2_44,
            R.string.zhaojun_2_45, R.string.zhaojun_2_46, R.string.zhaojun_2_47,
            R.string.zhaojun_2_48, R.string.zhaojun_2_49, R.string.zhaojun_2_50,
            R.string.zhaojun_2_51, R.string.zhaojun_2_52, R.string.zhaojun_2_53,
            R.string.zhaojun_2_54, R.string.zhaojun_2_55, R.string.zhaojun_2_56,
            R.string.zhaojun_2_57, R.string.zhaojun_2_58, R.string.zhaojun_2_59,
            R.string.zhaojun_2_60, R.string.zhaojun_2_61, R.string.zhaojun_2_62,
            R.string.zhaojun_2_63, R.string.zhaojun_2_64, R.string.zhaojun_2_65,
            R.string.zhaojun_2_66, R.string.zhaojun_2_67, R.string.zhaojun_2_68,
            R.string.zhaojun_2_69, R.string.zhaojun_2_70, R.string.zhaojun_2_71,
            R.string.zhaojun_2_72, R.string.zhaojun_2_73, R.string.zhaojun_2_74
        )
    }
}