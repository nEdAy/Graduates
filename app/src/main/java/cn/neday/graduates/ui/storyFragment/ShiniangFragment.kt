package cn.neday.graduates.ui.storyFragment

import android.os.Bundle
import android.view.View
import cn.neday.graduates.R
import cn.neday.graduates.activity.GameActivity
import cn.neday.graduates.databinding.FragmentStoryBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import com.dylanc.longan.doOnClick
import com.dylanc.longan.startActivity
import com.dylanc.longan.toast

class ShiniangFragment : BaseBindingFragment<FragmentStoryBinding>() {
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
            mStroy = 14
            //TODO：yes
            Score.partnerSn = 5
        }
        binding.rbNo.doOnClick {
            binding.rgStory.visibility = View.GONE
            binding.tvTalk.isClickable = true
            mStroy = 14
            //TODO：no
            Score.partnerSn = 6
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        mPartnerStory = Score.partnerStory
        when (mPartnerStory) {
            4 -> {
                //第一幕
                mStroyMax = 20
                binding.tvTalk.setText(sn_talk_0[0])
                binding.ivStory.setBackgroundResource(R.mipmap.sn00)
            }
            5 -> {
                //第二幕
                mStroyMax = 18
                binding.tvTalk.setText(sn_talk_1[0])
                binding.ivStory.setBackgroundResource(R.mipmap.sn02)
            }
            6 -> {
                //第三幕
                mStroyMax = 28
                binding.tvTalk.setText(sn_talk_2[0])
                binding.ivStory.setBackgroundResource(R.mipmap.sn04)
            }
            7 -> {
                //第四幕
                mStroyMax = 17
                binding.tvTalk.setText(sn_talk_3[0])
                binding.ivStory.setBackgroundResource(R.mipmap.sn06)
            }
            8 -> {
                //第五幕
                mStroyMax = 14
                binding.tvTalk.setText(sn_talk_4[0])
                binding.ivStory.setBackgroundResource(R.mipmap.sn09)
            }
            9 -> {
                //第六幕--A
                mStroyMax = 30
                binding.tvTalk.setText(sn_talk_5_a[0])
                binding.ivStory.setBackgroundResource(R.mipmap.sn12)
            }
            10 -> {
                //第六幕--B
                mStroyMax = 48
                binding.tvTalk.setText(sn_talk_5_b[0])
                binding.ivStory.setBackgroundResource(R.mipmap.sn14)
            }

        }
        mStroy = 0
    }

    fun onTalk() {
        mStroy++
        when (mPartnerStory) {
            4 -> {
                //第一幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(sn_talk_0[mStroy])
                } else {
                    Score.partnerSn = 1
                    Score.partnerSnTime = 0
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    6 -> binding.ivStory.setBackgroundResource(R.mipmap.sn01)

                }
            }
            5 -> {
                //第二幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(sn_talk_1[mStroy])
                } else {
                    Score.partnerSn = 2
                    Score.partnerSnTime = 0
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    5 -> binding.ivStory.setBackgroundResource(R.mipmap.sn03)

                }
            }
            6 -> {
                //第三幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(sn_talk_2[mStroy])
                } else {
                    Score.partnerSn = 3
                    Score.partnerSnTime = 0
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    4 -> binding.ivStory.setBackgroundResource(R.mipmap.sn05)

                }
            }
            7 -> {
                //第四幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(sn_talk_3[mStroy])
                } else {
                    Score.partnerSn = 4
                    Score.partnerSnTime = 0
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    4 -> binding.ivStory.setBackgroundResource(R.mipmap.sn07)

                }
            }
            8 -> {
                //第五幕
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(sn_talk_3[mStroy])
                } else {
                    Score.partnerSnTime = 0
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    4 -> binding.ivStory.setBackgroundResource(R.mipmap.sn10)
                    7 -> binding.ivStory.setBackgroundResource(R.mipmap.sn11)
                    12 -> {
                        binding.tvTalk.isClickable = false
                        binding.rbYes.setText(sn_talk_4[13])
                        binding.rbNo.setText(sn_talk_4[14])
                        binding.rgStory.visibility = View.VISIBLE
                    }

                }
            }
            9 -> {
                //第六幕--A
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(sn_talk_5_a[mStroy])
                } else {
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    6 -> {
                        Score.ability = Score.ability.plus(500)
                        Score.experience = Score.experience.plus(500)
                        Score.happy = Score.happy.plus(100)
                        Score.money = Score.money.plus(100000)
                    }
                    13 -> binding.ivStory.setBackgroundResource(R.mipmap.sn13)
                    30 -> {
                        toast("你和女友十娘的感情加深了，爱情机遇指数+2")
                        Score.love = Score.love.plus(2)
                    }

                }
            }
            10 -> {
                //第六幕--B
                if (mStroy <= mStroyMax) {
                    binding.tvTalk.setText(sn_talk_5_b[mStroy])
                } else {
                    startActivity<GameActivity>()
                    activity?.finish()
                }
                when (mStroy) {
                    3 -> binding.ivStory.setBackgroundResource(R.mipmap.sn19)
                    5 -> binding.ivStory.setBackgroundResource(R.mipmap.sn18)
                    8 -> binding.ivStory.setBackgroundResource(R.mipmap.sn08)
                    14 -> binding.ivStory.setBackgroundResource(R.mipmap.sn15)
                    16 -> binding.ivStory.setBackgroundResource(R.mipmap.sn16)
                    22 -> binding.ivStory.setBackgroundResource(R.mipmap.sn17)
                    74 -> {
                        toast("你和女友十娘分手了")
                        Score.partner = 0
                        //TODO:红娘会馆删除十娘信息
                        Score.partnerSn = 7
                    }
                }
            }
        }
    }

    companion object {
        private val sn_talk_0 = intArrayOf(
            R.string.shiniang_0_00, R.string.shiniang_0_01, R.string.shiniang_0_02,
            R.string.shiniang_0_03, R.string.shiniang_0_04, R.string.shiniang_0_05,
            R.string.shiniang_0_06, R.string.shiniang_0_07, R.string.shiniang_0_08,
            R.string.shiniang_0_09, R.string.shiniang_0_10, R.string.shiniang_0_11,
            R.string.shiniang_0_12, R.string.shiniang_0_13, R.string.shiniang_0_14,
            R.string.shiniang_0_15, R.string.shiniang_0_16, R.string.shiniang_0_17,
            R.string.shiniang_0_18, R.string.shiniang_0_19, R.string.shiniang_0_20
        )
        private val sn_talk_1 = intArrayOf(
            R.string.shiniang_1_00, R.string.shiniang_1_01, R.string.shiniang_1_02,
            R.string.shiniang_1_03, R.string.shiniang_1_04, R.string.shiniang_1_05,
            R.string.shiniang_1_06, R.string.shiniang_1_07, R.string.shiniang_1_08,
            R.string.shiniang_1_09, R.string.shiniang_1_10, R.string.shiniang_1_11,
            R.string.shiniang_1_12, R.string.shiniang_1_13, R.string.shiniang_1_14,
            R.string.shiniang_1_15, R.string.shiniang_1_16, R.string.shiniang_1_17,
            R.string.shiniang_1_18
        )
        private val sn_talk_2 = intArrayOf(
            R.string.shiniang_2_00, R.string.shiniang_2_01, R.string.shiniang_2_02,
            R.string.shiniang_2_03, R.string.shiniang_2_04, R.string.shiniang_2_05,
            R.string.shiniang_2_06, R.string.shiniang_2_07, R.string.shiniang_2_08,
            R.string.shiniang_2_09, R.string.shiniang_2_10, R.string.shiniang_2_11,
            R.string.shiniang_2_12, R.string.shiniang_2_13, R.string.shiniang_2_14,
            R.string.shiniang_2_15, R.string.shiniang_2_16, R.string.shiniang_2_17,
            R.string.shiniang_2_18, R.string.shiniang_2_19, R.string.shiniang_2_20,
            R.string.shiniang_2_21, R.string.shiniang_2_22, R.string.shiniang_2_23,
            R.string.shiniang_2_24, R.string.shiniang_2_25, R.string.shiniang_2_26,
            R.string.shiniang_2_27, R.string.shiniang_2_28
        )
        private val sn_talk_3 = intArrayOf(
            R.string.shiniang_3_00, R.string.shiniang_3_01, R.string.shiniang_3_02,
            R.string.shiniang_3_03, R.string.shiniang_3_04, R.string.shiniang_3_05,
            R.string.shiniang_3_06, R.string.shiniang_3_07, R.string.shiniang_3_08,
            R.string.shiniang_3_09, R.string.shiniang_3_10, R.string.shiniang_3_11,
            R.string.shiniang_3_12, R.string.shiniang_3_13, R.string.shiniang_3_14,
            R.string.shiniang_3_15, R.string.shiniang_3_16, R.string.shiniang_3_17
        )
        private val sn_talk_4 = intArrayOf(
            R.string.shiniang_4_00, R.string.shiniang_4_01, R.string.shiniang_4_02,
            R.string.shiniang_4_03, R.string.shiniang_4_04, R.string.shiniang_4_05,
            R.string.shiniang_4_06, R.string.shiniang_4_07, R.string.shiniang_4_08,
            R.string.shiniang_4_09, R.string.shiniang_4_10, R.string.shiniang_4_11,
            R.string.shiniang_4_12, R.string.shiniang_4_13, R.string.shiniang_4_14
        )
        private val sn_talk_5_a = intArrayOf(
            R.string.shiniang_5_a_00, R.string.shiniang_5_a_01, R.string.shiniang_5_a_02,
            R.string.shiniang_5_a_03, R.string.shiniang_5_a_04, R.string.shiniang_5_a_05,
            R.string.shiniang_5_a_06, R.string.shiniang_5_a_07, R.string.shiniang_5_a_08,
            R.string.shiniang_5_a_09, R.string.shiniang_5_a_10, R.string.shiniang_5_a_11,
            R.string.shiniang_5_a_12, R.string.shiniang_5_a_13, R.string.shiniang_5_a_14,
            R.string.shiniang_5_a_15, R.string.shiniang_5_a_16, R.string.shiniang_5_a_17,
            R.string.shiniang_5_a_18, R.string.shiniang_5_a_19, R.string.shiniang_5_a_20,
            R.string.shiniang_5_a_21, R.string.shiniang_5_a_22, R.string.shiniang_5_a_23,
            R.string.shiniang_5_a_24, R.string.shiniang_5_a_25, R.string.shiniang_5_a_26,
            R.string.shiniang_5_a_27, R.string.shiniang_5_a_28, R.string.shiniang_5_a_29,
            R.string.shiniang_5_a_30
        )
        private val sn_talk_5_b = intArrayOf(
            R.string.shiniang_5_b_00, R.string.shiniang_5_b_01, R.string.shiniang_5_b_02,
            R.string.shiniang_5_b_03, R.string.shiniang_5_b_04, R.string.shiniang_5_b_05,
            R.string.shiniang_5_b_06, R.string.shiniang_5_b_07, R.string.shiniang_5_b_08,
            R.string.shiniang_5_b_09, R.string.shiniang_5_b_10, R.string.shiniang_5_b_11,
            R.string.shiniang_5_b_12, R.string.shiniang_5_b_13, R.string.shiniang_5_b_14,
            R.string.shiniang_5_b_15, R.string.shiniang_5_b_16, R.string.shiniang_5_b_17,
            R.string.shiniang_5_b_18, R.string.shiniang_5_b_19, R.string.shiniang_5_b_20,
            R.string.shiniang_5_b_21, R.string.shiniang_5_b_22, R.string.shiniang_5_b_23,
            R.string.shiniang_5_b_24, R.string.shiniang_5_b_25, R.string.shiniang_5_b_26,
            R.string.shiniang_5_b_27, R.string.shiniang_5_b_28, R.string.shiniang_5_b_29,
            R.string.shiniang_5_b_30, R.string.shiniang_5_b_31, R.string.shiniang_5_b_32,
            R.string.shiniang_5_b_33, R.string.shiniang_5_b_34, R.string.shiniang_5_b_35,
            R.string.shiniang_5_b_36, R.string.shiniang_5_b_37, R.string.shiniang_5_b_38,
            R.string.shiniang_5_b_39, R.string.shiniang_5_b_40, R.string.shiniang_5_b_41,
            R.string.shiniang_5_b_42, R.string.shiniang_5_b_43, R.string.shiniang_5_b_44,
            R.string.shiniang_5_b_45, R.string.shiniang_5_b_46, R.string.shiniang_5_b_47,
            R.string.shiniang_5_b_48
        )
    }
}