package cn.neday.graduates.ui.main

import android.os.Bundle
import android.view.View
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentSuggestBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Settings.isSponsoredUser
import com.dylanc.longan.doOnClick

class SuggestFragment : BaseBindingFragment<FragmentSuggestBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        binding.btnBack.doOnClick { popBackStack() }
        binding.rlSwitchSuggest.doOnClick {
//            if (suggest_money > 0) {
            if (binding.ivOpenSuggest.visibility == View.VISIBLE) {
                binding.ivOpenSuggest.visibility = View.INVISIBLE
                binding.ivCloseSuggest.visibility = View.VISIBLE
                isSponsoredUser = false
            } else {
                binding.ivOpenSuggest.visibility = View.VISIBLE
                binding.ivCloseSuggest.visibility = View.INVISIBLE
                isSponsoredUser = true
            }
//            } else {
//                toast(R.string.suggest_0)
//            }
        }
        binding.go.doOnClick { }
        binding.type.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.alipay -> binding.go.setImageResource(R.mipmap.btn_zfbzz)
                R.id.wxpay -> binding.go.setImageResource(R.mipmap.btn_wxzz)

            }
        }
    }

//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        val isUserSuggest = isSponsoredUser
//        if (isUserSuggest) {
//            iv_open_suggest?.visibility = View.VISIBLE
//            iv_close_suggest?.visibility = View.INVISIBLE
//        } else {
//            iv_open_suggest?.visibility = View.INVISIBLE
//            iv_close_suggest?.visibility = View.VISIBLE
//        }
//        val df = DecimalFormat("0.00")
//        val mSuggestMoney = df.format((suggest_money? / 100).toLong())
//        tv_suggest?.text = mSuggestMoney
//    }

//    // 默认为0.1
//    private fun getPrice(): Double {
//        var price = 1.0
//        try {
//            price = this.price?.text.toString().toDouble()
//        } catch (ignored: NumberFormatException) {
//        }
//        return price
//    }
}