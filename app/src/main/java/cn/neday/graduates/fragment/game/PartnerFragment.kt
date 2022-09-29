package cn.neday.graduates.fragment.game

import android.os.Bundle
import android.view.View
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentPartnerBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import com.dylanc.longan.doOnClick
import com.dylanc.longan.toast

class PartnerFragment : BaseBindingFragment<FragmentPartnerBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        if (Score.partnerZj == 3) { //分手
            binding.ivPartner2.isClickable = false
            binding.ivPartner2.setImageResource(R.drawable.partners_2)
        }
        if (Score.partnerSn == 7) { //分手
            binding.ivPartner6.isClickable = false
            binding.ivPartner6.setImageResource(R.drawable.partners_6)
        }
        when (Score.partner) {
            1 -> {
                binding.ivPartner0.isClickable = false
                binding.ivPartner0.setImageResource(R.drawable.partners_0)
            }
            2 -> {
                binding.ivPartner1.isClickable = false
                binding.ivPartner1.setImageResource(R.drawable.partners_1)
            }
            3 -> {
                binding.ivPartner2.isClickable = false
                binding.ivPartner2.setImageResource(R.drawable.partners_2)
            }
            4 -> {
                binding.ivPartner3.isClickable = false
                binding.ivPartner3.setImageResource(R.drawable.partners_3)
            }
            5 -> {
                binding.ivPartner4.isClickable = false
                binding.ivPartner4.setImageResource(R.drawable.partners_4)
            }
            6 -> {
                binding.ivPartner5.isClickable = false
                binding.ivPartner5.setImageResource(R.drawable.partners_5)
            }
            7 -> {
                binding.ivPartner6.isClickable = false
                binding.ivPartner6.setImageResource(R.drawable.partners_6)
            }
            8 -> {
                binding.ivPartner7.isClickable = false
                binding.ivPartner7.setImageResource(R.drawable.partners_7)
            }
            9 -> {
                binding.ivPartner8.isClickable = false
                binding.ivPartner8.setImageResource(R.drawable.partners_8)
            }
            10 -> {
                binding.ivPartner9.isClickable = false
                binding.ivPartner9.setImageResource(R.drawable.partners_9)
            }
        }
    }

    private fun setUpViews() {
        binding.ivBack.doOnClick { popBackStack() }
        binding.ivPartner.doOnClickWithSoundAndLoseMoney1000 {
            binding.llPartner0.visibility = View.GONE
            binding.llPartner1.visibility = View.GONE
            binding.svPartner.visibility = View.VISIBLE
        }
        setListener()
    }

    private fun setListener() {
        binding.ivPartner0.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.house == 2 || Score.house == 3) {
                Score.partner = 1
            } else {
                toast(R.string.partner_0_plus)
            }
            popBackStack()
        }
        binding.ivPartner1.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.money > 2000000) {
                Score.partner = 2
            } else {
                toast(R.string.partner_1_plus)
            }
            popBackStack()
        }
        binding.ivPartner2.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.position == 8 || Score.position == 11) {
                Score.partner = 3
            } else {
                toast(R.string.partner_2_plus)
            }
            popBackStack()
        }
        binding.ivPartner3.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.position == 10 || Score.position == 11) {
                Score.partner = 4
            } else {
                toast(R.string.partner_3_plus)
            }
            popBackStack()
        }
        binding.ivPartner4.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.communication > 750) {
                Score.partner = 5
            } else {
                toast(R.string.partner_4_plus)
            }
            popBackStack()
        }
        binding.ivPartner5.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.car == 3 || Score.car == 4) {
                Score.partner = 6
            } else {
                toast(R.string.partner_5_plus)
            }
            popBackStack()
        }
        binding.ivPartner6.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.morality > 750) {
                Score.partner = 7
            } else {
                toast(R.string.partner_6_plus)
            }
            popBackStack()
        }
        binding.ivPartner7.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.healthy > 750) {
                Score.partner = 8
            } else {
                toast(R.string.partner_7_plus)
            }
        }
        binding.ivPartner8.doOnClickWithSoundAndLoseMoney1000 {
            popBackStack()
            if (Score.happy > 750) {
                Score.partner = 9
            } else {
                toast(R.string.partner_8_plus)
            }
            popBackStack()
        }
        binding.ivPartner9.doOnClickWithSoundAndLoseMoney1000 {
            if (Score.time < 24) {
                Score.partner = 10
            } else {
                toast(R.string.partner_9_plus)
            }
            popBackStack()
        }
    }


    private inline fun View.doOnClickWithSoundAndLoseMoney1000(crossinline block: () -> Unit) =
        setOnClickListener {
            Score.money = Score.money - 1000
            MusicConductor.playSound(R.raw.money)
            block()
        }
}