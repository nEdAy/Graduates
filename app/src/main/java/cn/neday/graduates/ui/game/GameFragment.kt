package cn.neday.graduates.ui.game

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import cn.neday.graduates.MusicConductor.playSound
import cn.neday.graduates.R
import cn.neday.graduates.activity.MainActivity
import cn.neday.graduates.activity.OverActivity
import cn.neday.graduates.activity.StorylineActivity
import cn.neday.graduates.databinding.FragmentGameBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import cn.neday.graduates.repository.Settings
import cn.neday.graduates.view.NiftyDialogBuilder
import cn.neday.graduates.view.SolarSystem.MenuStatus
import com.dylanc.longan.doOnClick
import com.dylanc.longan.startActivity
import com.dylanc.longan.toast
import java.util.*

class GameFragment : BaseBindingFragment<FragmentGameBinding>() {
    private var mStockValue: Int = 0
    private var mIndexStockValue: Int = 0
    private var mIndexHouseValue: Int = 0
    private var mIncomeValue: Int = 0
    private var mCommunicationMonthlyValue: Int = 0
    private var mHappyMonthlyValue: Int = 0
    private var mLoadValue: Int = 0
    private var mHealthyValue: Int = 0
    private var mMoneyValue: Int = 0
    private var mAbilityValue: Int = 0
    private var mExperienceValue: Int = 0
    private var mHappyValue: Int = 0
    private var mMoralityValue: Int = 0
    private var mCommunicationValue: Int = 0
    private var mPositionValue: Int = 0
    private var mCarValue: Int = 0
    private var mHouseValue: Int = 0
    private var mPartnerValue: Int = 0
    private var mTimeValue: Int = 0
    private var mMonthValue: Int = 0

    private var mAnimation: AnimationDrawable? = null
    private var animationDrawable: AnimationDrawable? = null
    private val handler = Handler { msg ->
        if (msg.what == 101) animationDrawable?.stop()
        false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setMValue()
        setClickAble()
        setSsItemChoose()
    }

    private fun setUpViews() {
        setSolarSystem()
        setListener()
    }

    private fun setSolarSystem() {
        val relative = binding.ss.relative
        val outMetrics = DisplayMetrics()
        activity?.window?.windowManager?.defaultDisplay?.getMetrics(outMetrics)
        val screenWidth = outMetrics.widthPixels
        val lp = relative.layoutParams
        lp.width = screenWidth / 3
        lp.height = screenWidth / 3
        relative.layoutParams = lp
        relative.setBackgroundResource(R.drawable.animation_rect_list)
        animationDrawable = relative.background as AnimationDrawable
        val solarSystem = binding.ss.solar
        val childCount = solarSystem.childCount
        val tvParams = FrameLayout.LayoutParams(
            screenWidth / 5, screenWidth / 5
        )
        for (i in 0 until childCount - 1) {
            solarSystem.getChildAt(i).layoutParams = tvParams
        }
        solarSystem.setRadius(screenWidth / 3)
        solarSystem.setRotaMenu(true)
        solarSystem.toggleMenu(300)
        solarSystem.setOnMenuStatus(object : MenuStatus {
            override fun openMenu() {
                animationDrawable?.start()
                handler.sendEmptyMessageDelayed(101, 600)
            }

            override fun closeMenu() {
                animationDrawable?.start()
                handler.sendEmptyMessageDelayed(101, 600)
            }
        })

        // 通过ImageView对象拿到背景显示的AnimationDrawable
        val centerMenu = binding.ss.centerMenu
        mAnimation = centerMenu.background as AnimationDrawable?
        centerMenu.post { mAnimation?.start() }
    }

    private fun setListener() {
        binding.stock.doOnClick {
            Score.isStock = true
            playSound(R.raw.button_0)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(
                    R.id.fragment_game,
                    StockFragment()
                )?.addToBackStack(null)?.commit()
        }
        binding.position.doOnClick {
            playSound(R.raw.button_0)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(
                    R.id.fragment_game,
                    PositionFragment()
                )?.addToBackStack(null)?.commit()
        }
        binding.house.doOnClick {
            Score.isHouse = true
            playSound(R.raw.button_0)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(
                    R.id.fragment_game,
                    HouseFragment()
                )?.addToBackStack(null)?.commit()
        }
        binding.car.doOnClick {
            playSound(R.raw.button_0)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(
                    R.id.fragment_game,
                    CarFragment()
                )?.addToBackStack(null)?.commit()
        }
        binding.partner.doOnClick {
            playSound(R.raw.button_0)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(
                    R.id.fragment_game,
                    PartnerFragment()
                )?.addToBackStack(null)?.commit()
        }
        binding.lottery.doOnClick {
            playSound(R.raw.button_0)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(
                    R.id.fragment_game,
                    LotteryFragment()
                )?.addToBackStack(null)?.commit()
        }

        binding.btnExit.doOnClick { showReturnDialog() }
        binding.btnSave.doOnClick { goSave() }
        binding.btnLoad.doOnClick {
            if (Settings.isSave) {
                showLoadDialog()
            } else {
                toast(R.string.no_save)
            }
        }

        binding.btnGoNextMonth.doOnClick {
            playSound(R.raw.next_month)
            if (mHappyValue < 0 || mMoralityValue < 0 || mHealthyValue < 0) {
                playSound(R.raw.die)
                toGameOver()
            } else if (mMonthValue <= 0) {
                toGameOver()
            } else {
                if (mPartnerValue == 7 && mMonthValue <= 24) {
                    beginStroySn()
                }
                setMonthValue()
                val randomE = Random()
                val e = randomE.nextInt(2) //0~1
                if (e == 0) {
                    setRandomEvent()
                }
                onActivityCreated(null)
            }
        }
    }

    private fun setMValue() {
        mLoadValue = Settings.load

        mHealthyValue = Score.healthy
        mMoneyValue = Score.money
        mAbilityValue = Score.ability
        mExperienceValue = Score.experience
        mHappyValue = Score.happy
        mMoralityValue = Score.morality
        mCommunicationValue = Score.communication
        mMonthValue = Score.month
        mTimeValue = Score.time
        mCommunicationMonthlyValue = Score.communicationMonthly
        mHappyMonthlyValue = Score.happyMonthly
        val mAgeValue = 22 + (96 - mMonthValue) / 12
        mCarValue = Score.car
        mPartnerValue = Score.partner
        mPositionValue = Score.position
        mHouseValue = Score.house
        mIncomeValue = Score.income
        mStockValue = Score.stock
        mIndexStockValue = Score.indexStock
        mIndexHouseValue = Score.indexHouse
        binding.tvHealthy.text = mHealthyValue.toString()
        binding.tvMoney.text = mMoneyValue.toString()
        binding.tvAbility.text = mAbilityValue.toString()
        binding.tvExperience.text = mExperienceValue.toString()
        binding.tvHappy.text = mHappyValue.toString()
        binding.tvMorality.text = mMoralityValue.toString()
        binding.tvCommunication.text = mCommunicationValue.toString()
        binding.tvMonth.text = mMonthValue.toString()
        binding.tvTime.text = mTimeValue.toString()
        binding.tvAge.text = mAgeValue.toString()
        binding.tvPosition.text = mPositionString[mPositionValue]
        binding.tvCar.text = mCarString[mCarValue]
        binding.tvHouse.text = mHouseString[mHouseValue]
        binding.tvPartner.text = mPartnerString[mPartnerValue]
        if (mMonthValue <= 0) {
            toGameOver()
        }
    }

    private fun setClickAble() {
        val isCar: Boolean = Score.isCar
        binding.car.isClickable = !isCar
        binding.car.setImageResource(if (isCar) R.mipmap.qiche_1 else R.mipmap.qiche_0)
        val isHouse: Boolean = Score.isHouse
        binding.house.isClickable = !isHouse
        binding.house.setImageResource(if (isHouse) R.mipmap.fangchan_1 else R.mipmap.fangchan_0)
        val isPartner: Boolean = Score.isPartner
        binding.partner.isClickable = !isPartner
        binding.partner.setImageResource(if (isPartner) R.mipmap.hongniang_1 else R.mipmap.hongniang_0)
        val isPosition: Boolean = Score.isPosition
        binding.position.isClickable = !isPosition
        binding.position.setImageResource(if (isPosition) R.mipmap.rencai_1 else R.mipmap.rencai_0)
        val isLottery: Boolean = Score.isLottery
        binding.lottery.isClickable = !isLottery
        binding.lottery.setImageResource(if (isLottery) R.mipmap.caipiao_1 else R.mipmap.caipiao_0)
        val isStock: Boolean = Score.isStock
        binding.stock.isClickable = !isStock
        binding.stock.setImageResource(if (isStock) R.mipmap.gupiao_1 else R.mipmap.gupiao_0)
    }

    private fun setSsItemChoose() {
        binding.ss.solar.setOnMenuItemClickListener { view: View, _: Int ->
            when (view.tag as String) {
                "yd" -> if (checkValue(30, 200)) {
                    playSound(R.raw.read)
                    showImageViewDialog(R.string.plan, R.string.plan_0, R.mipmap.rd)
                    setValue(0, -200, 6, 7, -2, 0, 0, -30)
                    Score.yd = Score.yd.plus(1)
                    if (Score.yd == 30) {
                        showRgChooseViewDialog(
                            R.string.opportunity,
                            R.string.opportunity_0,
                            R.string.opportunity_0_a,
                            R.string.opportunity_0_b,
                            R.string.opportunity_0_c,
                            R.string.opportunity_0_d,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "bl" -> if (checkValue(30, 100)) {
                    playSound(R.raw.read)
                    showImageViewDialog(R.string.plan, R.string.plan_1, R.mipmap.bl)
                    setValue(0, -100, 4, 5, 2, 0, 0, -30)
                    Score.bl = Score.bl.plus(1)
                    if (Score.bl == 30) {
                        showRgChooseViewDialog(
                            R.string.opportunity,
                            R.string.opportunity_1,
                            R.string.opportunity_1_a,
                            R.string.opportunity_1_b,
                            R.string.opportunity_1_c,
                            R.string.opportunity_1_d,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "ts" -> if (checkValue(30, 1000)) {
                    playSound(R.raw.bar)
                    showImageViewDialog(R.string.plan, R.string.plan_2, R.mipmap.ts)
                    setValue(-2, -1000, 0, 4, 5, -2, 6, -30)
                    Score.ts = Score.ts.plus(1)
                    if (Score.ts == 20) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_2,
                            R.string.opportunity_2_b,
                            R.string.opportunity_2_c,
                            R.string.opportunity_2_a,
                            R.string.opportunity_2_d,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "tx" -> if (checkValue(30, 600)) {
                    playSound(R.raw.classmate)
                    showImageViewDialog(R.string.plan, R.string.plan_3, R.mipmap.tx)
                    setValue(0, -600, 0, 2, 3, 0, 4, -30)
                    Score.py = Score.py.plus(1)
                    if (Score.py == 30) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_3,
                            R.string.opportunity_3_a,
                            R.string.opportunity_3_d,
                            R.string.opportunity_3_c,
                            R.string.opportunity_3_b,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "gj" -> if (checkValue(30, 1000)) {
                    playSound(R.raw.shopping)
                    showImageViewDialog(R.string.plan, R.string.plan_4, R.mipmap.gj)
                    setValue(0, -1000, 0, 2, 7, 0, 0, -30)
                    // 接下来（当月即可）选择“逛街购物吃饭”，可触发“女友情节：昭君?第二幕”；
                    if (mPartnerValue == 3 && Score.partnerZj == 1) {
                        Score.partnerStory = 2
                        startActivity<StorylineActivity>()
                        activity?.finish()
                    }
                    Score.gj = Score.gj.plus(1)
                    if (Score.gj == 30) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_4,
                            R.string.opportunity_4_c,
                            R.string.opportunity_4_a,
                            R.string.opportunity_4_d,
                            R.string.opportunity_4_b,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "cm" -> if (checkValue(60, 3000)) {
                    playSound(R.raw.tour)
                    showImageViewDialog(R.string.plan, R.string.plan_5, R.mipmap.cm)
                    setValue(4, -3000, 4, 4, 10, 0, 2, -60)
                    // 接下来（仍当月即可）选择“出门旅游度假”，可触发“女友情节：昭君?第三幕”。
                    if (mPartnerValue == 3 && Score.partnerZj == 2) {
                        Score.partnerStory = 2
                        startActivity<StorylineActivity>()
                        activity?.finish()
                    }
                    Score.cm = Score.cm.plus(1)
                    if (Score.cm == 30) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_5,
                            R.string.opportunity_5_d,
                            R.string.opportunity_5_a,
                            R.string.opportunity_5_b,
                            R.string.opportunity_5_c,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            }, object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            }, object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            }, object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            })
                    }
                }
                "cj" -> if (checkValue(60, 2000)) {
                    playSound(R.raw.train)
                    showImageViewDialog(R.string.plan, R.string.plan_6, R.mipmap.cj)
                    setValue(-2, -2000, 20, 20, -4, 0, 0, -60)
                    ////说明：首先满足条件——女友是昭君，
                    // 然后在计划安排时选择“参加学习培训”，可触发“女友情节：昭君?第一幕”；
                    if (mPartnerValue == 3 && Score.partnerZj == 0) {
                        Score.partnerStory = 1
                        startActivity<StorylineActivity>()
                        activity?.finish()
                    }
                    Score.cj = Score.cj.plus(1)
                    if (Score.cj == 30) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_6,
                            R.string.opportunity_6_c,
                            R.string.opportunity_6_b,
                            R.string.opportunity_6_a,
                            R.string.opportunity_6_d,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "zj" -> if (checkValue(20, -5000000)) {
                    playSound(R.raw.sleep)
                    showImageViewDialog(R.string.plan, R.string.plan_7, R.mipmap.zj)
                    setValue(2, 0, 0, 0, 2, 0, 0, -20)
                    Score.zj = Score.zj.plus(1)
                    if (Score.zj == 30) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_7,
                            R.string.opportunity_7_a,
                            R.string.opportunity_7_c,
                            R.string.opportunity_7_b,
                            R.string.opportunity_7_d,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "jb" -> if (checkValue(60, -5000000)) {
                    playSound(R.raw.hammer)
                    showImageViewDialog(R.string.plan, R.string.plan_8, R.mipmap.jb)
                    setValue(-5, 1500, 10, 10, -8, 0, 0, -60)
                    Score.jb = Score.jb.plus(1)
                    if (Score.jb == 20) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_8,
                            R.string.opportunity_8_d,
                            R.string.opportunity_8_b,
                            R.string.opportunity_8_c,
                            R.string.opportunity_8_a,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "sw" -> if (checkValue(60, -100)) {
                    playSound(R.raw.internet)
                    showImageViewDialog(R.string.plan, R.string.plan_9, R.mipmap.sw)
                    setValue(-2, -100, 0, 2, 8, 0, -1, -60)
                    Score.sw = Score.sw.plus(1)
                    if (Score.sw == 20) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_9,
                            R.string.opportunity_9_b,
                            R.string.opportunity_9_a,
                            R.string.opportunity_9_d,
                            R.string.opportunity_9_c,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            }, object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            }, object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            }, object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            })
                    }
                }
                "ty" -> if (checkValue(30, -100)) {
                    playSound(R.raw.exercise)
                    showImageViewDialog(R.string.plan, R.string.plan_10, R.mipmap.ty)
                    setValue(5, -100, 3, 0, 2, 0, 0, -30)
                    Score.ty = Score.ty.plus(1)
                    if (Score.ty == 30) {
                        showRgChooseViewDialog(
                            R.string.opportunity,
                            R.string.opportunity_10,
                            R.string.opportunity_10_d,
                            R.string.opportunity_10_c,
                            R.string.opportunity_10_a,
                            R.string.opportunity_10_b,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
                "hj" -> if (checkValue(30, -300)) {
                    playSound(R.raw.parents)
                    showImageViewDialog(R.string.plan, R.string.plan_11, R.mipmap.hj)
                    setValue(1, -300, 0, 0, 2, 2, 0, -30)
                    Score.hj = Score.hj.plus(1)
                    if (Score.hj == 30) {
                        showRgChooseViewDialog(R.string.opportunity,
                            R.string.opportunity_11,
                            R.string.opportunity_11_a,
                            R.string.opportunity_11_b,
                            R.string.opportunity_11_c,
                            R.string.opportunity_11_d,
                            object : RbAonClickListener {
                                override fun onClick() {
                                    toast(R.string.both_add)
                                    Score.love = Score.love.plus(1)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbBonClickListener {
                                override fun onClick() {
                                    toast(R.string.love_add)
                                    Score.love = Score.love.plus(1)
                                    onActivityCreated(null)
                                }
                            },
                            object : RbConClickListener {
                                override fun onClick() {
                                    toast(R.string.no_add)
                                }
                            },
                            object : RbDonClickListener {
                                override fun onClick() {
                                    toast(R.string.career_add)
                                    Score.career = Score.career.plus(1)
                                    onActivityCreated(null)
                                }
                            })
                    }
                }
            }
        }
        binding.ss.solar.setOnMenuItemLongClickListener { view: View, _: Int ->
            when (view.tag as String) {
                "yd" -> toast(R.string.plan_time_0)
                "bl" -> toast(R.string.plan_time_1)
                "ts" -> toast(R.string.plan_time_2)
                "tx" -> toast(R.string.plan_time_3)
                "gj" -> toast(R.string.plan_time_4)
                "cm" -> toast(R.string.plan_time_5)
                "cj" -> toast(R.string.plan_time_6)
                "zj" -> toast(R.string.plan_time_7)
                "jb" -> toast(R.string.plan_time_8)
                "sw" -> toast(R.string.plan_time_9)
                "ty" -> toast(R.string.plan_time_10)
                "hj" -> toast(R.string.plan_time_11)
                else -> {}
            }
        }
    }

    private fun setValue(
        mHealthyValue: Int, mMoneyValue: Int, mAbilityValue: Int, mExperienceValue: Int,
        mHappyValue: Int, mMoralityValue: Int, mCommunicationValue: Int, mTimeValue: Int
    ) {
        Score.healthy = Score.healthy.plus(mHealthyValue)
        Score.money = Score.money.plus(mMoneyValue)
        Score.ability = Score.ability.plus(mAbilityValue)
        Score.experience = Score.experience.plus(mExperienceValue)
        Score.happy = Score.happy.plus(mHappyValue)
        Score.morality = Score.morality.plus(mMoralityValue)
        Score.communication = Score.communication.plus(mCommunicationValue)
        Score.time = Score.time.plus(mTimeValue)
        onActivityCreated(null)
    }

    override fun onResume() {
        super.onResume()
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _: View?, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                // 屏蔽back
                showReturnDialog()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun checkValue(nTime: Int, nMoney: Int): Boolean {
        return if (mTimeValue < nTime) {
            toast(R.string.time_run_out)
            false
        } else if (mMoneyValue < nMoney) {
            toast(R.string.money_run_out)
            false
        } else {
            true
        }
    }

    private fun beginStroySn() {
        val partnerSn: Int = Score.partnerSn
        val partnerSnTime: Int = Score.partnerSnTime
        //如果你的女友是十娘在你28岁时开始会有剧情，注意如果剩余时间不够24个月将无法触发十娘的所有剧情。
        if (partnerSn == 0) {
            Score.partnerStory = (4)
            startActivity<StorylineActivity>()
            activity?.finish()
        } else {
            Score.partnerSnTime = Score.partnerSnTime.plus(1)
        }
        //第二幕在四个月后
        if (partnerSn == 1 && partnerSnTime == 4) {
            Score.partnerStory = (5)
            startActivity<StorylineActivity>()
            activity?.finish()
        }
        //第三幕在四个月后
        if (partnerSn == 2 && partnerSnTime == 4) {
            Score.partnerStory = (6)
            startActivity<StorylineActivity>()
            activity?.finish()
        }
        //第四幕在四个月后
        if (partnerSn == 3 && partnerSnTime == 4) {
            Score.partnerStory = (7)
            startActivity<StorylineActivity>()
            activity?.finish()
        }
        //第五幕在四个月后
        if (partnerSn == 4 && partnerSnTime == 4) {
            Score.partnerStory = (8)
            startActivity<StorylineActivity>()
            activity?.finish()
        }
        //第六幕A在六个月后
        if (partnerSn == 5 && partnerSnTime == 6) {
            Score.partnerStory = (9)
            startActivity<StorylineActivity>()
            activity?.finish()
        }
        //第六幕B在六个月后
        if (partnerSn == 6 && partnerSnTime == 6) {
            Score.partnerStory = (10)
            startActivity<StorylineActivity>()
            activity?.finish()
        }
    }

    private fun setRandomEvent() {
        //随机事件指数
        val randomS = Random()
        val s = randomS.nextInt(26) //0~25
        val randomT = Random()
        val t = randomT.nextInt(2) //0~1
        when (s) {
            0 -> showImageChooseViewDialog(R.string.event_0, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_0_a)
                        toast("快乐-3，道德-10")
                        Score.happy = Score.happy.plus(-3)
                        Score.morality = Score.morality.plus(-10)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_0_b)
                        toast("快乐-5，道德-15")
                        Score.happy = Score.happy.plus(-5)
                        Score.morality = Score.morality.plus(-15)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_0_c)
                        toast(
                            "金钱-800，快乐+5，道德+10"
                        )
                        Score.money = Score.money.plus(-800)
                        Score.happy = Score.happy.plus(+5)
                        Score.morality = Score.morality.plus(+10)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_0_d)
                        toast("金钱-500，快乐+3，道德+5")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(+3)
                        Score.morality = Score.morality.plus(+5)
                        onActivityCreated(null)
                    }
                }
            })
            1 -> showImageChooseViewDialog(R.string.event_1, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_1_a)
                        toast(
                            "金钱+1000 快乐+3 交际+3"
                        )
                        Score.money = Score.money.plus(+1000)
                        Score.happy = Score.happy.plus(+3)
                        Score.communication = Score.communication.plus(+3)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_1_b)
                        toast("金钱-2000 快乐-3")
                        Score.happy = Score.happy.plus(-3)
                        Score.money = Score.money.plus(-2000)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_1_c)
                        toast("金钱-3000,快乐-5")
                        Score.money = Score.money.plus(-3000)
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_1_d)
                        toast("金钱+5000 快乐+5 经验+10")
                        Score.money = Score.money.plus(+5000)
                        Score.happy = Score.happy.plus(+5)
                        Score.experience = Score.experience.plus(+10)
                        onActivityCreated(null)
                    }
                }
            })
            2 -> showImageChooseViewDialog(R.string.event_2, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_2_a)
                        toast("金钱-2000 快乐-5 健康-10")
                        Score.money = Score.money.plus(-2000)
                        Score.happy = Score.happy.plus(-5)
                        Score.healthy = Score.healthy.plus(-10)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_2_b)
                        toast("快乐+3，经验+5，能力+5，道德+10，交际+5")
                        Score.happy = Score.happy.plus(+3)
                        Score.experience = Score.experience.plus(+5)
                        Score.ability = Score.ability.plus(+5)
                        Score.morality = Score.morality.plus(+10)
                        Score.communication = Score.communication.plus(+5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_2_c)
                        toast("金钱+100000，经验+10，能力+10，快乐-5，道德-20")
                        Score.money = Score.money.plus(+10000)
                        Score.experience = Score.experience.plus(+10)
                        Score.ability = Score.ability.plus(+10)
                        Score.happy = Score.happy.plus(-5)
                        Score.morality = Score.morality.plus(-20)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_2_d)
                        toast("金钱-30000，经验+10，能力-10，健康-10，快乐-10，道德-20")
                        Score.money = Score.money.plus(-30000)
                        Score.experience = Score.experience.plus(+10)
                        Score.ability = Score.ability.plus(-10)
                        Score.healthy = Score.healthy.plus(-10)
                        Score.happy = Score.happy.plus(-10)
                        Score.morality = Score.morality.plus(-20)
                        onActivityCreated(null)
                    }
                }
            })
            3 -> showImageChooseViewDialog(R.string.event_3, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_3_a)
                        toast("快乐-3 经验+5")
                        Score.happy = Score.happy.plus(-3)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_3_b)
                        toast("金钱-30%，快乐-10，经验+10，能力+10")
                        Score.money = Score.money.plus((-(Score.money * 0.3)).toInt())
                        Score.experience = Score.experience.plus(+10)
                        Score.happy = Score.happy.plus(-10)
                        Score.ability = Score.ability.plus(+10)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_3_c)
                        toast("经验+8")
                        Score.experience = Score.experience.plus(+8)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_3_d)
                        toast("金钱+30%，快乐+10，经验+10，能力+10")
                        Score.money = Score.money.plus((+(Score.money * 0.3)).toInt())
                        Score.experience = Score.experience.plus(+10)
                        Score.ability = Score.ability.plus(+10)
                        Score.happy = Score.happy.plus(+10)
                        onActivityCreated(null)
                    }
                }
            })
            4 -> showImageChooseViewDialog(R.string.event_4, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_4_a)
                        toast("快乐+8")
                        Score.happy = Score.happy.plus(+8)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_4_b)
                        toast("快乐+5")
                        Score.happy = Score.happy.plus(+5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_4_c)
                        toast("金钱-3000 快乐-2")
                        Score.money = Score.money.plus(-3000)
                        Score.happy = Score.happy.plus(-2)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_4_d)
                        toast("金钱-1000，快乐-3")
                        Score.money = Score.money.plus(-1000)
                        Score.happy = Score.happy.plus(-3)
                        onActivityCreated(null)
                    }
                }
            })
            5 -> showImageChooseViewDialog(R.string.event_5, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_5_a)
                        toast("金钱-500，快乐-3，经验+5")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(-3)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_5_b)
                        toast("金钱-500，快乐+3，交际+5，经验+10")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(+3)
                        Score.communication = Score.communication.plus(+5)
                        Score.experience = Score.experience.plus(+10)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_5_c)
                        toast("金钱-500，快乐-5，健康-5，交际+2，经验+5")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(-5)
                        Score.healthy = Score.healthy.plus(-5)
                        Score.communication = Score.communication.plus(+2)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_5_d)
                        toast("金钱-300，快乐-5，健康-5")
                        Score.money = Score.money.plus(-300)
                        Score.happy = Score.happy.plus(-5)
                        Score.healthy = Score.healthy.plus(-5)
                        onActivityCreated(null)
                    }
                }
            })
            6 -> showImageChooseViewDialog(R.string.event_6, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_6_a)
                        toast("能力+3，经验+3，交际-3，快乐-5")
                        Score.ability = Score.ability.plus(+3)
                        Score.experience = Score.experience.plus(+3)
                        Score.communication = Score.communication.plus(-3)
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_6_b)
                        toast("能力+8，经验+8，交际+5，快乐+3")
                        Score.ability = Score.ability.plus(+8)
                        Score.experience = Score.experience.plus(+8)
                        Score.communication = Score.communication.plus(+5)
                        Score.happy = Score.happy.plus(+3)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_6_c)
                        toast("能力+3，经验+3，交际-3，快乐-5")
                        Score.ability = Score.ability.plus(+3)
                        Score.experience = Score.experience.plus(+3)
                        Score.communication = Score.communication.plus(-3)
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_6_d)
                        toast("能力+10，经验+10，交际+8，快乐+8")
                        Score.ability = Score.ability.plus(+10)
                        Score.experience = Score.experience.plus(+10)
                        Score.communication = Score.communication.plus(+8)
                        Score.happy = Score.happy.plus(+8)
                        onActivityCreated(null)
                    }
                }
            })
            7 -> showImageChooseViewDialog(R.string.event_7, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_7_a)
                        toast("快乐+3 健康+2 经验+5")
                        Score.happy = Score.happy.plus(+3)
                        Score.healthy = Score.healthy.plus(+2)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_7_b)
                        toast("快乐-10 健康-10")
                        Score.happy = Score.happy.plus(-10)
                        Score.healthy = Score.healthy.plus(-10)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_7_c)
                        toast("金钱-500 快乐+5 健康+5")
                        Score.happy = Score.happy.plus(+5)
                        Score.money = Score.money.plus(-500)
                        Score.healthy = Score.healthy.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_7_d)
                        toast("金钱-500 快乐-5 健康-5")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(-5)
                        Score.healthy = Score.healthy.plus(-5)
                        onActivityCreated(null)
                    }
                }
            })
            8 -> showImageChooseViewDialog(R.string.event_8, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_8_a)
                        toast("快乐+5，经验+5")
                        Score.happy = Score.happy.plus(+5)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_8_b)
                        toast("经验+3 快乐-5")
                        Score.experience = Score.experience.plus(+3)
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_8_c)
                        toast("金钱-40%，快乐-20，经验+5，能力+5")
                        Score.money = Score.money.plus((-(Score.money * 0.4)).toInt())
                        Score.happy = Score.happy.plus(-20)
                        Score.experience = Score.experience.plus(+5)
                        Score.ability = Score.ability.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_8_d)
                        toast("金钱+30%，快乐+20 经验+10 能力+10")
                        Score.money = Score.money.plus((+(Score.money * 0.3)).toInt())
                        Score.happy = Score.happy.plus(+20)
                        Score.experience = Score.experience.plus(+10)
                        Score.ability = Score.ability.plus(+10)
                        onActivityCreated(null)
                    }
                }
            })
            9 -> showImageChooseViewDialog(R.string.event_9, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_9_a)
                        toast(
                            "快乐+5，交际+5，道德+8"
                        )
                        Score.happy = Score.happy.plus(+5)
                        Score.communication = Score.communication.plus(+5)
                        Score.morality = Score.morality.plus(+8)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_9_b)
                        toast("经验+5，快乐+1，道德+5")
                        Score.experience = Score.experience.plus(+5)
                        Score.happy = Score.happy.plus(+1)
                        Score.morality = Score.morality.plus(+5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_9_c)
                        toast("金钱+12500，快乐+8，道德-10")
                        Score.money = Score.money.plus(+12500)
                        Score.happy = Score.happy.plus(+8)
                        Score.morality = Score.morality.plus(-10)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_9_d)
                        toast("金钱-500，快乐-5，道德-7")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(-5)
                        Score.morality = Score.morality.plus(-7)
                        onActivityCreated(null)
                    }
                }
            })
            10 -> showImageChooseViewDialog(R.string.event_10, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_10_a)
                        toast("快乐+5，经验+5")
                        Score.happy = Score.happy.plus(+5)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_10_b)
                        toast("快乐+3 经验+3")
                        Score.happy = Score.happy.plus(+3)
                        Score.experience = Score.experience.plus(+3)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_10_c)
                        toast("金钱-10000，快乐-8，经验+5")
                        Score.money = Score.money.plus(-10000)
                        Score.happy = Score.happy.plus(-8)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_10_d)
                        toast("金钱-12000，快乐-10，经验+5")
                        Score.money = Score.money.plus(-12000)
                        Score.happy = Score.happy.plus(+10)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    }
                }
            })
            11 -> showImageChooseViewDialog(R.string.event_11, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_11_a)
                        toast("金钱-1000 健康-10 快乐-10 道德-5")
                        Score.money = Score.money.plus(-1000)
                        Score.happy = Score.happy.plus(+10)
                        Score.healthy = Score.healthy.plus(-10)
                        Score.morality = Score.morality.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_11_b)
                        toast("道德-10，快乐-10")
                        Score.happy = Score.happy.plus(-10)
                        Score.morality = Score.morality.plus(-10)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_11_c)
                        toast("道德+10，金钱-5000")
                        Score.money = Score.money.plus(-5000)
                        Score.morality = Score.morality.plus(+10)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_11_d)
                        toast("道德+15，金钱-13200")
                        Score.money = Score.money.plus(+13200)
                        Score.morality = Score.morality.plus(+15)
                        onActivityCreated(null)
                    }
                }
            })
            12 -> showImageChooseViewDialog(R.string.event_12, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_12_a)
                        toast("快乐-3，道德-5")
                        Score.happy = Score.happy.plus(-3)
                        Score.morality = Score.morality.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_12_b)
                        toast("快乐-5，道德-3")
                        Score.happy = Score.happy.plus(-5)
                        Score.morality = Score.morality.plus(-3)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_12_c)
                        toast("金钱-800，快乐+2，道德+5")
                        Score.money = Score.money.plus(-800)
                        Score.happy = Score.happy.plus(+2)
                        Score.morality = Score.morality.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_12_d)
                        toast("金钱-500，快乐+3，道德+3")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(+3)
                        Score.morality = Score.morality.plus(+3)
                        onActivityCreated(null)
                    }
                }
            })
            13 -> showImageChooseViewDialog(R.string.event_13, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_13_a)
                        toast("道德-8")
                        Score.morality = Score.morality.plus(-8)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_13_b)
                        toast("道德-5")
                        Score.morality = Score.morality.plus(-5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_13_c)
                        toast("金钱-800，经验+3，道德+8")
                        Score.money = Score.money.plus(-800)
                        Score.experience = Score.experience.plus(+3)
                        Score.morality = Score.morality.plus(+8)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_13_d)
                        toast("金钱-1000，经验+5，道德+10")
                        Score.money = Score.money.plus(-1000)
                        Score.experience = Score.experience.plus(+5)
                        Score.morality = Score.morality.plus(+10)
                        onActivityCreated(null)
                    }
                }
            })
            14 -> showImageChooseViewDialog(R.string.event_14, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_14_a)
                        toast("金钱-500，快乐-3")
                        Score.money = Score.money.plus(-500)
                        Score.happy = Score.happy.plus(-3)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_14_b)
                        toast("快乐-5")
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_14_c)
                        toast("健康-3，快乐-2，经验+5")
                        Score.healthy = Score.healthy.plus(-3)
                        Score.happy = Score.happy.plus(-2)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_14_d)
                        toast("金钱-300，快乐+3，经验+3")
                        Score.money = Score.money.plus(-300)
                        Score.happy = Score.happy.plus(+3)
                        Score.experience = Score.experience.plus(+3)
                        onActivityCreated(null)
                    }
                }
            })
            15 -> showImageChooseViewDialog(R.string.event_15, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_15_a)
                        toast("经验+2")
                        Score.experience = Score.experience.plus(+2)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_15_b)
                        toast("经验+1")
                        Score.experience = Score.experience.plus(+1)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_15_c)
                        toast("金钱+50000，经验+10，能力+10，健康-5，快乐+5")
                        Score.healthy = Score.healthy.plus(-5)
                        Score.happy = Score.happy.plus(+5)
                        Score.experience = Score.experience.plus(+10)
                        Score.ability = Score.ability.plus(+10)
                        Score.money = Score.money.plus(+50000)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_15_d)
                        toast("金钱-10000，经验+5，快乐-5")
                        Score.money = Score.money.plus(-10000)
                        Score.happy = Score.happy.plus(-5)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    }
                }
            })
            16 -> showImageChooseViewDialog(R.string.event_16, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_16_a)
                        toast("经验+3 道德-3")
                        Score.morality = Score.morality.plus(-3)
                        Score.experience = Score.experience.plus(+3)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_16_b)
                        toast("经验+1，道德-3")
                        Score.experience = Score.experience.plus(+1)
                        Score.morality = Score.morality.plus(-3)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_16_c)
                        toast("金钱-50，经验+2，道德+2")
                        Score.money = Score.money.plus(-50)
                        Score.experience = Score.experience.plus(+2)
                        Score.morality = Score.morality.plus(2)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_16_d)
                        toast("金钱-100，经验+3，道德+3")
                        Score.money = Score.money.plus(-100)
                        Score.experience = Score.experience.plus(+3)
                        Score.morality = Score.morality.plus(3)
                        onActivityCreated(null)
                    }
                }
            })
            17 -> showImageChooseViewDialog(R.string.event_17, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_17_a)
                        toast("金钱-500，经验+5，快乐-5")
                        Score.money = Score.money.plus(-500)
                        Score.experience = Score.experience.plus(+5)
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_17_b)
                        toast("金钱+500，快乐+5")
                        Score.money = Score.money.plus(+500)
                        Score.happy = Score.happy.plus(+5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_17_c)
                        toast("金钱-20，快乐+1")
                        Score.money = Score.money.plus(-20)
                        Score.happy = Score.happy.plus(+1)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_17_d)
                        toast("金钱-200，快乐-2")
                        Score.money = Score.money.plus(-200)
                        Score.happy = Score.happy.plus(-2)
                        onActivityCreated(null)
                    }
                }
            })
            18 -> showImageChooseViewDialog(R.string.event_18, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_18_a)
                        toast("交际-2")
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_18_b)
                        toast("交际-3，快乐-3")
                        Score.communication = Score.communication.plus(-3)
                        Score.happy = Score.happy.plus(-3)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_18_c)
                        toast("金钱-800，经验+3，交际+5，快乐+5")
                        Score.money = Score.money.plus(-800)
                        Score.experience = Score.experience.plus(+3)
                        Score.communication = Score.communication.plus(+5)
                        Score.happy = Score.happy.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_18_d)
                        toast("金钱-200，快乐-2")
                        Score.money = Score.money.plus(-200)
                        Score.happy = Score.happy.plus(-2)
                        onActivityCreated(null)
                    }
                }
            })
            19 -> showImageChooseViewDialog(R.string.event_19, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_19_a)
                        toast("经验+2")
                        Score.experience = Score.experience.plus(+2)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_19_b)
                        toast("经验+1")
                        Score.experience = Score.experience.plus(+1)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_19_c)
                        toast("金钱-30000，经验+5，能力+5，快乐-3")
                        Score.money = Score.money.plus(-30000)
                        Score.experience = Score.experience.plus(+5)
                        Score.ability = Score.ability.plus(+5)
                        Score.happy = Score.happy.plus(-3)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_19_d)
                        toast("金钱+400000，经验+10，能力+10，快乐+5")
                        Score.money = Score.money.plus(+400000)
                        Score.experience = Score.experience.plus(+10)
                        Score.ability = Score.ability.plus(+10)
                        Score.happy = Score.happy.plus(+5)
                        onActivityCreated(null)
                    }
                }
            })
            20 -> showImageChooseViewDialog(R.string.event_20, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_20_a)
                        toast("道德+2 快乐+2")
                        Score.happy = Score.happy.plus(+2)
                        Score.morality = Score.morality.plus(+2)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_20_b)
                        toast("道德+2")
                        Score.morality = Score.morality.plus(+2)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_20_c)
                        toast("金钱+100，快乐+4，道德-5")
                        Score.money = Score.money.plus(+100)
                        Score.happy = Score.happy.plus(+4)
                        Score.morality = Score.morality.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_20_d)
                        toast("金钱-500 快乐-3 道德-3")
                        Score.morality = Score.morality.plus(-3)
                        Score.happy = Score.happy.plus(-3)
                        Score.money = Score.money.plus(-500)
                        onActivityCreated(null)
                    }
                }
            })
            21 -> showImageChooseViewDialog(R.string.event_21, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_21_a)
                        toast("经验+4，能力+4，快乐-2，交际+4")
                        Score.ability = Score.ability.plus(+4)
                        Score.experience = Score.experience.plus(+4)
                        Score.happy = Score.happy.plus(-2)
                        Score.communication = Score.communication.plus(+4)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_21_b)
                        toast("快乐-4 健康-2 经验+2")
                        Score.happy = Score.happy.plus(-4)
                        Score.experience = Score.experience.plus(+2)
                        Score.healthy = Score.healthy.plus(-2)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_21_c)
                        toast("经验+8，能力+8，快乐+5，交际+5")
                        Score.ability = Score.ability.plus(+8)
                        Score.experience = Score.experience.plus(+8)
                        Score.happy = Score.happy.plus(+5)
                        Score.communication = Score.communication.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_21_d)
                        toast("经验+3，能力+3，快乐-3，交际-5")
                        Score.experience = Score.experience.plus(+3)
                        Score.ability = Score.ability.plus(+3)
                        Score.happy = Score.happy.plus(-3)
                        Score.communication = Score.communication.plus(-5)
                        onActivityCreated(null)
                    }
                }
            })
            22 -> showImageChooseViewDialog(R.string.event_22, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_22_a)
                        toast("快乐-5")
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_22_b)
                        toast("快乐+5")
                        Score.happy = Score.happy.plus(+5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_22_c)
                        toast("金钱-5000 快乐+5")
                        Score.money = Score.money.plus(-5000)
                        Score.happy = Score.happy.plus(+5)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_22_d)
                        toast("金钱-4000 快乐-5 经验+3")
                        Score.money = Score.money.plus(-4000)
                        Score.experience = Score.experience.plus(+3)
                        Score.happy = Score.happy.plus(-5)
                        onActivityCreated(null)
                    }
                }
            })
            23 -> showImageChooseViewDialog(R.string.event_23, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_23_a)
                        toast("能力-4，快乐-4")
                        Score.ability = Score.ability.plus(-4)
                        Score.happy = Score.happy.plus(-4)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_23_b)
                        toast("快乐+5 经验+5")
                        Score.happy = Score.happy.plus(+5)
                        Score.experience = Score.experience.plus(+5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_23_c)
                        toast("金钱+3000 能力+15 经验+15")
                        Score.money = Score.money.plus(+3000)
                        Score.experience = Score.experience.plus(+15)
                        Score.ability = Score.ability.plus(+15)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_23_d)
                        toast("经验+8，能力+8")
                        Score.experience = Score.experience.plus(+8)
                        Score.ability = Score.ability.plus(+8)
                        onActivityCreated(null)
                    }
                }
            })
            24 -> showImageChooseViewDialog(R.string.event_16, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_24_a)
                        toast("快乐+3，道德+8，经验+3")
                        Score.morality = Score.morality.plus(+8)
                        Score.experience = Score.experience.plus(+3)
                        Score.happy = Score.happy.plus(+3)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_24_b)
                        toast("快乐+5，交际+5，道德+8，经验+5")
                        Score.experience = Score.experience.plus(+5)
                        Score.morality = Score.morality.plus(-8)
                        Score.happy = Score.happy.plus(+5)
                        Score.communication = Score.communication.plus(+5)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_24_c)
                        toast("金钱-3000，经验+8，交际-8，快乐-8，道德-20")
                        Score.money = Score.money.plus(-3000)
                        Score.experience = Score.experience.plus(+8)
                        Score.morality = Score.morality.plus(-20)
                        Score.communication = Score.communication.plus(-8)
                        Score.happy = Score.happy.plus(-8)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_24_d)
                        toast("金钱+30000，经验+8，快乐+8，道德-20")
                        Score.money = Score.money.plus(+30000)
                        Score.experience = Score.experience.plus(+8)
                        Score.happy = Score.happy.plus(+8)
                        Score.morality = Score.morality.plus(-20)
                        onActivityCreated(null)
                    }
                }
            })
            25 -> showImageChooseViewDialog(R.string.event_25, object : Button1onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_25_a)
                        toast("道德+2")
                        Score.morality = Score.morality.plus(+2)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_25_b)
                        toast("经验+2")
                        Score.experience = Score.experience.plus(+2)
                        onActivityCreated(null)
                    }
                }
            }, object : Button2onClickListener {
                override fun onClick() {
                    if (t == 0) {
                        showViewDialog(R.string.event_25_c)
                        toast("金钱+20%，快乐+20，经验+5，能力+5，道德-1")
                        Score.money = Score.money.plus((+(Score.money * 0.2)).toInt())
                        Score.experience = Score.experience.plus(+5)
                        Score.morality = Score.morality.plus(-1)
                        Score.ability = Score.ability.plus(+5)
                        Score.happy = Score.happy.plus(+20)
                        onActivityCreated(null)
                    } else {
                        showViewDialog(R.string.event_25_d)
                        toast("金钱-30%，快乐-20，经验+5，能力+5，道德-1")
                        Score.money = Score.money.plus((-(Score.money * 0.3)).toInt())
                        Score.experience = Score.experience.plus(+5)
                        Score.morality = Score.morality.plus(-1)
                        Score.ability = Score.ability.plus(+5)
                        Score.happy = Score.happy.plus(-20)
                        onActivityCreated(null)
                    }
                }
            })

        }
    }

    private fun toGameOver() {
        startActivity<OverActivity>()
        activity?.finish()
    }

    private fun setMonthValue() {
        Score.isStock = false
        Score.isPosition = false
        Score.isHouse = false
        Score.isCar = false
        Score.isPartner = false
        Score.isLottery = false
        Score.month = Score.month.minus(1)
        Score.time = 230
        Score.money = Score.money.plus(mIncomeValue)
        Score.healthy = Score.healthy.minus(2)
        Score.ability = Score.ability.plus(11)
        Score.experience = Score.experience.plus(11)
        Score.happy = Score.happy.plus(mHappyMonthlyValue)
        Score.communication = Score.communication.plus(mCommunicationMonthlyValue)
        //随机股票指数
        val randomX = Random()
        var x = randomX.nextInt(21) //0~20,0~9 (┬＿┬)↘ 跌，10平，1~20 (￣︶￣)↗ 涨
        if (x < 20) {
            val randomY = Random()
            val y = randomY.nextInt(5)
            if (y < 3) {
                x += 1
            }
        }
        x += 90
        Score.randomStock = x
        Score.indexStock = mIndexStockValue * x / 100
        Score.stock = mStockValue * (x - 100) / 100

        //随机房价指数
        val randomM = Random()
        var m = randomM.nextInt(21) //0~30,0~9 (┬＿┬)↘ 跌，10平，11~20 (￣︶￣)↗ 涨
        if (m < 20) {
            val randomN = Random()
            val n = randomN.nextInt(5)
            if (n < 3) {
                m += 1
            }
        }
        m += 90
        Score.indexHouse = (mIndexHouseValue * m / 100)
    }

    private fun showLoadDialog() {
        val dialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle(R.string.load_or_un)
            .withMessage("您的数据将恢复到上次保存的值（可读档次数共9次，当前还剩" + mLoadValue + "次，请慎重使用）。")
            .isCancelable(true)
            .withDuration(500).withButtonCancle().withButtonOk()
            .setButtonCancleClick { dialogBuilder.getDismiss() }
            .setButtonOk {
                dialogBuilder.dismiss()
                goLoad()
            }.show()
    }

    private fun goSave() {
        playSound(R.raw.appreciation)
        Settings.isSave = true
        Score.manualSave()
        onActivityCreated(null)
        toast(R.string.save_success)
    }

    private fun goLoad() {
        if (mLoadValue == 0) {
            toast(R.string.load_times_run_out)
        } else {
            playSound(R.raw.appreciation)
            Score.manualLoad()
            Settings.load = Settings.load.minus(1)
            onActivityCreated(null)
            toast(R.string.load_success)
        }
    }

    private fun showReturnDialog() {
        val dialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle(R.string.return_main_or_un)
            .withMessage(R.string.return_main_or_un_0).isCancelable(true)
            .withDuration(500).withButtonCancle().withButtonOk()
            .setButtonCancleClick { dialogBuilder.getDismiss() }
            .setButtonOk {
                dialogBuilder.closeDialog(dialogBuilder)
                startActivity<MainActivity>()
                activity?.finish()
            }.show()
    }

    private fun showViewDialog(message: Int) {
        val dialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle(R.string.event)
            .withMessage(message).isCancelable(true)
            .withDuration(500).withButtonOk()
            .setButtonOk { dialogBuilder.closeDialog(dialogBuilder) }.show()
    }

    private fun showImageViewDialog(title: Int, message: Int, iv: Int) {
        val dialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle(title)
            .withMessage(message).withImageView(iv).isCancelable(true)
            .withDuration(500).withButtonOk()
            .setButtonOk { dialogBuilder.closeDialog(dialogBuilder) }.show()
    }

    private fun showImageChooseViewDialog(
        id: Int,
        Button1onClickListener: Button1onClickListener,
        Button2onClickListener: Button2onClickListener
    ) {
        val dialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle(R.string.event)
            .withMessage(id).isCancelable(false)
            .withDuration(500).withButtonCancle().withButtonOk()
            .setButtonCancleClick {
                Button1onClickListener.onClick()
                dialogBuilder.closeDialog(dialogBuilder)
            }
            .setButtonOk {
                Button2onClickListener.onClick()
                dialogBuilder.closeDialog(dialogBuilder)
            }.show()
    }

    private fun showRgChooseViewDialog(
        title: Int, message: Int,
        a: Int, b: Int, c: Int, d: Int,
        rbAonClickListener: RbAonClickListener,
        rbBonClickListener: RbBonClickListener,
        rbConClickListener: RbConClickListener,
        rbDonClickListener: RbDonClickListener
    ) {
        val dialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle(title).withRg(a, b, c, d).setRb_a {
            rbAonClickListener.onClick()
            dialogBuilder.closeDialog(dialogBuilder)
        }.setRb_b {
            rbBonClickListener.onClick()
            dialogBuilder.closeDialog(dialogBuilder)
        }.setRb_c {
            rbConClickListener.onClick()
            dialogBuilder.closeDialog(dialogBuilder)
        }.setRb_d {
            rbDonClickListener.onClick()
            dialogBuilder.closeDialog(dialogBuilder)
        }
            .withMessage(message).isCancelable(false)
            .withDuration(500).show()
    }

    private interface Button1onClickListener {
        fun onClick()
    }

    private interface Button2onClickListener {
        fun onClick()
    }

    private interface RbAonClickListener {
        fun onClick()
    }

    private interface RbBonClickListener {
        fun onClick()
    }

    private interface RbConClickListener {
        fun onClick()
    }

    private interface RbDonClickListener {
        fun onClick()
    }

    companion object {
        private val mPositionString = arrayOf(
            "国内中小公司基层员工", "国内知名公司基层员工", "海外驻华公司基层员工",
            "国内中小公司基层主管", "国内知名公司基层主管", "海外驻华公司基层主管",
            "国内中小公司中层经理", "国内知名公司中层经理", "海外驻华公司中层经理",
            "国内中小公司高层老总", "国内知名公司高层老总", "海外驻华公司高层老总"
        )
        private val mCarString = arrayOf(
            "你还没有买车", "你有一辆小型节油低档车", "你有一辆经济实用中档车",
            "你有一辆豪华舒适中档车", "你有一辆超豪华享受高档车", "你有一辆时尚拉风跑车"
        )
        private val mHouseString = arrayOf(
            "你还没有买房", "你有1室1厅的房子", "你有2室1厅的房子", "你有2室2厅的房子", "你有3室1厅的房子", "你有3室2厅的房子", "你有4室2厅的房子",
            "你有市郊豪华小别墅", "你有城区超豪华别墅"
        )
        private val mPartnerString = arrayOf(
            "你还没有女朋友", "你的女友是施施", "你的女友是阿禅",
            "你的女友是昭君", "你的女友是玉环", "你的女友是圆圆", "你的女友是香香",
            "你的女友是十娘", "你的女友是小小", "你的女友是飞燕", "你的女友是莺莺"
        )
    }
}