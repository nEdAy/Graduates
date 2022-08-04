package cn.neday.graduates.ui.gameFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.activity.StorylineActivity
import cn.neday.graduates.databinding.FragmentHouseBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import com.dylanc.longan.doOnClick
import com.dylanc.longan.startActivity
import com.dylanc.longan.toast

class HouseFragment : BaseBindingFragment<FragmentHouseBinding>() {
    private var myHouse: Int = 0
    private var indexHouse: Int = 0
    private var partnerXy: Int = 0
    private var money0: Int = 0
    private var money1: Int = 0
    private var money2: Int = 0
    private var money3: Int = 0
    private var money4: Int = 0
    private var money5: Int = 0
    private var money6: Int = 0
    private var money7: Int = 0
    private val houseText = intArrayOf(
        R.string.text_house_00, R.string.text_house_01, R.string.text_house_02,
        R.string.text_house_03, R.string.text_house_04, R.string.text_house_05,
        R.string.text_house_06, R.string.text_house_07, R.string.text_house_08,
        R.string.text_house_09, R.string.text_house_10, R.string.text_house_11,
        R.string.text_house_12, R.string.text_house_13, R.string.text_house_14,
        R.string.text_house_15, R.string.text_house_16, R.string.text_house_17,
        R.string.text_house_18, R.string.text_house_19, R.string.text_house_20,
        R.string.text_house_21, R.string.text_house_22, R.string.text_house_23,
        R.string.text_house_24, R.string.text_house_25, R.string.text_house_26,
        R.string.text_house_27, R.string.text_house_28, R.string.text_house_29,
        R.string.text_house_30, R.string.text_house_31, R.string.text_house_32,
        R.string.text_house_33, R.string.text_house_34, R.string.text_house_35,
        R.string.text_house_36, R.string.text_house_37, R.string.text_house_38,
        R.string.text_house_39, R.string.text_house_40, R.string.text_house_41,
        R.string.text_house_42, R.string.text_house_43, R.string.text_house_44,
        R.string.text_house_45, R.string.text_house_46, R.string.text_house_47,
        R.string.text_house_48, R.string.text_house_49, R.string.text_house_50,
        R.string.text_house_51, R.string.text_house_52, R.string.text_house_53,
        R.string.text_house_54, R.string.text_house_55, R.string.text_house_56,
        R.string.text_house_57, R.string.text_house_58, R.string.text_house_59,
        R.string.text_house_60, R.string.text_house_61, R.string.text_house_62,
        R.string.text_house_63, R.string.text_house_64, R.string.text_house_65,
        R.string.text_house_66, R.string.text_house_67, R.string.text_house_68,
        R.string.text_house_69, R.string.text_house_70, R.string.text_house_71,
        R.string.text_house_72, R.string.text_house_73, R.string.text_house_74,
        R.string.text_house_75, R.string.text_house_76, R.string.text_house_77,
        R.string.text_house_78, R.string.text_house_79, R.string.text_house_80,
        R.string.text_house_81, R.string.text_house_82, R.string.text_house_83,
        R.string.text_house_84, R.string.text_house_85, R.string.text_house_86,
        R.string.text_house_87, R.string.text_house_88, R.string.text_house_89,
        R.string.text_house_90, R.string.text_house_91, R.string.text_house_92,
        R.string.text_house_93, R.string.text_house_94, R.string.text_house_95,
        R.string.text_house_96, R.string.text_house_97, R.string.text_house_98,
        R.string.text_house_99
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpViews()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setUpViews() {
        binding.ivBack.doOnClick { activity?.supportFragmentManager?.popBackStack() }
        setListener()
    }

    private fun setListener() {
        binding.ivHouse.doOnClick { onClickXy() }
        binding.btnHouse0.doOnClick { onBuyOrSell(1, money0, 200, 5, 1) }
        binding.btnHouse1.doOnClick { onBuyOrSell(2, money1, 300, 5, 1) }
        binding.btnHouse2.doOnClick { onBuyOrSell(3, money2, 350, 5, 1) }
        binding.btnHouse3.doOnClick { onBuyOrSell(4, money3, 400, 8, 2) }
        binding.btnHouse4.doOnClick { onBuyOrSell(5, money4, 450, 8, 2) }
        binding.btnHouse5.doOnClick { onBuyOrSell(6, money5, 500, 8, 2) }
        binding.btnHouse6.doOnClick { onBuyOrSell(7, money6, 2000, 10, 3) }
        binding.btnHouse7.doOnClick { onBuyOrSell(8, money7, 4000, 15, 3) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myHouse = Score.house
        indexHouse = Score.indexHouse
        partnerXy = Score.partner
        onViewShow(myHouse)
    }

    private fun onViewShow(my_house: Int?) {
        if (partnerXy > 90) {
            binding.ivHouse.setImageResource(R.mipmap.house_3)
            binding.textHouse.setText(houseText[98])
            if (partnerXy > 97) {
                partnerXy = 98
            }
        } else {
            binding.textHouse.setText(houseText[0])
        }
        when (my_house) {
            0 -> {
                binding.btnHouse0.visibility = View.VISIBLE
                binding.btnHouse1.visibility = View.VISIBLE
                binding.btnHouse2.visibility = View.VISIBLE
                binding.btnHouse3.visibility = View.VISIBLE
                binding.btnHouse4.visibility = View.VISIBLE
                binding.btnHouse5.visibility = View.VISIBLE
                binding.btnHouse6.visibility = View.VISIBLE
                binding.btnHouse7.visibility = View.VISIBLE
            }
            1 -> {
                binding.btnHouse0.visibility = View.VISIBLE
                binding.btnHouse0.setImageResource(R.mipmap.btn_sale)
            }
            2 -> {
                binding.btnHouse1.visibility = View.VISIBLE
                binding.btnHouse1.setImageResource(R.mipmap.btn_sale)
            }
            3 -> {
                binding.btnHouse2.visibility = View.VISIBLE
                binding.btnHouse2.setImageResource(R.mipmap.btn_sale)
            }
            4 -> {
                binding.btnHouse3.visibility = View.VISIBLE
                binding.btnHouse3.setImageResource(R.mipmap.btn_sale)
            }
            5 -> {
                binding.btnHouse4.visibility = View.VISIBLE
                binding.btnHouse4.setImageResource(R.mipmap.btn_sale)
            }
            6 -> {
                binding.btnHouse5.visibility = View.VISIBLE
                binding.btnHouse5.setImageResource(R.mipmap.btn_sale)
            }
            7 -> {
                binding.btnHouse6.visibility = View.VISIBLE
                binding.btnHouse6.setImageResource(R.mipmap.btn_sale)
            }
            8 -> {
                binding.btnHouse7.visibility = View.VISIBLE
                binding.btnHouse7.setImageResource(R.mipmap.btn_sale)
            }

        }
        binding.tvIndex.text = "本月房价指数：$indexHouse%"
        money0 = 80000 * indexHouse / 100
        money1 = 100000 * indexHouse / 100
        money2 = 200000 * indexHouse / 100
        money3 = 240000 * indexHouse / 100
        money4 = 300000 * indexHouse / 100
        money5 = 360000 * indexHouse / 100
        money6 = 1500000 * indexHouse / 100
        money7 = 3000000 * indexHouse / 100
        binding.tvHouse0.text = "" + money0
        binding.tvHouse1.text = "" + money1
        binding.tvHouse2.text = "" + money2
        binding.tvHouse3.text = "" + money3
        binding.tvHouse4.text = "" + money4
        binding.tvHouse5.text = "" + money5
        binding.tvHouse6.text = "" + money6
        binding.tvHouse7.text = "" + money7
    }

    private fun onClickXy() {
        partnerXy++
        binding.textHouse.setText(houseText[partnerXy])
        when (partnerXy) {
            1 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            4 -> binding.ivHouse.isClickable = false
            5 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            8 -> binding.ivHouse.isClickable = false
            9 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            12 -> binding.ivHouse.isClickable = false
            13 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            15 -> binding.ivHouse.isClickable = false
            16 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            19 -> binding.ivHouse.isClickable = false
            20 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            21 -> binding.ivHouse.isClickable = false
            22 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            23 -> binding.ivHouse.isClickable = false
            24 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            26 -> binding.ivHouse.isClickable = false
            27 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            28 -> binding.ivHouse.isClickable = false
            29 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            30 -> binding.ivHouse.isClickable = false
            31 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            33 -> binding.ivHouse.isClickable = false
            34 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            35 -> binding.ivHouse.isClickable = false
            36 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            38 -> binding.ivHouse.isClickable = false
            39 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            40 -> binding.ivHouse.isClickable = false
            41 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            43 -> binding.ivHouse.isClickable = false
            44 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            45 -> binding.ivHouse.isClickable = false
            46 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            48 -> binding.ivHouse.isClickable = false
            49 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            50 -> binding.ivHouse.isClickable = false
            51 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            53 -> binding.ivHouse.isClickable = false
            54 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            55 -> binding.ivHouse.isClickable = false
            56 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            57 -> binding.ivHouse.isClickable = false
            58 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            59 -> binding.ivHouse.isClickable = false
            60 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            61 -> binding.ivHouse.isClickable = false
            62 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            63 -> binding.ivHouse.isClickable = false
            64 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            65 -> binding.ivHouse.isClickable = false
            66 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            67 -> binding.ivHouse.isClickable = false
            68 -> binding.ivHouse.setImageResource(R.mipmap.house_0)
            69 -> binding.ivHouse.isClickable = false
            70 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            71 -> binding.ivHouse.isClickable = false
            72 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            73 -> binding.ivHouse.isClickable = false
            74 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            75 -> binding.ivHouse.isClickable = false
            76 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            78 -> binding.ivHouse.isClickable = false
            79 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            82 -> binding.ivHouse.isClickable = false
            83 -> binding.ivHouse.setImageResource(R.mipmap.house_1)
            86 -> binding.ivHouse.isClickable = false
            87 -> binding.ivHouse.setImageResource(R.mipmap.house_2)
            90 -> {
                binding.ivHouse.isClickable = false
                Score.partnerStory = 0
                startActivity<StorylineActivity>()
                activity?.finish()
            }
            92 -> {
                binding.ivHouse.isClickable = false
                partnerXy = 97
            }
            94 -> binding.ivHouse.isClickable = false
            97 -> binding.ivHouse.isClickable = false
            99 -> binding.ivHouse.isClickable = false

        }
        Score.partnerXy = partnerXy
    }

    private fun onBuyOrSell(house: Int, money: Int, income: Int, value: Int, monthly: Int) {
        MusicConductor.playSound(R.raw.money)
        val mMoney: Int = Score.money
        if (myHouse == house) {
            Score.house = 0
            Score.money = Score.money.plus(money)
            Score.income = Score.income.plus(income)
            Score.happyMonthly = Score.happyMonthly.minus(monthly)
            activity?.supportFragmentManager?.popBackStack()
        } else {
            if (mMoney < money) {
                toast(R.string.house_no_money)
            } else {
                Score.house = house
                Score.money = Score.money.minus(money)
                Score.income = Score.income.minus(income)
                Score.ability = Score.ability.plus(value)
                Score.experience = Score.experience.plus(value)
                Score.happy = Score.happy.plus(value)
                Score.happyMonthly = Score.happyMonthly.plus(monthly)
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }
}