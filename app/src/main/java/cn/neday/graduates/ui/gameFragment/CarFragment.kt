package cn.neday.graduates.ui.gameFragment

import android.os.Bundle
import android.view.View
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentCarBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import cn.neday.graduates.view.NiftyDialogBuilder
import com.dylanc.longan.doOnClick
import com.dylanc.longan.toast

class CarFragment : BaseBindingFragment<FragmentCarBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        onViewShow()
    }

    private fun setUpViews() {
        binding.ivBack.doOnClick { activity?.supportFragmentManager?.popBackStack() }
        setListener()
    }

    private fun setListener() {
        binding.btnCar0.doOnClick { onBuyOrSell(1, 40000, 600, 10, 1) }
        binding.btnCar1.doOnClick { onBuyOrSell(2, 120000, 1000, 15, 2) }
        binding.btnCar2.doOnClick { onBuyOrSell(3, 350000, 1500, 20, 2) }
        binding.btnCar3.doOnClick { onBuyOrSell(4, 1600000, 3000, 30, 3) }
        binding.btnCar4.doOnClick { onBuyOrSell(5, 2500000, 4000, 40, 3) }
        binding.ivCar0.doOnClick { showBigImageViewDialog(R.mipmap.qc4) }
        binding.ivCar1.doOnClick { showBigImageViewDialog(R.mipmap.qc3) }
        binding.ivCar2.doOnClick { showBigImageViewDialog(R.mipmap.qc2) }
        binding.ivCar3.doOnClick { showBigImageViewDialog(R.mipmap.qc1) }
        binding.ivCar4.doOnClick { showBigImageViewDialog(R.mipmap.qc0) }
    }


    private fun onViewShow() {
        binding.btnCar0.visibility = View.INVISIBLE
        binding.btnCar1.visibility = View.INVISIBLE
        binding.btnCar2.visibility = View.INVISIBLE
        binding.btnCar3.visibility = View.INVISIBLE
        binding.btnCar4.visibility = View.INVISIBLE
        when (Score.car) {
            0 -> {
                binding.btnCar0.visibility = View.VISIBLE
                binding.btnCar1.visibility = View.VISIBLE
                binding.btnCar2.visibility = View.VISIBLE
                binding.btnCar3.visibility = View.VISIBLE
                binding.btnCar4.visibility = View.VISIBLE
                binding.btnCar0.setImageResource(R.mipmap.btn_buy)
                binding.btnCar1.setImageResource(R.mipmap.btn_buy)
                binding.btnCar2.setImageResource(R.mipmap.btn_buy)
                binding.btnCar3.setImageResource(R.mipmap.btn_buy)
                binding.btnCar4.setImageResource(R.mipmap.btn_buy)
            }
            1 -> {
                binding.btnCar0.visibility = View.VISIBLE
                binding.btnCar0.setImageResource(R.mipmap.btn_sale)
            }
            2 -> {
                binding.btnCar1.visibility = View.VISIBLE
                binding.btnCar1.setImageResource(R.mipmap.btn_sale)
            }
            3 -> {
                binding.btnCar2.visibility = View.VISIBLE
                binding.btnCar2.setImageResource(R.mipmap.btn_sale)
            }
            4 -> {
                binding.btnCar3.visibility = View.VISIBLE
                binding.btnCar3.setImageResource(R.mipmap.btn_sale)
            }
            5 -> {
                binding.btnCar4.visibility = View.VISIBLE
                binding.btnCar4.setImageResource(R.mipmap.btn_sale)
            }

        }
    }

    private fun onBuyOrSell(car: Int, money: Int, income: Int, value: Int, monthly: Int) {
        MusicConductor.playSound(R.raw.money)
        if (Score.car == car) {
            Score.car = 0
            Score.money = Score.money.plus(money / 2)
            Score.income = Score.income.plus(income)
            Score.communicationMonthly = Score.communicationMonthly.minus(monthly)
        } else {
            if (Score.money < money) {
                toast(R.string.car_no_money)
            } else {
                Score.car = car
                Score.money = Score.money.minus(money)
                Score.income = Score.income.minus(income)
                Score.communicationMonthly = Score.communicationMonthly.plus(monthly)
                Score.experience = Score.experience.plus(value)
                Score.happy = Score.happy.plus(value)
                activity?.supportFragmentManager?.popBackStack()
            }
        }
        onActivityCreated(null)
    }

    private fun showBigImageViewDialog(id: Int) {
        NiftyDialogBuilder
            .getInstance(activity)
            .isCancelable(true)
            .withDuration(500)
            .withBigImageView(id)
            .show()
    }
}