package cn.neday.graduates.ui.gameFragment

import android.os.Bundle
import android.view.View
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentPositionBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import com.dylanc.longan.doOnClick
import com.dylanc.longan.toast

class PositionFragment : BaseBindingFragment<FragmentPositionBinding>() {
    private var lastPositionIncome: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        when (Score.position) {
            0 -> lastPositionIncome = 2200
            1 -> lastPositionIncome = 3300
            2 -> lastPositionIncome = 4400
            3 -> lastPositionIncome = 4400
            4 -> lastPositionIncome = 6600
            5 -> lastPositionIncome = 8800
            6 -> lastPositionIncome = 8800
            7 -> lastPositionIncome = 13200
            8 -> lastPositionIncome = 17600
            9 -> lastPositionIncome = 17600
            10 -> lastPositionIncome = 26400
            11 -> lastPositionIncome = 35200
        }
    }

    private fun setUpViews() {
        binding.ivBack.doOnClick { popBackStack() }
        setListener()
    }

    private fun setListener() {
        binding.btnPosition0.doOnClick { setValue(100, -500, 1, 1, 1, 1, 0, 2200) }
        binding.btnPosition1.doOnClick { setValue(400, -1000, 2, 2, 2, 2, 1, 3300) }
        binding.btnPosition2.doOnClick { setValue(800, -1500, 3, 3, 3, 3, 2, 4400) }
        binding.btnPosition3.doOnClick { setValue(1000, -2000, 3, 3, 3, 3, 3, 4400) }
        binding.btnPosition4.doOnClick { setValue(1500, -2500, 4, 4, 4, 4, 4, 6600) }
        binding.btnPosition5.doOnClick { setValue(2000, -3000, 5, 5, 5, 5, 5, 8800) }
        binding.btnPosition6.doOnClick { setValue(2500, -3500, 5, 5, 5, 5, 6, 8800) }
        binding.btnPosition7.doOnClick { setValue(3000, -4000, 6, 6, 6, 6, 7, 13200) }
        binding.btnPosition8.doOnClick { setValue(3500, -4500, 7, 7, 7, 7, 8, 17600) }
        binding.btnPosition9.doOnClick { setValue(4000, -5000, 7, 7, 7, 7, 9, 17600) }
        binding.btnPosition10.doOnClick { setValue(4500, -5500, 8, 8, 8, 8, 10, 26400) }
        binding.btnPosition11.doOnClick { setValue(5000, -6000, 9, 9, 9, 9, 11, 35200) }
    }

    private fun setValue(
        value: Int, moneyValue: Int, abilityValue: Int, experienceValue: Int,
        happyValue: Int, communicationValue: Int, position: Int, income: Int
    ) {
        if (Score.ability > value && Score.experience > value) {
            MusicConductor.playSound(R.raw.appreciation)
            Score.money = Score.money.plus(moneyValue)
            Score.ability = Score.ability.plus(abilityValue)
            Score.experience = Score.experience.plus(experienceValue)
            Score.happy = Score.happy.plus(happyValue)
            Score.communication = Score.communication.plus(communicationValue)
            Score.position = position
            Score.income = Score.income.plus(income - lastPositionIncome)
            toast(R.string.position_su)
        } else {
            toast(R.string.position_no_ae)
        }
        popBackStack()
    }
}